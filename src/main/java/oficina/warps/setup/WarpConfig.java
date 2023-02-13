package oficina.warps.setup;

import oficina.warps.Teleporters;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class WarpConfig {

    private static WarpConfig instance;

    private final String fileName = "warps.yml";
    private final File dataFile = new File(Teleporters.getPlugin().getDataFolder(), fileName);
    private final FileConfiguration warpsConfig = new YamlConfiguration();

    private WarpConfig() {
        if (!dataFile.exists()) Teleporters.getPlugin().saveResource(fileName, false);
        reloadWarpConfig();
    }

    public static WarpConfig getInstance() {
        if (instance == null) instance = new WarpConfig();
        return instance;
    }

    public FileConfiguration getWarpsConfig() { return warpsConfig; }

    public boolean reloadWarpConfig() {
        try {
            warpsConfig.load(dataFile);

            Bukkit.getLogger().info(ChatColor.YELLOW + "[" + ChatColor.GOLD + Teleporters.getPlugin() + "" + ChatColor.YELLOW + "]" + ChatColor.WHITE + " configuration successfully reloaded!");
            return true;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();

            return false;
        }
    }

    public void addWarp(String name, Location location) {
        name = name.toLowerCase();
        ConfigurationSection section = getWarpsConfig().getConfigurationSection(name);

        if (section == null) section = getWarpsConfig().createSection(name);

        section.set("position", location);

        try {
            getWarpsConfig().save(dataFile);

            Bukkit.getLogger().info(ChatColor.YELLOW + "[" + ChatColor.GOLD + Teleporters.getPlugin() + "" + ChatColor.YELLOW + "]" + ChatColor.GREEN + " successfully created " + ChatColor.LIGHT_PURPLE + name + ChatColor.GREEN + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteWarp(String name) {
        name = name.toLowerCase();

        getWarpsConfig().set(name, null);

        try {
            getWarpsConfig().save(dataFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Location getWarpLocation(String name) {
        name = name.toLowerCase();
        ConfigurationSection section = getWarpsConfig().getConfigurationSection(name);

        if (section == null) { return null; }

        return section.getLocation("position");
    }

    public ArrayList<String> getWarps() {
        Set<String> keys = getWarpsConfig().getKeys(false);

        return new ArrayList<>(keys);
    }

    public boolean warpExists(String name) {
        name = name.toLowerCase();
        ConfigurationSection section = getWarpsConfig().getConfigurationSection(name);

        return section != null;
    }
}