package com.team2.centennial_helper.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.team2.centennial_helper.CreateRequestActivity;
import com.team2.centennial_helper.R;
import com.team2.centennial_helper.common.LoginActivity;
import com.team2.centennial_helper.employee.EmployeeHomeActivity;

public class StudentHomeActivity extends Activity {
    private Button logout, createRequest, ticketHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        createRequest = findViewById(R.id.btn_createRequest);
        ticketHistory = findViewById(R.id.btn_TicketHistory);

        logout = findViewById(R.id.btn_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentHomeActivity.this,LoginActivity.class));
                finish();
            }
        });

        createRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, CreateTicketActivity.class));
            }
        });

        ticketHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHomeActivity.this, DisplayTickets.class));
            }
        });

    }

}
