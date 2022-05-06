package com.example.myreu.service;

import com.example.myreu.Models.Meeting;

import org.joda.time.DateTime;

import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    List<Meeting> getMeetingsFilteredByRoom(String room);

    void addMeeting(Meeting meeting);

     List<Meeting> getMeetingsRoom();

    public  List<Meeting> getMeetingsAscendingDate();

    public  List<Meeting> getMeetingsDescendingDate();


    public List<Meeting> getMeetingsBydate(DateTime time);
}
