package com.team2.centennial_helper.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.team2.centennial_helper.R;
import com.team2.centennial_helper.pojo.TicketInfo;
import com.team2.centennial_helper.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateTicketActivity extends Activity {

    private TextInputEditText mStudentNumber, mProgramName, mCourseName, mDiscription;
    private Spinner mOption;
    private MaterialButton mSubmit;
    private String key = "";
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);
        initUi();

        Intent intent = getIntent();
        TicketInfo ticketInfo = (TicketInfo) intent.getSerializableExtra("ticket_info");

        if(ticketInfo != null){

            mStudentNumber.setText(ticketInfo.getStudentNo());
            mDiscription.setText(ticketInfo.getDiscription());
            mProgramName.setText(ticketInfo.getProgramName());
            mCourseName.setText(ticketInfo.getCourseName());
            mOption.setSelection(ticketInfo.getTicketType());
            key = ticketInfo.getTicketKey();

        }
    }

    private void initUi() {

        mStudentNumber = findViewById(R.id.ctStudentNum);
        mDiscription = findViewById(R.id.ctDisc);
        mProgramName = findViewById(R.id.ctProgramName);
        mCourseName = findViewById(R.id.ctCourseName);

        mOption = findViewById(R.id.ctCategory);
        List<String> options = new ArrayList<String>();
        options.add("Finance");
        options.add("Time Table Change");
        options.add("Add/Drop a course");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mOption.setAdapter(dataAdapter);

        mSubmit = findViewById(R.id.ctCreateTicket);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TicketInfo ticketInfo = new TicketInfo();
                ticketInfo.setStudentNo(mStudentNumber.getText().toString());
                ticketInfo.setDiscription(mDiscription.getText().toString());
                ticketInfo.setProgramName(mProgramName.getText().toString());
                ticketInfo.setCourseName(mCourseName.getText().toString());
                ticketInfo.setTicketType(mOption.getSelectedItemPosition());
                ticketInfo.setUid(FirebaseAuth.getInstance().getUid());

                if(key.equals("")){
                    key = Util.database.getReference("/tickets/").push().getKey();
                }

                ticketInfo.setTicketKey(key);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                ticketInfo.setTime(formatter.format(date));


                Util.database.getReference("/tickets/"+key).setValue(ticketInfo)
                        .addOnCompleteListener(CreateTicketActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                startActivity(new Intent(CreateTicketActivity.this, DisplayTickets.class));
                                Toast.makeText(CreateTicketActivity.this, "Ticket Added", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });
    }


}
