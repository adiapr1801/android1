package com.example.aplikasiku.index;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aplikasiku.R;
import com.example.aplikasiku.dialog.Loading2;
import com.example.aplikasiku.fragment.BawahFragment;
import com.example.aplikasiku.fragment.HomeFragment;
import com.example.aplikasiku.fragment.IsiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContentBook extends AppCompatActivity {
    private BottomNavigationView btmNavigation;
    private TextView textJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_book);

        initView();

        openHomeFragment();

    }

    private void initView() {
        btmNavigation = findViewById(R.id.btmNavigation);
        textJudul = findViewById(R.id.textJudul);

        btmNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        final int previousItem = btmNavigation.getSelectedItemId();
                        final int nextItem = item.getItemId();
                        if (previousItem != nextItem) {
                            switch (nextItem) {
                                case R.id.home:
                                    selectedFragment("home");
                                    break;
                                case R.id.add:
                                    selectedFragment("add");
                                    break;
                            }
                        }
                        return true;
                    }
                }
        );

        btmNavigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Log.e("TAG", "onNavigationItemReselected: "+ item );
            }
        });
    }

    private void selectedFragment(String id) {

        Log.e("TAG", "selectedFragment: " + id);
        if (id.equals("home")) {

            Log.e("TAG", "halaman home");
            openHomeFragment();
        } else {
            Log.e("TAG", "halaman add ");
            openAddFragment();
        }

    }

    public void openHomeFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment strCode = new HomeFragment();
        fragmentTransaction.replace(R.id.content, strCode, "home fragment");
        fragmentTransaction.commit();
    }


    public void openAddFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BawahFragment bookAdd = new BawahFragment();
        fragmentTransaction.replace(R.id.content, bookAdd, "book fragment");
        fragmentTransaction.commit();
    }

    public void openIsiFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        IsiFragment bookView = new IsiFragment();
        fragmentTransaction.replace(R.id.content, bookView, "book fragment");
        fragmentTransaction.commit();
    }

}