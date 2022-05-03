package com.example.myreu.service;

import com.example.myreu.Models.Meeting;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingGenerator {
    private static final DateTime startTimeMeeting0 = new DateTime(2020,3,25,10,0);
    private static final DateTime endTimeMeeting0 = new DateTime(2020,3,25,12,0);

    private static final DateTime startTimeMeeting1 = new DateTime(2020,4,25,10,0);
    private static final DateTime endTimeMeeting1 = new DateTime(2020,4,25,12,0);

    private static final DateTime startTimeMeeting2 = new DateTime(2020,5,25,10,0);
    private static final DateTime endTimeMeeting2 = new DateTime(2020,5,25,12,0);

    private static final DateTime startTimeMeeting3 = new DateTime(2020,6,25,10,0);
    private static final DateTime endTimeMeeting3 = new DateTime(2020,6,25,12,0);

    private static final DateTime startTimeMeeting4 = new DateTime(2020,7,25,10,0);
    private static final DateTime endTimeMeeting4 = new DateTime(2020,7,25,12,0);

    private static final DateTime startTimeMeeting5 = new DateTime(2020,8,25,10,0);
    private static final DateTime endTimeMeeting5 = new DateTime(2020,8,25,12,0);

    private static final DateTime startTimeMeeting6 = new DateTime(2020,9,25,10,0);
    private static final DateTime endTimeMeeting6 = new DateTime(2020,9,25,12,0);

    private static final DateTime startTimeMeeting7 = new DateTime(2020,10,25,10,0);
    private static final DateTime endTimeMeeting7 = new DateTime(2020,10,25,12,0);



    public static final List<Meeting> MEETING_LIST = Arrays.asList(
            new Meeting(0,startTimeMeeting0,endTimeMeeting0, "Urgent",
                    RoomGenerator.genrateRooms().get(0),
                    "support@top-drivers.fr driver@driver.com"),
            new Meeting(1,startTimeMeeting1,endTimeMeeting1, "MARKETING",
                    RoomGenerator.genrateRooms().get(1),
                    "user@user.fr smail.omari@laposte.net"),
            new Meeting(2,startTimeMeeting2,endTimeMeeting2, "Social media",
                    RoomGenerator.genrateRooms().get(2),
                    "propice67100@gmail.com c_reelement_moi@yahoo.com"),
            new Meeting(3,startTimeMeeting3,endTimeMeeting3, "Comptabilite",
                    RoomGenerator.genrateRooms().get(3),
                    "support@orange.fr"),
            new Meeting(4,startTimeMeeting4,endTimeMeeting4, "Raport annuel",
                    RoomGenerator.genrateRooms().get(4),
                    "contact@decathlon.fr support@gosport.com"),
            new Meeting(5,startTimeMeeting5,endTimeMeeting5, "Retour clients",
                    RoomGenerator.genrateRooms().get(5),
                    "test@test.fr"),
            new Meeting(6,startTimeMeeting6,endTimeMeeting6, "Investissements",
                    RoomGenerator.genrateRooms().get(6),
                    "help@sncf.com contact@sncf.fr")


            );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETING_LIST);
    }

}
