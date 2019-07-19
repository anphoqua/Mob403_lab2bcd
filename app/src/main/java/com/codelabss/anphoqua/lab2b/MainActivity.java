package com.codelabss.anphoqua.lab2b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout goRetangle, goCube, goQuadratic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goRetangle = findViewById(R.id.retangleID);
        goCube = findViewById(R.id.cudeID);
        goQuadratic = findViewById(R.id.algebra);

        goRetangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_goRetangle = new Intent(MainActivity.this, RetangleActivity.class);
                startActivity(i_goRetangle);
            }
        });

        goCube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_goCube = new Intent(MainActivity.this, CubeActivity.class);
                startActivity(i_goCube);
            }
        });

        goQuadratic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_goQuadratic = new Intent(MainActivity.this, QuadraticActivity.class);
                startActivity(i_goQuadratic);
            }
        });
    }
}
