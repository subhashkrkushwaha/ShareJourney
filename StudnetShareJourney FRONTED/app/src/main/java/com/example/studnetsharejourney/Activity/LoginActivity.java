package com.example.studnetsharejourney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.studnetsharejourney.API.ApiInterface;
import com.example.studnetsharejourney.Entity.LoginRequest;
import com.example.studnetsharejourney.Entity.LoginResponse;
import com.example.studnetsharejourney.MainActivity;
import com.example.studnetsharejourney.R;
import com.example.studnetsharejourney.API.RetrofitClient;
import com.example.studnetsharejourney.Utiles.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton btnLogin, btnSignupRedirect;
     EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignupRedirect = findViewById(R.id.btnSignupRedirect);

        btnLogin.setOnClickListener(v -> loginUser());

        btnSignupRedirect.setOnClickListener(v -> startActivity(new Intent(this, SingupActivity.class)));
    }

    private void loginUser() {
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
//        LoginRequest loginRequest = new LoginRequest(etUsername.getText().toString(), etPassword.getText().toString());
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // 🔴 Validation
        if (username.isEmpty()) {
            etUsername.setError("Username required");
            etUsername.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password required");
            etPassword.requestFocus();
            return;
        }
        LoginRequest loginRequest = new LoginRequest(username, password);

        apiInterface.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                response.isSuccessful()
                if (response.code() == 200 && response.body() != null) {

                    Toast.makeText(LoginActivity.this, "Login  Successful", Toast.LENGTH_SHORT).show();
                    TokenManager.saveToken(LoginActivity.this, response.body().getToken());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
    }
}