package dev.rm20.eightbitfireworkshow;

import dev.rm20.eightbitfireworkshow.commands.CommandFirework;
import dev.rm20.eightbitfireworkshow.Show.ShowManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EightBitFireworkShow extends JavaPlugin {
    private ShowManager showManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        showManager = new ShowManager(this); // Initialize here

        this.getCommand("FWS").setExecutor(new CommandFirework(this));
        getCommand("FWS-start-show").setExecutor(this); // Assuming this class implements CommandExecutor

    }

    @Override
    public void onDisable() {

    }


    public void reload() {
        reloadConfig();
    }
}
