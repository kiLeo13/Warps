package oficina.warps.setup;

import oficina.warps.Teleporters;
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
            return true;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addWarp(String name, Location location) {
        String warpName = name.toLowerCase();
        ConfigurationSection section = getWarpsConfig().getConfigurationSection(warpName);

        if (section == null) section = getWarpsConfig().createSection(warpName);

        section.set("position", location);

        try {
            getWarpsConfig().save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
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