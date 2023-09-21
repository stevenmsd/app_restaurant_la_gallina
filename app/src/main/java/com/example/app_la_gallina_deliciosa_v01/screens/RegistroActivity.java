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
import com.example.app_la_gallina_deliciosa_v01.models.Auth.RegistroRequest;
import com.example.app_la_gallina_deliciosa_v01.models.Auth.RegistroResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroActivity extends AppCompatActivity {

    private EditText nombreEditText;
    private EditText correoEditText;
    private EditText contrasenaEditText;
    private Button registroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreEditText = findViewById(R.id.editTextNombre);
        correoEditText = findViewById(R.id.editTextCorreo);
        contrasenaEditText = findViewById(R.id.editTextContrasena);
        registroButton = findViewById(R.id.buttonRegistro);

        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarRegistro();
            }
        });
    }

    private void realizarRegistro() {
        String nombre = nombreEditText.getText().toString();
        String correo = correoEditText.getText().toString();
        String contrasena = contrasenaEditText.getText().toString();

        // Crea una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.108:3002") // Reemplaza con la URL de tu servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea una instancia de la interfaz de servicio
        ApiService apiService = retrofit.create(ApiService.class);

        // Crea un objeto de solicitud de registro
        RegistroRequest registroRequest = new RegistroRequest();
        registroRequest.setNombre(nombre);
        registroRequest.setCorreo(correo);
        registroRequest.setContrasena(contrasena);

        // Realiza la solicitud de registro
        Log.d("RegistroActivity", "Nombre: " + nombre);
        Log.d("RegistroActivity", "Correo: " + correo);
        Log.d("RegistroActivity", "Contraseña: " + contrasena);
        Call<RegistroResponse> call = apiService.registrarUsuario(registroRequest);
        Log.d("Call","Esto"+call.request());
        call.enqueue(new Callback<RegistroResponse>() {
            @Override
            public void onResponse(Call<RegistroResponse> call, Response<RegistroResponse> response) {

                if (response.isSuccessful()) {
                    RegistroResponse registroResponse = response.body();
                    String mensaje = registroResponse.getMensaje();
                    mostrarMensajeEmergente("Registro Exitoso", mensaje);
                } else {
                    mostrarMensajeEmergente("Registro Fallido", "Ocurrió un error durante el registro.");
                }
            }

            @Override
            public void onFailure(Call<RegistroResponse> call, Throwable t) {
                Log.e("RegistroActivity", "Error de Conexión: " + t.getMessage());
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
    public void iniciarSesion(View view) {
        Intent intent = new Intent(this, LoginActivity.class); // Reemplaza LoginActivity.class con la actividad de inicio de sesión correcta
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Establece las banderas para eliminar las actividades anteriores
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }


}
