package ro.iacobai.redstoneclock.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class ClockConvertData {
    public static Location locationConvert(String location){
        String world_name = location.substring(location.indexOf("name=")+5,location.indexOf("}"));
        World world = Bukkit.getWorld(world_name);
        double x = Double.parseDouble(location.substring(location.indexOf("x=")+3,location.indexOf("y")-1));
        double y = Double.parseDouble(location.substring(location.indexOf("y=")+3,location.indexOf("z")-1));
        double z = Double.parseDouble(location.substring(location.indexOf("z=")+3,location.indexOf("pitch")-1));
        return new Location(world,x,y,z);
    }
}
