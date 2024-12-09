package dev.rm20.eightbitfireworkshow.commands;



import dev.rm20.eightbitfireworkshow.EightBitFireworkShow;
import dev.rm20.eightbitfireworkshow.Show.ShowManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandStart implements CommandExecutor {
    EightBitFireworkShow fireworkshow;
    public CommandStart(EightBitFireworkShow fireworkshow) {
        this.fireworkshow = fireworkshow;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2 && args[0].equalsIgnoreCase("FWS-start-show")) {
            if (sender.hasPermission("FireworkShow.start")) {
                String showName = args[1];
                ShowManager.startShow(showName); // Start the show!
            } else {
                sender.sendPlainMessage("You do not have permission to use this command");
            }
            return true;
        }
        return false;
    }

}
