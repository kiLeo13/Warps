package oficina.warps;

import oficina.warps.commands.CreateWarp;
import oficina.warps.commands.DeleteWarp;
import oficina.warps.commands.TeleportersMain;
import oficina.warps.commands.Warp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Teleporters extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        // Register commands.
        registerCommands();

        Bukkit.getLogger().info(ChatColor.GOLD + "" + this + ChatColor.LIGHT_PURPLE + " has been successfully enabled!");
    }

    @Override
    public void onDisable() { Bukkit.getLogger().info(ChatColor.LIGHT_PURPLE + "Bye bye!"); }

    public static Plugin getPlugin() { return plugin; }

    private void registerCommands() {
        PluginCommand createwarp = this.getCommand("createwarp");
        if (createwarp != null) createwarp.setExecutor(new CreateWarp());

        PluginCommand warp = this.getCommand("warp");
        if (warp != null) warp.setExecutor(new Warp());

        PluginCommand reload = this.getCommand("teleporters");
        if (reload != null) reload.setExecutor(new TeleportersMain());

        PluginCommand deletewarp = this.getCommand("deletewarp");
        if (deletewarp != null) deletewarp.setExecutor(new DeleteWarp());
    }
}