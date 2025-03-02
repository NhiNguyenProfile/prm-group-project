package com.example.prm392_project.util;

public class SessionManager {

    private static SessionManager instance;

    private String userId;
    private boolean isLoggedIn;

    private SessionManager() {

    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setLogin(boolean isLoggedIn, String userId) {
        this.isLoggedIn = isLoggedIn;
        this.userId = userId;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getUserId() {
        return userId;
    }

    public void logout() {
        isLoggedIn = false;
        userId = null;
    }
}
