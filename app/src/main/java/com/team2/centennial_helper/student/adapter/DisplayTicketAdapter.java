package com.team2.centennial_helper.student.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team2.centennial_helper.R;
import com.team2.centennial_helper.pojo.TicketInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DisplayTicketAdapter extends RecyclerView.Adapter<DisplayTicketAdapter.ViewHolder> {

    private List<TicketInfo> ticketInfos = new ArrayList<>();
    private Context context;

    public DisplayTicketAdapter(Context context,List<TicketInfo> ticketInfos){
        this.ticketInfos = ticketInfos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.display_ticket,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TicketInfo ticketInfo = ticketInfos.get(position);
        holder.ticketNumber.setText(ticketInfo.getTicketKey());
        holder.time.setText(ticketInfo.getTime());

        if(ticketInfo.getTicketType() == 0){
            holder.category.setText("Finance");
        }
        else if(ticketInfo.getTicketType() == 1){
            holder.category.setText("Time Table Change");
        }
        else if(ticketInfo.getTicketType() == 2){
            holder.category.setText("Add/Drop a course");
        }
    }

    @Override
    public int getItemCount() {
        return ticketInfos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView ticketNumber, time, category;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ticketNumber = itemView.findViewById(R.id.displayTicketNumber);
            time = itemView.findViewById(R.id.displayTicketTime);
            category = itemView.findViewById(R.id.displayCategory);
        }
    }
}
