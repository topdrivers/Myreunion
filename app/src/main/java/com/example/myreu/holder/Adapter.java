package com.example.myreu.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreu.Models.Meeting;
import com.example.myreu.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Meeting> meetingList;

    public Adapter(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updateWithMeeting(meetingList.get(position));

    }

    @Override
    public int getItemCount() {
        return meetingList.size();
    }

    public Meeting getMeeting(int position) {
        return this.meetingList.get(position);
    }
}
