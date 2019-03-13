package com.team2.centennial_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.btn_login2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStudentHomeActivity();
            }
        });
    }

    public void openStudentHomeActivity(){
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }

}
