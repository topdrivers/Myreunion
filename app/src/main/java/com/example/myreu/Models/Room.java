package com.example.myreu.Models;

public class Room {

    private String name;
    private int roomColor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomColor() {
        return roomColor;
    }

    public void setRoomColor(int roomColor) {
        this.roomColor = roomColor;
    }

    public Room(String name, int roomColor) {
        this.name = name;
        this.roomColor = roomColor;
    }
}
