package ro.iacobai.redstoneclock.utils;

import com.google.gson.Gson;
import org.bukkit.ChatColor;
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
    public static String listAllOwnerClocks(Player player) {
        //linear search
        String list = "";
        int i = 0;
        for (Clock clock : clocks){
            if(clock.getOwnerUuid().equals(player.getUniqueId().toString())){
                list+=" "+ChatColor.LIGHT_PURPLE+clock.getName()+ ChatColor.WHITE+",";
            }
        }
        return list.substring(0,list.length()-1);
    }
    public static void deleteClock(String name , Player player) {
        //linear search
        for (Clock clock : clocks){
            if(clock.getName().equals(name) && clock.getOwnerUuid().equals(player.getUniqueId().toString())){
                clocks.remove(clock);
                break;
            }
        }

        try {
            saveClocks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Clock updateClock(String name, Clock newClock){
        return null;
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
        System.out.println("Clocks saved");
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
