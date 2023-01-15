package ro.iacobai.redstoneclock.tasks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.yaml.snakeyaml.events.Event;
import ro.iacobai.redstoneclock.RedstoneClock;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.models.Id;
import ro.iacobai.redstoneclock.utils.ClockConvertData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlaceAirBlock {
    private  static ArrayList<Id> Ids = new ArrayList<>();
    public static Integer getId(Player player,Clock clock) {
        for (Id id: Ids){
            if(id.getName()== clock.getName()&&id.getOwnerUUID()== clock.getOwnerUuid()){
                return id.getID();
            }
        }
        return 0;
    }

    public static void run_task(Player player, Clock clock){
        int delay = 0;
        if (clock !=null){
            delay = clock.getTime_on();
        }
        int ID = new BukkitRunnable() {
            @Override
            public void run() {
                if (clock !=null){
                    if(!clock.getState()){
                        return;
                    }
                    Location location = ClockConvertData.locationConvert(clock.getLocation());
                    location.getBlock().setBlockData(Material.AIR.createBlockData());
                }
            }
        }.runTaskLater(RedstoneClock.getPlugin(),delay*20).getTaskId();
        for (Id id: Ids){
            if(id.getName()== clock.getName()&&id.getOwnerUUID()== clock.getOwnerUuid()){
                id.setID(ID);
                return;
            }
        }
        Ids.add(new Id(ID,player.getUniqueId().toString(), clock.getName()));
    }
}
