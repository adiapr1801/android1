package com.example.aplikasiku.index;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.aplikasiku.R;
import com.example.aplikasiku.login.UserApiService;
import com.example.aplikasiku.signup.SignupBody;
import com.example.aplikasiku.signup.SignupResult;
import com.example.aplikasiku.utility.RetrofitUtility;
import com.example.aplikasiku.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Signup extends Activity {
    private Retrofit retrofit;
    private AwesomeValidation awesomeValidation;

    EditText username, Email, password, name;
    Spinner listitem;
    RadioButton radioButton, radioButton2;
    Button button;
    private boolean active;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Utility.askPermission(this);
        retrofit = RetrofitUtility.initialieRetrofit();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        listitem = findViewById(R.id.listItem);
        radioButton = findViewById(R.id.salah);
        radioButton2 = findViewById(R.id.betul);
        button = findViewById(R.id.signup);

        awesomeValidation.addValidation(this, R.id.username, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.usernameerror);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passwordeerror);
        awesomeValidation.addValidation(this, R.id.name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()){
                    List<String>roles1 = new ArrayList<>();
                    roles1.add(listitem.getSelectedItem().toString());
                    signupSubmit(username.getText().toString(), password.getText().toString(), Email.getText().toString(), name.getText().toString(),roles1, active );

                }else{
                    Toast.makeText(getApplicationContext(),"Signup Gagal", Toast.LENGTH_SHORT);
                }
            }
        });


    }
    private void signupSubmit(String username, String password, String email, String name, List<String> roles, boolean active) {
        SignupBody signupBody = new SignupBody(username, password, email, name,roles, active);

        UserApiService apiService = retrofit.create(UserApiService.class);
        Call<SignupResult> result = apiService.signupUser(signupBody);

        result.enqueue(new Callback<SignupResult>() {
            @Override
            public void onResponse(Call<SignupResult> call, Response<SignupResult> response) {
                boolean success = response.body().isSuccess();
                try {
                    if (response.body().isSuccess()) {
                        Log.e("TAG", "---SELAMAT ANDA TELAH TERDAFTAR, SILAHKAN MASUK UNTUK MELANJUTKAN---");
                        Log.e("TAG", "| " + response.body().getRecord() +" |");
                        Toast.makeText(Signup.this,  "Signup berhasil :" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        Intent LoginIntent = new Intent(Signup.this, Lo
//                        ginActivity.class);
//                        startActivity(LoginIntent);
//                        finish();
                    } else {
                        Toast.makeText(Signup.this,  "Signup Gagal :" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<SignupResult> call, Throwable t) {

            }
        });

    }
}