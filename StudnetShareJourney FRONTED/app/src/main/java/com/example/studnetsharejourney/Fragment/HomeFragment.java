package com.example.studnetsharejourney.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studnetsharejourney.API.ApiInterface;
import com.example.studnetsharejourney.API.RetrofitClient;
import com.example.studnetsharejourney.Adptar.ContentRecyclerViewAdapter;
import com.example.studnetsharejourney.Entity.JournalResponse;
import com.example.studnetsharejourney.R;
import com.example.studnetsharejourney.Utiles.TokenManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContentRecyclerViewAdapter adapter;
    private List<JournalResponse> contentItems = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ContentRecyclerViewAdapter(contentItems,getActivity());
        recyclerView.setAdapter(adapter);

        // 🔑 Replace this with your JWT token from Login
        String token = TokenManager.getToken(requireContext());
        String bearerToken = "Bearer " + token;
        fetchJournals(bearerToken);

        // Sample Data
      /*  List<JournalResponse> contentItems = new ArrayList<>();
        contentItems.add(new JournalResponse("Sonu Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("ram Journey","This is a sample content for Ram Journey " +
                "This is a sample content for Ram Journey " + "This is a sample content for Ram Journey"+getString(R.string.contents)));
        contentItems.add(new JournalResponse("Sanker Journey"," This is a sample content for Sanker Journey "+getString(R.string.contents)));
        contentItems.add(new JournalResponse("Mohan Journey","Mohan is  flee not good  boy"+getString(R.string.contents)));
        contentItems.add(new JournalResponse("Shaashi Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("Mohan Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("Rahul Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("Sankaer Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("Pankaj Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("Guptam Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("Anish Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("Angad Journey",getString(R.string.contents)));
        contentItems.add(new JournalResponse("ubhash Journey",getString(R.string.contents)));
*/

        // Adapter setup
/*        ContentRecyclerViewAdapter adapter = new ContentRecyclerViewAdapter(contentItems);
        recyclerView.setAdapter(adapter);*/
    }
    private void fetchJournals(String bearerToken) {
        ApiInterface apiService = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Call<List<JournalResponse>> call = apiService.getAllJournals(bearerToken);
        call.enqueue(new retrofit2.Callback<List<JournalResponse>>() {
            @Override
            public void onResponse(Call<List<JournalResponse>> call, retrofit2.Response<List<JournalResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    contentItems.clear();
                    contentItems.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(requireContext(), "Error: " + response.code() + " " + response.message(), Toast.LENGTH_LONG).show();
                    // Handle error (e.g., unauthorized, no data)
                }
            }

            @Override
            public void onFailure(Call<List<JournalResponse>> call, Throwable t) {
                // Handle failure (no internet, server down, etc.)
            }
        });
    }
}