package com.example.studnetsharejourney.Fragment;

import static android.content.Context.MODE_PRIVATE;

import static com.example.studnetsharejourney.Utiles.TokenManager.clearToken;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.studnetsharejourney.Activity.LoginActivity;
import com.example.studnetsharejourney.R;

import java.util.Objects;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find the RelativeLayout by ID
        RelativeLayout logoutLayout = view.findViewById(R.id.Logout);

        // Set onClickListener
        logoutLayout.setOnClickListener(v -> {
            if(getActivity() != null) {
                // Handle logout click action

                // 2️⃣ Clear login flag
                clearToken(getActivity());
                // 2️⃣ Clear login flag
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("flag", true);
                editor.apply();

                // 3️⃣ Go to Login screen
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
            // Example: Navigate to login screen or clear session
        });
        // Inflate the layout for this fragment
        return view;
    }
}