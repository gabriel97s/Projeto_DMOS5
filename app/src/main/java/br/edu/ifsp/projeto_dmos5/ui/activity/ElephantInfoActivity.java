package br.edu.ifsp.projeto_dmos5.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import br.edu.ifsp.projeto_dmos5.R;
import br.edu.ifsp.projeto_dmos5.model.Elephant;

public class ElephantInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elephant_info);

        Elephant elephant =
                (Elephant) getIntent().getSerializableExtra(MainActivity.KEY_ELEPHANT_OBJECT);

        initComponents(elephant);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponents(Elephant elephant) {
        TextView textNameDescription = findViewById(R.id.text_name_description);
        TextView textAffiliationDescription = findViewById(R.id.text_affiliation_description);
        TextView textSpecieDescription = findViewById(R.id.text_specie_description);
        TextView textSexDescription = findViewById(R.id.text_sex_description);
        TextView textFictional = findViewById(R.id.text_fictional_description);
        TextView textNoteDescription = findViewById(R.id.text_note_description);
        TextView textDobDescription = findViewById(R.id.text_dob_description);
        TextView textDodDescription = findViewById(R.id.text_dod_description);
        TextView textWikiDescription = findViewById(R.id.text_wiki_description);
        ImageView imageElephantPicture = findViewById(R.id.image_elephant_picture);

        textNameDescription.setText(elephant.getName());
        textAffiliationDescription.setText(elephant.getAffiliation());
        textSpecieDescription.setText(elephant.getSpecies());
        textSexDescription.setText(elephant.getSex());
        textFictional.setText(elephant.getFictional().equals("true") ? "Yes" : "No");
        textNoteDescription.setText(elephant.getNote());
        textDobDescription.setText(elephant.getDob());
        textDodDescription.setText(elephant.getDod());
        textWikiDescription.setText(elephant.getWikilink());
        Picasso.get().load(elephant.getImage()).into(imageElephantPicture);

        textWikiDescription.setOnClickListener(v -> {
            Uri uri = Uri.parse(elephant.getWikilink());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }
}