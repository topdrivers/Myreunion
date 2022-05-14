package com.example.myreu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.idling.CountingIdlingResource;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.myreu.Models.Meeting;
import com.example.myreu.di.DI;
import com.example.myreu.holder.Adapter;
import com.example.myreu.service.MeetingAdded;
import com.example.myreu.service.MeetingApiService;
import com.example.myreu.service.MeetingGenerator;
import com.google.android.material.textfield.TextInputLayout;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {


    @BindView(R.id.activity_details_date)    TextView detailsDate;
    @BindView(R.id.activity_details_participants)    TextView detailsParticipants;
    @BindView(R.id.activity_details_item_name)    TextView detailsName;
    @BindView(R.id.activity_details_room)    TextView detailsRoom;
    MeetingApiService meetingApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        this.configureEspressoIdlingResource();
        incrementIdleResource();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
       // Bundle bundle = intent.getExtras();
        //String bundle = intent.getStringExtra("userSelected");

        //Meeting name = (Meeting) bundle.getSerializable("userSelected");
        int id = bundle.getInt("id");
        //Adapter adapter = null;
        //Meeting meeting = adapter.getMeeting(id);
       // Meeting meeting = MeetingGenerator.MEETING_LIST.get(id);
        //Meeting meeting = MeetingGenerator.generateMeetings().get(id);

       //MeetingAdded meetingAdded = new MeetingAdded();
        Meeting meeting = new Meeting();
        meetingApiService = DI.getMeetingApiService();
        meeting = meetingApiService.getMeetings().get(id);

        //GithubUser user = (GithubUser) bundle.getSerializable("userSelected");
        Log.e("Name", "-------------Name------------- : "+meeting.getName());
        Log.e("Room", "-------------Room------------- : "+meeting.getRoom().getName());

        //Meeting user =   bundle.getString("userSelected");

        this.detailsName.setText(meeting.getName());
        this.detailsDate.setText(meeting.getStartMeeting().toString("dd/MM HH:mm"));
        //this.detailsDate.setInputType(meeting.getStartMeeting().getDayOfYear());
        this.detailsParticipants.setText(meeting.getParticipants());
        this.detailsRoom.setText(meeting.getRoom().getName());
        decrementIdleResource();


    }

    @VisibleForTesting
    public CountingIdlingResource getEspressoIdlingResourceForMainFragment() {
        return this.getEspressoIdlingResource();
    }
    // FOR TESTING
    @VisibleForTesting protected CountingIdlingResource espressoTestIdlingResource;

    @VisibleForTesting
    public CountingIdlingResource getEspressoIdlingResource() { return espressoTestIdlingResource; }

    @VisibleForTesting
    private void configureEspressoIdlingResource(){
        this.espressoTestIdlingResource = new CountingIdlingResource("Network_Call");
    }

    protected void incrementIdleResource(){
        if (BuildConfig.DEBUG) this.espressoTestIdlingResource.increment();
    }

    protected void decrementIdleResource(){
        if (BuildConfig.DEBUG) this.espressoTestIdlingResource.decrement();
    }
}