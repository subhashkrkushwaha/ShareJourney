package com.example.studnetsharejourney.API;

import com.example.studnetsharejourney.Entity.JournalResponse;
import com.example.studnetsharejourney.Entity.LoginRequest;
import com.example.studnetsharejourney.Entity.LoginResponse;
import com.example.studnetsharejourney.Entity.SignupRequest;
import com.example.studnetsharejourney.Entity.SignupResponse;
import com.example.studnetsharejourney.Entity.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ApiInterface {


    // ✅ Public API - Health check (no auth)
    @GET("auth/health-check")
    Call<String> healthCheck();
    //Authentication
    @POST("auth/register")
    Call<SignupResponse> signup(@Body SignupRequest signupRequest);
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    // ✅ Get all journals of logged-in user
    @GET("api/journals/all")
    Call<List<JournalResponse>> getAllJournals(@Header("Authorization") String bearerToken);

    // ✅ Save a journal entry
    @POST("api/journals/create")
    Call<JournalResponse> saveJournal(@Header("Authorization") String bearerToken, @Body JournalResponse journal);

    // ✅ Get journal by ID
    @GET("/journal/id/{id}")
    Call<JournalResponse> getJournalById(@Header("Authorization") String bearerToken, @Path("id") int journalId);

    // ✅ Update journal by ID
    @PUT("/journal/id/{id}")
    Call<JournalResponse> updateJournal(@Header("Authorization") String bearerToken,
                                        @Path("id") int journalId,
                                        @Body JournalResponse journal);

}

