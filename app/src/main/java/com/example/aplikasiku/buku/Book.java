package com.example.aplikasiku.buku;

public class Book {
    private int id;
    private String judul;
    private String penerbit;
    private String penulis;
    private int harga;
    private int userid;
    private int tahun;
    private String thumb;


    public Book() {
    }

    public Book(String judul, String penulis, String penerbit) {
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
    }

    public Book(String judul, String penerbit, String penulis, int harga, int userid, String thumb, int tahun) {
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
        this.harga = harga;
        this.userid = userid;
        this.thumb = thumb;
        this.tahun = tahun;
    }

    public Book(int id, String judul, String penerbit, String penulis, int harga, int userid) {
        this.id = id;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
        this.harga = harga;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", judul='" + judul + '\'' +
                ", penerbit='" + penerbit + '\'' +
                ", penulis='" + penulis + '\'' +
                ", harga=" + harga +
                ", userid=" + userid +
                ", tahun=" + tahun +
                ", thumb='" + thumb + '\'' +
                '}';
    }
}
