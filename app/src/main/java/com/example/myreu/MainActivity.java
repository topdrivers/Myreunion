package com.example.myreu;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.idling.CountingIdlingResource;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;



import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;


import com.example.myreu.Models.Meeting;
import com.example.myreu.Utils.ItemClickSupport;
import com.example.myreu.Utils.ToastUtils;
import com.example.myreu.di.DI;
import com.example.myreu.holder.Adapter;
import com.example.myreu.service.MeetingAdded;
import com.example.myreu.service.MeetingApiService;
import com.example.myreu.service.MeetingGenerator;
import com.example.myreu.service.RoomGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import org.joda.time.DateTime;

import java.io.SerializablePermission;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    MeetingApiService meetingApiService;

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
        this.configureEspressoIdlingResource();

        new RoomGenerator();
        meetingApiService= DI.getMeetingApiService();
        this.configureToolbar();
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

    private void configureToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){

            case R.id.allMeeting:showAllMeetings();return true;
            case R.id.roomOrder: filterByRoom();return true;
            case R.id.dateIncreasing: filterByIncreasingDate();return true;
            case R.id.dateDecreasing: filterByDecreasingDate();return true;
            case R.id.byDate : configureDialogCalendar();return true;
            case R.id.eclair: filterItemByRoom("Eclair");return true;
            case R.id.paintsilvia:filterItemByRoom("Paintsilvia");return true;
            case R.id.pegasus:filterItemByRoom("Pegasus");return true;
            case R.id.andromede: filterItemByRoom("Andromède");return true;
            case R.id.quantum:filterItemByRoom("Quantum");return true;
            case R.id.trier: filterItemByRoom("Trier");return true;
            case R.id.vulton:filterItemByRoom("Vulton");return true;
            case R.id.sirius:filterItemByRoom("Sirius");return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showAllMeetings() {
        incrementIdleResource();
        initList(meetingApiService.getMeetings());

        meetingApiService.getMeetings();

        adapter.notifyDataSetChanged();

    }

    private void filterByDecreasingDate() {
        meetingApiService.getMeetingsDescendingDate();
        adapter.notifyDataSetChanged();
    }

    private void filterByIncreasingDate() {
        meetingApiService.getMeetingsAscendingDate();
        adapter.notifyDataSetChanged();
    }

    private void filterByRoom() {
        meetingApiService.getMeetingsRoom();
        adapter.notifyDataSetChanged();
    }

    private void filterItemByRoom(String salle) {
        /* Filtre par salle */
        boolean nothing = true;
        for (Meeting m : meetingApiService.getMeetings()) {
            if (m.getRoom().getName().equals(salle)) {
                nothing = false;
                break;
            }
        }
        if (!nothing) {
            initList(meetingApiService.getMeetingsFilteredByRoom(salle));
            meetingApiService.getMeetingsFilteredByRoom(salle);
            adapter.notifyDataSetChanged();
        } else {
           // ToastUtils.showToastLong("Aucune réunion de prévue dans cette salle", getApplicationContext());
        }
    }

    private void initList(List<Meeting> meetings) {
        decrementIdleResource();
        adapter = new Adapter(meetings);
        recyclerView.setAdapter(adapter);

    }

    private void configureRecyclerView() {
        // 3.1 - Reset list
       // this.meetingList = MeetingGenerator.MEETING_LIST;
        // 3.2 - Create adapter passing the list of users
        //MeetingAdded meetingAdded;
        //metingAdded = new MeetingAdded();

        this.adapter = new Adapter(meetingApiService.getMeetings());
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


    private DatePickerDialog.OnDateSetListener generateDatePickerDialog() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                DateTime time = new DateTime(year, monthOfYear + 1, dayOfMonth, 00, 00);

                /* Filtre par date */

                for (Meeting m : meetingApiService.getMeetings()) {
                    if (m.getStartMeeting().toLocalDate().equals(time.toLocalDate())) {
                        initList(meetingApiService.getMeetingsBydate(time));
                        meetingApiService.getMeetingsBydate(time);
                        adapter.notifyDataSetChanged();

                    } else {
                        ToastUtils.showToastLong("Aucune réunion prévue à cette date", getApplicationContext());
                    }
                }
            }
        };
    }


    private void configureDialogCalendar() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialogDate = new DatePickerDialog(this, generateDatePickerDialog(), year, month, day);
        dialogDate.getDatePicker().setMinDate(System.currentTimeMillis());
        dialogDate.show();
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