package com.surverwisepocketexampe;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.surverwisepocketexampe.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "xml_survey.xml";
    private ActivityMainBinding binding;
    private String xmlFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        try {
            this.readXmlFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean readXmlFile() throws IOException {

        BufferedReader reader = null;
        boolean readResultAction = false;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(this.FILE_NAME), "UTF-8"));

            // do reading, usually loop until end of file reading
            this.xmlFile = "";
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                xmlFile += mLine;
            }
            readResultAction = true;
        } catch (IOException e) {
            //log the exception
            e.printStackTrace();
            readResultAction = false;

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                    e.printStackTrace();
                    readResultAction = false;
                }
            }
            return readResultAction;
        }
    }
}