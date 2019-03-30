package com.team2.centennial_helper.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.team2.centennial_helper.R;
import com.team2.centennial_helper.pojo.TicketInfo;
import com.team2.centennial_helper.student.adapter.DisplayTicketAdapter;
import com.team2.centennial_helper.util.Util;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DisplayTickets extends Activity {

    private TextView mLabel;
    private RecyclerView recyclerView;
    private List<TicketInfo> ticketInfos = new ArrayList<>();
    private int departmentType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tickets);

        mLabel = findViewById(R.id.noTicketsMsg);
        recyclerView = findViewById(R.id.displayTicketRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayTickets.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DisplayTicketAdapter displayTicketAdapter = new DisplayTicketAdapter(this, ticketInfos);
        recyclerView.setAdapter(displayTicketAdapter);

        try {
            Intent intent = getIntent();
            departmentType = intent.getIntExtra("department_type", -1);

        } catch (Exception e) {
            departmentType = -1;
        }

        Util.database.getReference("/tickets/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("KEY:", dataSnapshot.getValue().toString());

                for (final DataSnapshot valueSnapshot : dataSnapshot.getChildren()) {
                    Util.database.getReference("/tickets/" + valueSnapshot.getKey())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Log.e("SNAPSHOT", dataSnapshot.getValue().toString());
                                    TicketInfo ticketInfo = dataSnapshot.getValue(TicketInfo.class);

                                    if (departmentType != -1) {
                                        if (ticketInfo.getTicketType() == departmentType) {
                                            ticketInfos.add(ticketInfo);
                                        }
                                    } else {

                                        if (ticketInfo.getUid().equals(FirebaseAuth.getInstance().getUid())) {
                                            ticketInfos.add(ticketInfo);
                                        }
                                        if (ticketInfos.size() == 0) {
                                            recyclerView.setVisibility(View.GONE);
                                            mLabel.setVisibility(View.VISIBLE);
                                        } else {
                                            recyclerView.setVisibility(View.VISIBLE);
                                            mLabel.setVisibility(View.GONE);
                                        }

                                    }

                                    recyclerView.getAdapter().notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(DisplayTickets.this, "Connection Error", Toast.LENGTH_SHORT).show();
                                }
                            });

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DisplayTickets.this, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
