package br.edu.ifsp.projeto_dmos5.api;

import java.util.List;

import br.edu.ifsp.projeto_dmos5.model.Elephant;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ElephantService {
    String baseURL = "https://elephant-api.herokuapp.com";

    @GET("elephants")
    Call<List<Elephant>> findAll();
}
