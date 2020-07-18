package com.example.aplikasiku.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.aplikasiku.R;
import com.example.aplikasiku.buku.Book;
import com.example.aplikasiku.buku.BookResult;
import com.example.aplikasiku.index.ContentBook;
import com.example.aplikasiku.service.AppService;
import com.example.aplikasiku.service.BookApiService;
import com.example.aplikasiku.utility.DialogUtility;
import com.example.aplikasiku.utility.RetrofitUtility;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class IsiFragment extends Fragment {
    public static final int PICK_IMAGE = 1;
    private String base64Image;
    private String TAG = "Dialog Fragment";
    private View view;
    private ImageView imageView;
    private EditText inputJudul, inputPenulis, inputPenerbit, inputTahun, inputHarga;
    private Retrofit retrofit;
    private Button btnChoose;
    private Button btnUpload;
    private Button btnDelete;
    private AlertDialog dlg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_isi, container, false);

        getData();
        initview();

        return view;
    }

    private void initview() {

        inputJudul = view.findViewById(R.id.inputJudul);
        inputPenulis = view.findViewById(R.id.inputPenulis);
        inputPenerbit = view.findViewById(R.id.inputPenerbit);
        imageView = view.findViewById(R.id.imgChoose);
        inputTahun = view.findViewById(R.id.inputTahun);
        inputHarga = view.findViewById(R.id.inputHarga);
        btnChoose = view.findViewById(R.id.btnChoose);
        btnUpload = view.findViewById(R.id.btnUpload);
        btnDelete = view.findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBuku();
//            shoConfirmDialog(R.raw.delete, "Delete Data Buku Ini..?", getContext());
                Toast.makeText(getContext(), "Delete Buku ini?", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "save ");
                Toast.makeText(getContext(), "Upload Buku ini?", Toast.LENGTH_SHORT).show();
                sendData(inputJudul.getText().toString(),
                        inputPenulis.getText().toString(),
                        inputPenerbit.getText().toString(),
                        inputTahun.getText().toString(),
                        inputHarga.getText().toString());

            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    private void getData() {

//        DialogUtility.showDialog(R.raw.plane, "Loading : Get Data Buku", getContext());
        retrofit = RetrofitUtility.initialieRetrofit();

        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<BookResult> result = apiService.getBookById(AppService.getToken(), AppService.getIdBuku());
        result.enqueue(new Callback<BookResult>() {
            @Override
            public void onResponse(Call<BookResult> call, Response<BookResult> response) {
//                DialogUtility.closeAllDialog();
                if (response.body().getSuccess()) {

                    Toast.makeText(getContext(), "Tampil Buku ", Toast.LENGTH_SHORT).show();

                    String judul = response.body().getRecord().getJudul() != null ? response.body().getRecord().getJudul() : "";
                    String penulis = response.body().getRecord().getPenulis() != null ? response.body().getRecord().getPenulis() : "";
                    String penerbit = response.body().getRecord().getPenerbit() != null ? response.body().getRecord().getPenerbit() : "";
                    String tahun = response.body().getRecord().getTahun() > 0 ? String.valueOf(response.body().getRecord().getTahun()) : "";
                    String harga = response.body().getRecord().getHarga() > 0 ? String.valueOf(response.body().getRecord().getHarga()) : "";

                    Log.e(TAG, "judul: " + judul);
                    Log.e(TAG, "penulis: " + penulis);
                    Log.e(TAG, "penerbit: " + penerbit);
                    Log.e(TAG, "tahun: " + tahun);
                    Log.e(TAG, "harga: " + harga);

                    inputJudul.setText(judul);
                    inputPenulis.setText(penulis);
                    inputPenerbit.setText(penerbit);
                    inputTahun.setText(tahun);
                    inputHarga.setText(harga);
                    setImageThumb(response.body().getRecord().getThumb());
                } else {
                    Toast.makeText(getContext(), "Error : ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookResult> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Error : ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFormDisable(boolean value) {
        inputJudul.setEnabled(value);
        inputTahun.setEnabled(value);
        inputHarga.setEnabled(value);
        inputPenerbit.setEnabled(value);
        inputPenulis.setEnabled(value);
    }

    private Bitmap setImageThumb(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
        return decodedByte;
    }

    private void deleteBuku() {
        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<ApiResponse> result = apiService.deleteBook(AppService.getToken(), AppService.getIdBuku());
        result.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e(TAG, "onResponse: " + response.body().toString());

                if (response.body().isSuccess()) {
                    Toast.makeText(getContext(), " Success Delete Data ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error : ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Error : ", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            InputStream imageStream;
            String encodedImage = "";

            imageView.getLayoutParams().height = 400;
            imageView.getLayoutParams().width = 300;

            try {
                imageStream = getContext().getContentResolver().openInputStream(uri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                encodedImage = encodedImage + encodeImage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            base64Image = encodedImage;
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }

    private void sendData(String judul, String penulis, String penerbit, String tahun, String harga) {

        Book book = new Book();
        book.setHarga(Integer.valueOf(harga));
        book.setId(AppService.getIdBuku());
        book.setJudul(judul);
        book.setPenulis(penulis);
        book.setPenerbit(penerbit);
        book.setTahun(Integer.valueOf(tahun));
        book.setThumb(base64Image);

        Log.e(TAG, "sendData: " + book.toString());

        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<ApiResponse> result = apiService.updateBook(AppService.getToken(), book);
        result.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                Log.e(TAG, "onResponse: " + response.body().toString());



                if (response.body().isSuccess()) {
                    Toast.makeText(getContext(), " Success Edit Data ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error : ", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Error : ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}