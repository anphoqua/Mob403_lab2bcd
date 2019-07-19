package com.codelabss.anphoqua.lab2b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuadraticActivity extends AppCompatActivity {
    EditText edt_unknown_a, edt_unknown_b, edt_unknown_c;
    Button btn_result;
    TextView tv_result;

    public static final String SERVER_NAME="http://192.168.64.2/mob403lab2/quadraticPOST.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic);
        edt_unknown_a = findViewById(R.id.unknownAID);
        edt_unknown_b = findViewById(R.id.unknownBID);
        edt_unknown_c = findViewById(R.id.unknownCID);
        btn_result = findViewById(R.id.btnQuadraticResultID);
        tv_result = findViewById(R.id.quadraticResultID);

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invoke AsyncTask
                String a = edt_unknown_a.getText().toString().trim();
                String b = edt_unknown_b.getText().toString().trim();
                String c = edt_unknown_c.getText().toString().trim();
                QuadraticEquation quadraticEquation = new QuadraticEquation(QuadraticActivity.this, a,b,c,tv_result );
                quadraticEquation.execute();
            }
        });
    }
}
