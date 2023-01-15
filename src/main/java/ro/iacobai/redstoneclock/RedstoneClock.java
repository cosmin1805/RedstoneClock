package ro.iacobai.redstoneclock;

import org.bukkit.plugin.java.JavaPlugin;
import ro.iacobai.redstoneclock.Items.RedstoneBlockSelection;
import ro.iacobai.redstoneclock.commands.CommandManager;
import ro.iacobai.redstoneclock.commands.TabComplete;
import ro.iacobai.redstoneclock.utils.ClockStorageUtil;

import java.io.IOException;

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RedstoneClock getPlugin() {
        return plugin;
    }
}
