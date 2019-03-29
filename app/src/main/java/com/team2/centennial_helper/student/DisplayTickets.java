package com.team2.centennial_helper.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

public class DisplayTickets extends Activity {

    private RecyclerView recyclerView;
    private List<TicketInfo> ticketInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tickets);

        recyclerView = findViewById(R.id.displayTicketRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DisplayTickets.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DisplayTicketAdapter displayTicketAdapter = new DisplayTicketAdapter(this, ticketInfos);
        recyclerView.setAdapter(displayTicketAdapter);

        Util.database.getReference("/tickets/").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("KEY:",dataSnapshot.getValue().toString());

                for(final DataSnapshot valueSnapshot : dataSnapshot.getChildren()){
                    Util.database.getReference("/tickets/"+valueSnapshot.getKey())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Log.e("SNAPSHOT",dataSnapshot.getValue().toString());
                                    TicketInfo ticketInfo = dataSnapshot.getValue(TicketInfo.class);
                                    /*if(ticketInfo.getUid().equals(FirebaseAuth.getInstance().getUid())){

                                    }*/
                                    ticketInfos.add(ticketInfo);
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
