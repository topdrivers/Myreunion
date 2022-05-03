package com.example.myreu.holder;



import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreu.Models.Meeting;
import com.example.myreu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {

@BindView(R.id.activity_main_item_name )TextView textViewItemName;
    @BindView(R.id.activity_main_date )TextView textViewDate;
    @BindView(R.id.activity_main_participants )TextView textViewParticipants;
    @BindView(R.id.activity_main_room )TextView textViewRoom;
    @BindView(R.id.activity_main_delete_button ) ImageButton imageButtonDelete;
    @BindView(R.id.activity_main_image ) ImageView imageView;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void updateWithMeeting(Meeting meeting){
        this.textViewItemName.setText(meeting.getName());
        this.textViewDate.setText(meeting.getStartMeeting().toString("dd/MM HH:mm"));
        this.textViewParticipants.setText(meeting.getParticipants());
        this.textViewRoom.setText(meeting.getRoom().getName());
        this.imageView.setImageResource(meeting.getRoom().getRoomColor());

    }
}
