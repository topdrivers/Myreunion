package com.example.myreu.service;

import com.example.myreu.Models.Meeting;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassMeetingApiService implements MeetingApiService{

    List<Meeting> meetingList = MeetingGenerator.generateMeetings();
    private List<Meeting> res = new ArrayList<>();



    @Override
    public List<Meeting> getMeetings() {
        return meetingList;
    }

    @Override
    public List<Meeting> getMeetingsFilteredByRoom(String room) {
            resetList();
        for(Meeting meeting : meetingList){
            if(meeting.getRoom().getName().equals(room)){
                res.add(meeting);
            }
        }
        return res;
    }

    @Override
    public void addMeeting(Meeting meeting) {


            meetingList.add(meeting);

    }

    @Override
    public  List<Meeting> getMeetingsRoom() {

           Collections.sort(meetingList, new Comparator<Meeting>() {
                public int compare(Meeting o2, Meeting o1) {
                    return o1.getRoom().getRoomColor() - (o2.getRoom().getRoomColor());

                }
            });
        System.out.println("-------------listmeetingfiltered----------"+meetingList);
            return res;



    }

    @Override
    public List<Meeting> getMeetingsAscendingDate() {
        Collections.sort(meetingList, new Comparator<Meeting>() {
            public int compare(Meeting o2, Meeting o1) {
                return o2.getStartMeeting().compareTo(o1.getStartMeeting());

            }
        });

        return res;


    }

    @Override
    public List<Meeting> getMeetingsDescendingDate() {
        Collections.sort(meetingList, new Comparator<Meeting>() {
            public int compare(Meeting o2, Meeting o1) {
                return o1.getStartMeeting().compareTo(o2.getStartMeeting());

            }
        });

        return res;


    }

    public List<Meeting> getMeetingsBydate(DateTime time){

            resetList();
            for(Meeting m : meetingList){
                if(m.getStartMeeting().toLocalDate().equals(time.toLocalDate())){
                    res.add(m);
                }

            }
            return res;
    }



    private void resetList() {
        for(Meeting m : meetingList){
            res.clear();
        }
    }


}
