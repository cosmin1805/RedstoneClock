package ro.iacobai.redstoneclock.models;

import java.lang.String;



public class Clock {

    private String name;
    private String ownerUuid;
    private String location;
    private boolean state;
    private int time;

    public Clock(String name, String ownerUuid) {
        this.name = name;
        this.ownerUuid = ownerUuid;
        this.location = "none";
        this.state = false;
        this.time = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
