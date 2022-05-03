package com.example.myreu.service;

import com.example.myreu.Models.Meeting;

import java.util.List;

public class MeetingAdded {

    private static List<Meeting> meetingList = MeetingGenerator.generateMeetings();

    public void addMeeting(Meeting meeting){

        meetingList.add(meeting);
    }

    public static List<Meeting> getMeetings(){
        return meetingList;
    }
}
