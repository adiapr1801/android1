package com.example.aplikasiku.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.aplikasiku.R;
import com.example.aplikasiku.buku.Book;
import com.example.aplikasiku.login.LoginResult;
import com.example.aplikasiku.service.AppService;
import com.example.aplikasiku.service.BookApiService;
import com.example.aplikasiku.utility.RetrofitUtility;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BawahFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private String TAG = "bawahfragment";
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int PICK_IMAGE = 1;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private String base64Image = "";
    private View view;
    private EditText judulbuku, penulis, penerbit, tahun, harga;
    private ImageView setImageURI;
    private String bas64Image;
    Retrofit retrofit;
    Button addimage;
    Button send;
    private ImageView imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void initRetrofit() {
        retrofit = RetrofitUtility.initialieRetrofit()  ;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initRetrofit();
        view = inflater.inflate(R.layout.fragment_bawah, container, false);
        judulbuku = view.findViewById(R.id.judul);
        penulis = view.findViewById(R.id.penulis);
        penerbit = view.findViewById(R.id.penerbit);
        tahun = view.findViewById(R.id.tahun);
        harga = view.findViewById(R.id.harga);
        addimage = view.findViewById(R.id.btnGallery);
        send = view.findViewById(R.id.buttonadd);
        imageView = view.findViewById(R.id.image);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Pilih Gambar Buku", Toast.LENGTH_SHORT).show();
                imageChooser();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatejudul() | !validatepenulis() | !validatepenerbit() | !validatetahun() | !validateharga()) {
                    Toast.makeText(getActivity(), "Masukkan Data", Toast.LENGTH_SHORT).show();
                } else {
                    sendData(
                            judulbuku.getText().toString(),
                            penulis.getText().toString(),
                            penerbit.getText().toString(),
                            harga.getText().toString(),
                            tahun.getText().toString(),
                            base64Image
                    );
                }
            }
        });
        return view;
    }

    private void imageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "PILIH GAMBAR"), PICK_IMAGE);
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = data.getData();
        InputStream imageStream;
        String encodeImage = "";
        imageView.setImageURI(uri);
        try {
            imageStream = getContext().getContentResolver().openInputStream(uri);
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            encodeImage = encodeImage(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        base64Image = encodeImage;
    }


    private void sendData(String judul, String penulis, String penerbit, String tahun, String harga, String base64Image) {
        Book book = new Book();
        book.setHarga(Integer.valueOf(harga));
        book.setJudul(judul);
        book.setPenulis(penulis);
        book.setPenerbit(penerbit);
        book.setTahun(Integer.valueOf(tahun));
        book.setThumb(base64Image);

        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<ApiResponse> result = apiService.insertNewBook(AppService.getToken(), book);
        result.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.body().isSuccess()) {
                    Log.e("TAG", "ADD BOOK IS TRUE BRO");
                    Toast.makeText(getActivity(),"ADD BOOK IS TRUE BRO", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("TAG", "gagal menambah buku");
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private boolean validatejudul() {
        String judulinput = judulbuku.getText().toString().trim();
        if (judulinput.isEmpty()) {
            judulbuku.setError("judul diisi dahulu");
            return false;
        } else {
            judulbuku.setError(null);
            return true;
        }
    }

    private boolean validatepenulis() {
        String penulisinput = penulis.getText().toString().trim();
        if (penulisinput.isEmpty()) {
            penulis.setError("penulis diisi dahulu");
            return false;
        } else {
            penulis.setError(null);
            return true;
        }
    }

    private boolean validatepenerbit() {
        String penerbitinput = penerbit.getText().toString().trim();
        if (penerbitinput.isEmpty()) {
            penerbit.setError("penerbit diisi dahulu");
            return false;
        } else {
            penerbit.setError(null);
            return true;
        }
    }

    private boolean validatetahun() {
        String tahuninput = tahun.getText().toString().trim();
        if (tahuninput.isEmpty()) {
            tahun.setError("tahun diisi dahulu");
            return false;
        } else {
            tahun.setError(null);
            return true;
        }
    }

    private boolean validateharga() {
        String hargainput = harga.getText().toString().trim();
        if (hargainput.isEmpty()) {
            harga.setError("Harga diisi dahulu");
            return false;
        } else {
            harga.setError(null);
            return true;
        }
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }
}