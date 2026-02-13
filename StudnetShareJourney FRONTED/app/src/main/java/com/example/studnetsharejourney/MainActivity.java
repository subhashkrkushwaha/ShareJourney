package com.example.studnetsharejourney;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.studnetsharejourney.Fragment.AddFragment;
import com.example.studnetsharejourney.Fragment.FevoriteFragment;
import com.example.studnetsharejourney.Fragment.HomeFragment;
import com.example.studnetsharejourney.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
         BottomNavigationView  bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if(id == R.id.home){
                    loadFragment(new HomeFragment(),0);
                } else if (id == R.id.add) {
                    loadFragment(new AddFragment(),1);
                } else if (id == R.id.fevorite) {
                    loadFragment(new FevoriteFragment(),1);
                }else {
                    loadFragment(new ProfileFragment(),1);
                }
                return  true;
            }
        });
         bottomNavigationView.setSelectedItemId(R.id.home);
    }
public  void loadFragment(Fragment fragment, int flag){
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft=fm.beginTransaction();
    if(flag == 0){
        ft.add(R.id.container,fragment);
    }else{
        ft.replace(R.id.container,fragment);
    }
    ft.commit();
}
}