package com.example.myreu.Models;

import org.joda.time.DateTime;

public class Meeting {
    private int id;
    private DateTime startMeeting;
    private DateTime endMeeting;
    private String name;
    private Room room;
    private String participants;

    public Meeting(int id, DateTime startMeeting, DateTime endMeeting, String name, Room room, String participants) {
        this.id = id;
        this.startMeeting = startMeeting;
        this.endMeeting = endMeeting;
        this.name = name;
        this.room = room;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getStartMeeting() {
        return startMeeting;
    }

    public void setStartMeeting(DateTime startMeeting) {
        this.startMeeting = startMeeting;
    }

    public DateTime getEndMeeting() {
        return endMeeting;
    }

    public void setEndMeeting(DateTime endMeeting) {
        this.endMeeting = endMeeting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}
