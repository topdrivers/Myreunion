package com.example.myreu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myreu.Models.Meeting;
import com.example.myreu.Utils.ItemClickSupport;
import com.example.myreu.holder.Adapter;
import com.example.myreu.service.MeetingAdded;
import com.example.myreu.service.MeetingGenerator;
import com.example.myreu.service.RoomGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.SerializablePermission;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recycler_view) RecyclerView recyclerView;

    private List<Meeting> meetingList;
    private Adapter adapter;
    //private MeetingAdded meetingAdded;

    @BindView(R.id.main_activity_add_button)
    FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        new RoomGenerator();

        this.configureRecyclerView();
        this.configureOnClickRecyclerView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void configureRecyclerView() {
        // 3.1 - Reset list
       // this.meetingList = MeetingGenerator.MEETING_LIST;
        // 3.2 - Create adapter passing the list of users
        MeetingAdded meetingAdded;
        meetingAdded = new MeetingAdded();

        this.adapter = new Adapter(meetingAdded.getMeetings());
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // 1 - Configure item click on RecyclerView
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.activity_main_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Meeting meeting = adapter.getMeeting(position);
                        Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                        Bundle bundle = new Bundle();
                       // bundle.putString("userSelected", meeting.toString());
                        //bundle.putSerializable("userSelected",  meeting.toString());
                        bundle.putInt("id",meeting.getId());

                        //bundle.putSerializable("userSelected", meeting.getParticipants());
                        //bundle.putSerializable("userSelected", meeting.getRoom().toString());
                        //String[] value = {meeting.getRoom(), meeting.getName()};
                        //bundle.putStringArray();


                        myIntent.putExtras(bundle);

                        MainActivity.this.startActivity(myIntent);
                        Log.e("TAG", "Position : "+position);
                    }
                });
    }
}