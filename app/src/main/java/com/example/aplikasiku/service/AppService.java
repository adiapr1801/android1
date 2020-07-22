package com.example.aplikasiku.service;

public class AppService {
    private static String token;
    private static int idBuku;


    public AppService() {
    }


    public static int getIdBuku() {
        return idBuku;
    }

    public static void setIdBuku(int idBuku) {
        AppService.idBuku = idBuku;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        AppService.token = token;
    }
}
