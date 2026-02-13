package com.example.studnetsharejourney.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.studnetsharejourney.API.ApiInterface;
import com.example.studnetsharejourney.Entity.SignupRequest;
import com.example.studnetsharejourney.Entity.SignupResponse;
import com.example.studnetsharejourney.R;
import com.example.studnetsharejourney.API.RetrofitClient;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;


public class SingupActivity extends AppCompatActivity {

    AppCompatButton btnSignup;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_singup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(v -> signupUser());
    }
    private void signupUser() {
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        SignupRequest signupRequest = new SignupRequest(etUsername.getText().toString(), etPassword.getText().toString());

        apiInterface.signup(signupRequest).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    String msg = response.body().getMessage();
                    if (msg == null || msg.trim().isEmpty()) {
                        msg = "Signup successful";
                    }
                    Toast.makeText(SingupActivity.this, msg, Toast.LENGTH_SHORT).show();
                    finish(); // Go back to login
                } else {
                    Toast.makeText(SingupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(SingupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("singup",t.getMessage());
            }
        });
    }
}
