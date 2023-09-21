package com.example.app_la_gallina_deliciosa_v01.api;

import com.example.app_la_gallina_deliciosa_v01.models.Auth.LoginRequest;
import com.example.app_la_gallina_deliciosa_v01.models.Auth.LoginResponse;
import com.example.app_la_gallina_deliciosa_v01.models.Auth.RegistroRequest;
import com.example.app_la_gallina_deliciosa_v01.models.Auth.RegistroResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/registro")
    Call<RegistroResponse> registrarUsuario(@Body RegistroRequest registroRequest);

    @POST("/api/login")
    Call<LoginResponse> iniciarSesion(@Body LoginRequest loginRequest);
}
