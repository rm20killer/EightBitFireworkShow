package dev.rm20.eightbitfireworkshow.commands;

import dev.rm20.eightbitfireworkshow.EightBitFireworkShow;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandFirework implements CommandExecutor {
    EightBitFireworkShow fireworkshow;
    public CommandFirework(EightBitFireworkShow fireworkshow) {
        this.fireworkshow = fireworkshow;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("FireworkShow.reload")) {
                fireworkshow.reload();
                sender.sendPlainMessage("Celeste has been reloaded");
            } else {
                sender.sendPlainMessage("You do not have permission to use this command");
            }
            return true;
        }
        return false;
    }

}
