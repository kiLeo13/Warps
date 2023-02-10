package oficina.warps;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Teleporters extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        // Register commands.
        PluginCommand createwarp = this.getCommand("createwarp");
        if (createwarp != null) createwarp.setExecutor(new CreateWarp());

        PluginCommand warp = this.getCommand("warp");
        if (warp != null) warp.setExecutor(new Warp());

        PluginCommand reload = this.getCommand("reload");
        if (reload != null) reload.setExecutor(new Reload());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() { return plugin; }
}