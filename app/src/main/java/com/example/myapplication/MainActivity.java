package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.model.Photographer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    static public Photographer photographer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photographer= (Photographer) getIntent().getSerializableExtra("Photographer");
        TextView name=findViewById(R.id.id_name);
        TextView location=findViewById(R.id.location);
        ImageView propic=findViewById(R.id.profilepic);

        name.setText(photographer.getPhotographerName());
        location.setText(photographer.getLocation());
        Picasso.with(this).load(photographer.getProfileImage()).into(propic);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_wedding, R.id.navigation_graduation, R.id.navigation_birthdate, R.id.navigation_other)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}