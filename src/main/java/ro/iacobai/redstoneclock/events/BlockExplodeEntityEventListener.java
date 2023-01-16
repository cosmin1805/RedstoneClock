package ro.iacobai.redstoneclock.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.util.ArrayList;
import java.util.List;

public class BlockExplodeEntityEventListener implements Listener {
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        List<Block> blocksDontRemove = new ArrayList<>();

        for(Block block : event.blockList()) {
            //The block being moved
            Location location = block.getLocation();
            ArrayList<Clock> clocks = ClockStorageUtil.listAllClocks();
            for(Clock clock: clocks){
                if(clock.getLocation().equals(location.toString())){
                    blocksDontRemove.add(block);
                }
            }
        }
        for(Block block : blocksDontRemove) {
            event.blockList().remove(block);
        }
    }
}
