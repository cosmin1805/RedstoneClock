package ro.iacobai.redstoneclock.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import ro.iacobai.redstoneclock.commands.subcommands.CreateCommand;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subcommands = new ArrayList<>();

    public  CommandManager(){
        subcommands.add(new CreateCommand());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            PersistentDataContainer data = p.getPersistentDataContainer();
            if(args.length == 0){
                p.sendMessage(ChatColor.GREEN+"---------------------");
                for (int i = 0; i < getSubcommands().size(); i++){
                    p.sendMessage(getSubcommands().get(i).getSyntax()+" - "+getSubcommands().get(i).getDescription());
                }
                p.sendMessage(ChatColor.GREEN+"---------------------");
            } else if (args.length > 0) {
                for (int i = 0; i < getSubcommands().size(); i++){
                    if(args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                        getSubcommands().get(i).perform(p,args);
                    }
                }
            }
        }
        return true;
    }
    public ArrayList<SubCommand> getSubcommands(){return  subcommands;}

}
