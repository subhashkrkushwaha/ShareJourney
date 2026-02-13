package com.example.studnetsharejourney.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studnetsharejourney.API.ApiInterface;
import com.example.studnetsharejourney.API.RetrofitClient;
import com.example.studnetsharejourney.Entity.JournalResponse;
import com.example.studnetsharejourney.R;
import com.example.studnetsharejourney.Utiles.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddFragment extends Fragment {

    private EditText titleEditText, contentEditText;
    private AppCompatButton postButton;

    public AddFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

         titleEditText = view.findViewById(R.id.title);
         contentEditText = view.findViewById(R.id.content);
         postButton = view.findViewById(R.id.post);
        postButton.setOnClickListener(v -> postJournal());

        return view;

    }
    private void postJournal() {
        String title = titleEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(requireContext(), "Title and Content cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get token
        String token = TokenManager.getToken(requireContext());
        String bearerToken = "Bearer " + token;

        JournalResponse journal = new JournalResponse(title, content);

        ApiInterface apiService = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<JournalResponse> call = apiService.saveJournal(bearerToken, journal);

        call.enqueue(new Callback<JournalResponse>() {
            @Override
            public void onResponse(@NonNull Call<JournalResponse> call, @NonNull Response<JournalResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(requireContext(), "Journal saved successfully", Toast.LENGTH_SHORT).show();
                    // Optionally clear the form
                    titleEditText.setText("");
                    contentEditText.setText("");
                } else {
                    Toast.makeText(requireContext(), "Error: " + response.code() + " " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JournalResponse> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}