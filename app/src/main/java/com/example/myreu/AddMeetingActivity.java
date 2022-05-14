package com.example.myreu;

import static com.example.myreu.Utils.AutocompleteTextViewAdapterUtils.Autocomplete;
import static com.example.myreu.Utils.TimeUtils.beginTimeHandle;
import static com.example.myreu.Utils.TimeUtils.dateHandle;
import static com.example.myreu.Utils.TimeUtils.endTimeHandle;
import static com.example.myreu.service.MeetingGenerator.generateMeetings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myreu.Models.Meeting;
import com.example.myreu.Models.Room;
import com.example.myreu.di.DI;
import com.example.myreu.holder.Adapter;
import com.example.myreu.service.MeetingAdded;
import com.example.myreu.service.MeetingApiService;
import com.example.myreu.service.MeetingGenerator;
import com.example.myreu.service.RoomGenerator;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMeetingActivity extends AppCompatActivity {

    @BindView(R.id.activity_add_meeting_date) TextView date;
    @BindView(R.id.activity_add_meeting_begin) TextView begin;
    @BindView(R.id.activity_add_meeting_end) TextView end;
    
    @BindView(R.id.textInputLayout) TextInputLayout participants;
    @BindView(R.id.activity_add_meeting_item_name)    EditText name;
    @BindView(R.id.activity_add_meeting_room)    TextView room;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.activity_add_meeting_button) Button saveButton;
    @BindView(R.id.chipGroup) ChipGroup mParticipantsChipGroup;
    @BindView(R.id.addParticipant_button) Button addParticipantButton;
    @BindView(R.id.participant_autoCompleteTextView) AutoCompleteTextView mParticipantsAutoCompleteTextView;
    MeetingApiService meetingApiService;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        meetingApiService= DI.getMeetingApiService();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateHandle(date);
            }
        });

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beginTimeHandle(begin,AddMeetingActivity.this);

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endTimeHandle(end,getApplicationContext());

            }
        });




        final ArrayList<String> roomList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.meetings_room_array)));
        System.out.println("----------------------------roomList----------"+roomList);
        roomList.add(0,"veuillez sélectionner votre salle :");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.array_item,roomList){
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =  super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);// Set the hint text color gray
                    tv.setTextSize(25);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;

            }

            @Override
            public boolean isEnabled(int position) {
                return position!=0;
            }
        };
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0 && view!=null){
                    TextView tv = (TextView)view;
                    tv.setTextColor(Color.WHITE);
                    tv.setBackgroundColor(getResources().getColor(R.color.black));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* String vers DateTime */
                DateTimeFormatter formatterDate = DateTimeFormat.forPattern("dd/MM/yyyy");
                DateTime mDateEditJoda = formatterDate.parseDateTime(date.getText().toString());

                DateTimeFormatter formatterHour = DateTimeFormat.forPattern("HH:mm");
                DateTime mBeginTimeEditJoda = formatterHour.parseDateTime(begin.getText().toString());
                //DateTime mEndTimeEditJoda = formatterHour.parseDateTime(end.getText().toString());
                //Room addNewRoom = new Room(spinner.getSelectedItem().toString(),R.color.black);

               // Room addNewRoom = new Room(spinner.getSelectedItem().toString(),  spinner.getSelectedItemPosition());
                Room addNewRoom = RoomGenerator.genrateRooms().get(spinner.getSelectedItemPosition() - 1);
                System.out.println("-------------------------newroom----------------"+addNewRoom);

                /* Créer la liste des participants dans la liste des réunions sous forme de String séparés par des virgules */
                String mParticipants = "";
                for (int i = 0; i < mParticipantsChipGroup.getChildCount(); i++) {
                    com.google.android.material.chip.Chip chip = (com.google.android.material.chip.Chip) mParticipantsChipGroup.getChildAt(i);
                    if (i == 0) {
                        mParticipants = chip.getText().toString().concat(mParticipants);
                    } else {
                        mParticipants = chip.getText().toString().concat(", " + mParticipants);
                    }
                }

                //Meeting meeting = new Meeting(7,mDateEditJoda,
                  //      mBeginTimeEditJoda,name.getText().toString(),addNewRoom, participants.getText().toString() );
                int id = meetingApiService.getMeetings().size();
                System.out.println("------------id----------"+id);

                Meeting meeting = new Meeting(id,new DateTime(mDateEditJoda.getYear(), mDateEditJoda.getMonthOfYear(), mDateEditJoda.getDayOfMonth(), mDateEditJoda.getHourOfDay(), mDateEditJoda.getMinuteOfHour()),
                        new DateTime(mBeginTimeEditJoda.getYear(), mBeginTimeEditJoda.getMonthOfYear(), mBeginTimeEditJoda.getDayOfMonth(), mBeginTimeEditJoda.getHourOfDay(), mBeginTimeEditJoda.getMinuteOfHour()),name.getText().toString(),addNewRoom, mParticipants );
/*
                 List<Meeting> meetingList = MeetingGenerator.generateMeetings();
                 meetingList.add(meeting);
                MeetingAdded meetingAdded = new MeetingAdded();
                meetingAdded.addMeeting(meeting);

 */

                System.out.println("---------------CHipGroup---------------"+mParticipantsChipGroup.getDisplay());

               // MeetingApiService meetingApiService = DI.getMeetingApiService();
                //List<Meeting> meetings = meetingApiService.getMeetings();
                meetingApiService.addMeeting(meeting);
                //meetingList.add(meeting);
                //System.out.println("------------meetinglist-----------"+meetings);
               //Adapter adapter = new Adapter(meetingList);
                //recyclerView.setAdapter(adapter);
//               adapter.notifyDataSetChanged();
                //recyclerView.setLayoutManager(new LinearLayout(this));
                //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                Intent intent = new Intent(AddMeetingActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        /* Autocomplete + chips to add the participants : */
        Autocomplete(mParticipantsAutoCompleteTextView, this, addParticipantButton, mParticipantsChipGroup, getDrawable(R.drawable.ic_person_pin_black_18dp));



    }

}