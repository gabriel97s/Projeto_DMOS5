package br.edu.ifsp.projeto_dmos5.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.projeto_dmos5.R;
import br.edu.ifsp.projeto_dmos5.api.ElephantService;
import br.edu.ifsp.projeto_dmos5.dao.ElephantDAO;
import br.edu.ifsp.projeto_dmos5.model.Elephant;
import br.edu.ifsp.projeto_dmos5.ui.recyclerview.adapter.ListElephantAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 64;
    public static final String KEY_ELEPHANT_OBJECT = "ELEPHANT";

    private ElephantDAO elephantDAO;

    private RecyclerView recyclerElephants;
    private ListElephantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initResources();
    }

    private void initResources() {
        elephantDAO = new ElephantDAO(getApplicationContext());
    }

    private void initComponents() {
        Button btnFindElephants = findViewById(R.id.btn_find_elephants);
        recyclerElephants = findViewById(R.id.recyclerview_elephant_list);
        btnFindElephants.setOnClickListener(this::findElephants);
    }

    private void findElephants(View view) {
        if (checkPermission()) {
            List<Elephant> elephants = findAllElephantsDatabase();
            /* se vier os elefantes do banco eu carrego o adapter, caso contrário busco na API
             * e salvo no banco e depois carrego no adapter de maneira unitária.*/
            if (elephants.size() > 0) {
                refreshAdapter(elephants);
            } else {
                findAllElephantsApi();
            }
        } else {
            requestPermission();
        }
    }

    private void saveElephantsInDatabase(List<Elephant> elephants) {
        elephantDAO.refreshTimeMilliBeforeInsert();
        for (Elephant elephant : elephants) {
            /* Eu faço esta tratátiva, pois alguns "elefantes" que estão vindo da API, estão somente
             * com o id.
             * */
            if (!(elephant.getName() == null)) {
                // salvando no banco
                elephantDAO.add(elephant);
                // carregando no adapter de maneira unitária
                refreshAdapter(elephant);
            }
        }
    }

    private List<Elephant> findAllElephantsDatabase() {
        return elephantDAO.findAll();
    }

    private void findAllElephantsApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ElephantService.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ElephantService service = retrofit.create(ElephantService.class);

        Call<List<Elephant>> call = service.findAll();


        call.enqueue(new Callback<List<Elephant>>() {
            @Override
            public void onResponse(Call<List<Elephant>> call, Response<List<Elephant>> response) {
                if (response.isSuccessful()) {
                    List<Elephant> elephants = response.body();
                    saveElephantsInDatabase(elephants);
                } else {
                    Toast.makeText(
                            getApplicationContext()
                            , R.string.error_retrieving_data_from_api
                            , Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<List<Elephant>> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext()
                        , R.string.error_generic
                        , Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void setAdapter(List<Elephant> elephants) {
        adapter = new ListElephantAdapter(getApplicationContext(), elephants);
        adapter.setClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), ElephantInfoActivity.class);
            intent.putExtra(KEY_ELEPHANT_OBJECT, adapter.getElephant(position));
            startActivity(intent);
        });
        recyclerElephants.setAdapter(adapter);
    }

    private void refreshAdapter(List<Elephant> elephants) {
        if (adapter == null) {
            setAdapter(elephants);
        } else {
            adapter.change(elephants);
        }
    }

    private void refreshAdapter(Elephant elephant) {
        if (adapter == null) {
            setAdapter(new ArrayList<>(100));
        }
        adapter.add(elephant);
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this
                , Manifest.permission.INTERNET
        )) {
            new AlertDialog.Builder(getApplicationContext())
                    .setMessage(R.string.msg_info_permission_internet)
                    .setPositiveButton(
                            R.string.grant_permission
                            , (dialogInterface, i) -> ActivityCompat.requestPermissions(
                                    MainActivity.this,
                                    new String[]{
                                            Manifest.permission.INTERNET
                                    },
                                    REQUEST_PERMISSION
                            ))
                    .setNegativeButton(
                            R.string.no_grant_permission
                            , (dialogInterface, i) -> dialogInterface.dismiss())
                    .show();
        }
    }
}