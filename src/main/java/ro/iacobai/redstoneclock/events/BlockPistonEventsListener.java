package ro.iacobai.redstoneclock.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.util.ArrayList;

public class BlockPistonEventsListener implements Listener {
    @EventHandler
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event) {
        for(Block block : event.getBlocks()) {
            //The block being moved
            Location location = block.getLocation();

            ArrayList<Clock> clocks = ClockStorageUtil.listAllClocks();
            for(Clock clock: clocks){
                if(clock.getLocation().equals(location.toString())){
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event) {
        for(Block block : event.getBlocks()) {
            //The block being moved
            Location location = block.getLocation();

            ArrayList<Clock> clocks = ClockStorageUtil.listAllClocks();
            for(Clock clock: clocks){
                if(clock.getLocation().equals(location.toString())){
                    event.setCancelled(true);
                }
            }
        }
    }
}
