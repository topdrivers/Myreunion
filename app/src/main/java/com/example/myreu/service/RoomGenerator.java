package com.example.myreu.service;

import com.example.myreu.Models.Room;
import com.example.myreu.R;

import java.util.Arrays;
import java.util.List;

public class RoomGenerator {


    private static List<Room> LIST_ROOM = Arrays.asList(
            new Room("Eclair", R.drawable.ic_brightness_1_amber_300_24dp),
            new Room("Andromède", R.drawable.ic_brightness_1_brown_300_24dp),
            new Room("Paintsilvia", R.drawable.ic_brightness_1_deep_orange_300_24dp),
            new Room("Pegasus", R.drawable.ic_brightness_1_grey_500_24dp),
            new Room("Vulton", R.drawable.ic_brightness_1_indigo_300_24dp),
            new Room("Quantum", R.drawable.ic_brightness_1_light_green_300_24dp),
            new Room("Sirius", R.drawable.ic_brightness_1_pink_300_24dp),
            new Room("Centuron", R.drawable.ic_brightness_1_purple_300_24dp),
            new Room("Poseidon", R.drawable.ic_brightness_1_red_200_24dp),
            new Room("Artemis", R.drawable.ic_brightness_1_yellow_300_24dp)


    );

    public static List<Room> genrateRooms(){
        return LIST_ROOM;
    }

}
