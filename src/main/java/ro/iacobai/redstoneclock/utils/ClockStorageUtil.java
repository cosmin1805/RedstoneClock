package ro.iacobai.redstoneclock.utils;

import com.google.gson.Gson;
import org.bukkit.entity.Player;

import ro.iacobai.redstoneclock.RedstoneClock;
import ro.iacobai.redstoneclock.models.Clock;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ClockStorageUtil {
    private  static ArrayList<Clock> clocks = new ArrayList<>();
    public static Boolean createClock(String name, Player player){
        for (Clock clock : clocks){
            if(clock.getName().equals(name) && clock.getOwnerUuid().equals(player.getUniqueId().toString())){
                return false;
            }
        }
        Clock clock = new Clock(name,player.getUniqueId().toString());
        clocks.add(clock);
        try {
            saveClocks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static Clock findClock(String name,Player player){
        //linear search
        for (Clock clock : clocks){
            if(clock.getName().equals(name) && clock.getOwnerUuid().equals(player.getUniqueId().toString())){
                return clock;
            }
        }
        return null;
    }
    public static ArrayList<Clock> listAllClocks(){
        return clocks;
    }
    public static ArrayList<Clock> listAllOwnerClocks(Player player) {
        //linear search
        ArrayList<Clock> clock_list = new ArrayList<>();
        for (Clock clock : clocks){
            if(clock.getOwnerUuid().equals(player.getUniqueId().toString())){
                clock_list.add(clock);
            }
        }
        return clock_list;
    }
    public static boolean deleteClock(String name , Player player) {
        //linear search
        for (Clock clock : clocks){
            if(clock.getName().equals(name) && clock.getOwnerUuid().equals(player.getUniqueId().toString())){
                clocks.remove(clock);
                return true;
            }
        }
        return false;
    }
    public static boolean updateClock(Clock newClock){
        for (Clock clock : clocks){
            if(clock.getName().equals(newClock.getName()) && clock.getOwnerUuid().equals(newClock.getOwnerUuid())){
                clock.setLocation(newClock.getLocation());
                clock.setDelay(newClock.getDelay());
                clock.setTime_on(newClock.getTime_on());
                clock.setState(newClock.getState());
                return true;
            }
        }
        return false;
    }
    public static void saveClocks() throws IOException {
        Gson gson = new Gson();
        File file = new File(RedstoneClock.getPlugin().getDataFolder().getAbsolutePath()+"/clocks.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file,false);
        gson.toJson(clocks,writer);
        writer.flush();
        writer.close();
    }

    public  static  void loadClocks() throws IOException{
        Gson gson = new Gson();
        File file = new File(RedstoneClock.getPlugin().getDataFolder().getAbsolutePath()+"/clocks.json");
        if(file.exists()){
            Reader reader = new FileReader(file);
            Clock[] n = gson.fromJson(reader,Clock[].class);
            clocks = new ArrayList<>(Arrays.asList(n));
            System.out.println("Clocks loaded!");
        }
    }
}
