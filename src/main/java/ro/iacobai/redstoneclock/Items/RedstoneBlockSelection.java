package ro.iacobai.redstoneclock.Items;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ro.iacobai.redstoneclock.commands.subcommands.SelectCommand;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.utils.ClockConvertData;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.util.HashMap;
import java.util.UUID;

public class RedstoneBlockSelection implements Listener {
    private HashMap<UUID, Long> cooldown=new HashMap<>();
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if(block == null)
        {
            return;
        }
        Location location = block.getLocation();
        Material material = block.getBlockData().getMaterial();
        if(action.equals(Action.LEFT_CLICK_BLOCK)||action.equals(Action.RIGHT_CLICK_BLOCK)){
            if(player.getInventory().getItemInMainHand().equals(new ItemStack(Material.REDSTONE_BLOCK))==false)
            {
                return;
            }
            if (this.cooldown.containsKey(player.getUniqueId())==true) {
                long timeE = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                if (timeE < 5000) {
                    return;
                }
                else {
                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                }
            } else {
                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
            }
            String name = SelectCommand.getSelected_Clock(player);
            player.sendMessage(ChatColor.GREEN+"---------------------");
            if(name == null){
                player.sendMessage(ChatColor.RED+"NO REDSTONE CLOCK SELECTED!");
            }
            else {
                Clock clock = ClockStorageUtil.findClock(name,player);

                //WE MAKE SURE THE OLD REDSTONE CLOCK LOCATION IS SET TO AIR
                Location clock_location = ClockConvertData.locationConvert(clock.getLocation());
                clock_location.getBlock().setType(Material.AIR);


                clock.setLocation(location.toString());
                ClockStorageUtil.updateClock(clock);
                player.sendMessage("REDSTONE CLOCK LOCATION: "+ ChatColor.GOLD+"x:"+ChatColor.LIGHT_PURPLE+location.getX()+ChatColor.GOLD+" y:"+ChatColor.LIGHT_PURPLE+location.getY()+ChatColor.GOLD+" z:"+ChatColor.LIGHT_PURPLE+location.getZ());
            }
            player.sendMessage(ChatColor.GREEN+"---------------------");
        }
    }
}
