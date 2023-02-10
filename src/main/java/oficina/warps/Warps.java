package oficina.warps;

import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Warps extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        // Register commands.
        PluginCommand command = this.getCommand("createwarp");
        if (command != null) command.setExecutor(new CreateWarp());

        PluginCommand command1 = this.getCommand("warp");
        if (command1 != null) command1.setExecutor(new Warp());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() { return plugin; }
}