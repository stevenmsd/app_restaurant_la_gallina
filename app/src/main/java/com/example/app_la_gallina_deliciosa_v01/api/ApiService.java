package com.example.app_la_gallina_deliciosa_v01.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/registro")
    Call<RegistroResponse> registrarUsuario(@Body RegistroRequest registroRequest);
}
