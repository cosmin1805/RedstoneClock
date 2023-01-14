package ro.iacobai.redstoneclock.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

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
        World world = Bukkit.getWorld("world");
        Location location = new Location(world,0,0,0);
        this.location = location.toString();
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
