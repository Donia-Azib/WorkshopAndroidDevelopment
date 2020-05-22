package com.example.workshopandroiddevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //1 etape
    private Button pressMe;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----2 la relation
        pressMe = findViewById(R.id.btn);

        //----3 l'action
        pressMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "MARI7BEE BIKOM EL YOUM M3ANA FI WORKSHOP 9BAL EL S7OUR !!! ", Toast.LENGTH_LONG).show();
            }
        });


    }
}
