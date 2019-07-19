package com.codelabss.anphoqua.lab2b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RetangleActivity extends AppCompatActivity {
    EditText edt_width, edt_height;
    Button btn_result;
    TextView tv_result;

    public static final String SERVER_NAME = "http://192.168.64.2/mob403lab2/retanglePOST.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retangle);
        edt_width = findViewById(R.id.widthID);
        edt_height = findViewById(R.id.heightID);
        btn_result = findViewById(R.id.btnRetangleResultID);
        tv_result = findViewById(R.id.retangleResultID);

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invoke AsyncTask
                String width = edt_width.getText().toString().trim();
                String height = edt_height.getText().toString().trim();
                PerimeterAreaOfRetangle perimeterAreaOfRetangle =
                        new PerimeterAreaOfRetangle(RetangleActivity.this, width, height,tv_result);
                perimeterAreaOfRetangle.execute();
            }
        });

    }
}
