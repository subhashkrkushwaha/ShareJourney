package com.example.studnetsharejourney.Entity;
import java.util.List;

public class UserResponse {
    private Integer id;
    private String userName;
    private String password;
    private List<String> roles;
    private List<JournalResponse> journalEntityList;


    // Add this constructor
    public UserResponse(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<JournalResponse> getJournalEntityList() {
        return journalEntityList;
    }

    public void setJournalEntityList(List<JournalResponse> journalEntityList) {
        this.journalEntityList = journalEntityList;
    }
}

