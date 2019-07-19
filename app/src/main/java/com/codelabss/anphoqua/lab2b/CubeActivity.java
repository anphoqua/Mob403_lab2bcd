package com.codelabss.anphoqua.lab2b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CubeActivity extends AppCompatActivity {

    EditText edt_edge;
    Button btn_result;
    TextView tv_result;

    public static final String SERVER_NAME = "http://192.168.64.2/mob403lab2/cubePOST.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube);

        edt_edge = findViewById(R.id.edgeID);
        btn_result = findViewById(R.id.btnCubeResultID);
        tv_result = findViewById(R.id.cubeResultID);

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invoke AsynTask
                String edge = edt_edge.getText().toString().trim();
                VolumeOfCube volumeOfCube = new VolumeOfCube(CubeActivity.this, edge, tv_result);
                volumeOfCube.execute();

            }
        });

    }
}
