package ro.iacobai.redstoneclock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import ro.iacobai.redstoneclock.Items.RedstoneBlockSelection;
import ro.iacobai.redstoneclock.commands.CommandManager;
import ro.iacobai.redstoneclock.commands.TabComplete;
import ro.iacobai.redstoneclock.models.Clock;
import ro.iacobai.redstoneclock.tasks.PlaceRedstoneBlock;
import ro.iacobai.redstoneclock.utils.ClockConvertData;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.io.IOException;

import static ro.iacobai.redstoneclock.utils.ClockStorageUtil.saveClocks;

public final class RedstoneClock extends JavaPlugin {
    private static RedstoneClock plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("rc").setExecutor(new CommandManager());
        getCommand("rc").setTabCompleter(new TabComplete());
        getServer().getPluginManager().registerEvents(new RedstoneBlockSelection(),this);
        plugin = this;
        try {
            ClockStorageUtil.loadClocks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Clock clock: ClockStorageUtil.listAllClocks()){
            if(clock.getState()){
                PlaceRedstoneBlock.run_task(clock);
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    saveClocks();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskTimer(this,200*20,300*20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            saveClocks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static RedstoneClock getPlugin() {
        return plugin;
    }
}
