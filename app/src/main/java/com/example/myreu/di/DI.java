package com.example.myreu.di;

import com.example.myreu.service.ClassMeetingApiService;
import com.example.myreu.service.MeetingApiService;

public class DI {

        private static final MeetingApiService meetingApiService = new ClassMeetingApiService();

        public static MeetingApiService getMeetingApiService(){
            return meetingApiService;
        }
}
