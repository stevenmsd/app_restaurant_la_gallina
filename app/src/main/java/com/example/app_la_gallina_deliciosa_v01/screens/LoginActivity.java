package com.example.app_la_gallina_deliciosa_v01.screens;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_la_gallina_deliciosa_v01.R;
import com.example.app_la_gallina_deliciosa_v01.api.ApiService;
import com.example.app_la_gallina_deliciosa_v01.models.Auth.LoginRequest;
import com.example.app_la_gallina_deliciosa_v01.models.Auth.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText correoEditText;
    private EditText contrasenaEditText;
    private Button iniciarSesionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correoEditText = findViewById(R.id.editTextCorreo);
        contrasenaEditText = findViewById(R.id.editTextContrasena);
        iniciarSesionButton = findViewById(R.id.buttonIniciarSesion);

        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        String correo = correoEditText.getText().toString();
        String contrasena = contrasenaEditText.getText().toString();

        // Crea una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.108:3002") // Reemplaza con la URL de tu servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea una instancia de la interfaz de servicio
        ApiService apiService = retrofit.create(ApiService.class);

        // Crea un objeto de solicitud de inicio de sesión
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo(correo);
        loginRequest.setContrasena(contrasena);

        // Realiza la solicitud de inicio de sesión
        Log.d("LoginActivity", "Correo: " + correo);
        Log.d("LoginActivity", "Contraseña: " + contrasena);
        Call<LoginResponse> call = apiService.iniciarSesion(loginRequest);
        Log.d("Call", "Esto" + call.request());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    String mensaje = loginResponse.getMensaje();

                    mostrarMensajeEmergente("Registro Exitoso", mensaje);
                } else {
                    mostrarMensajeEmergente("Inicio de Sesión Fallido", "Credenciales incorrectas");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LoginActivity", "Error de Conexión: " + t.getMessage());
                mostrarMensajeEmergente("Error de Conexión", "No se pudo conectar al servidor.");
            }
        });
    }

    private void mostrarMensajeEmergente(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Puedes realizar acciones adicionales al hacer clic en Aceptar si es necesario
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void Registrarse(View view) {
        Intent intent = new Intent(this, RegistroActivity.class); // Reemplaza RegistroActivity.class con la actividad de registro correcta
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Establece las banderas para eliminar las actividades anteriores
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }
}
