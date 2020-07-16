package com.example.aplikasiku.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasiku.dialog.Loadig;
import com.example.aplikasiku.index.ContentBook;
import com.example.aplikasiku.R;
import com.example.aplikasiku.index.Signup;
import com.example.aplikasiku.service.AppService;
import com.example.aplikasiku.utility.RetrofitUtility;
import com.example.aplikasiku.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Retrofit retrofit;

    @Override
    protected  void  onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );
        Utility.askPermission(this);
        retrofit= RetrofitUtility.initialieRetrofit();
        final EditText usernamee,passwordd;
        Button buttonLogin;
        TextView daftar;
        buttonLogin = findViewById(R.id.buttonLogin);
        usernamee = findViewById(R.id.username);
        passwordd = findViewById(R.id.password);
        daftar = findViewById(R.id.daftar);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                // action
                Intent intent = new Intent(LoginActivity.this, Signup.class);
                startActivity(intent);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernamee.getText().toString().length() == 0) {
                    usernamee.setError("Username harus diisi");
                } else if (passwordd.getText().toString().length() == 0) {
                    passwordd.setError("Password harus diisi");
                }else{
                    submitLogin(usernamee.getText().toString(),passwordd.getText().toString());
                }
            }
        });
    }


    private  void submitLogin(String username, String password){

        LoginBody loginBody = new LoginBody (username, password);

        UserApiService apiService =retrofit.create(UserApiService.class);
        Call<LoginResult> result =apiService.getResultInfo(loginBody);
        result.enqueue(new Callback<LoginResult>(){
            @Override
            public void  onResponse(Call<LoginResult> call , Response<LoginResult> response){
                try{
                    if(response.body().isSuccess()){
//                        Log.e("TAG", "|    SELAMAT ANDA BERHASIL LOGIN");
//                        Log.e("TAG", "|    Token anda adalah: ");
//                        Log.e("TAG", "|    "+response.body().getToken());
                        Toast.makeText(getApplicationContext(),"SELAMAT DATANG", Toast.LENGTH_SHORT).show();
                        AppService.setToken("Bearer " + response.body().getToken());
                        startActivity(new Intent(LoginActivity.this, ContentBook.class));
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"USERNAME DAN PASSWORD SALAH", Toast.LENGTH_SHORT).show();

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<LoginResult> call,Throwable t){
                t.printStackTrace();
            }
        });
    }
}
