package oficina.warps.setup;

import oficina.warps.Warps;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WarpConfig {

    private static WarpConfig instance;

    private final String fileName = "warps.yml";
    private final File dataFile = new File(Warps.getPlugin().getDataFolder(), fileName);
    private final FileConfiguration warpsConfig = new YamlConfiguration();

    private WarpConfig() {
        if (!dataFile.exists()) Warps.getPlugin().saveResource(fileName, false);
        reloadWarpConfig();
    }

    public static WarpConfig getInstance() {
        if (instance == null) instance = new WarpConfig();
        return instance;
    }

    public FileConfiguration getWarpsConfig() { return warpsConfig; }

    public void reloadWarpConfig() {
        try { warpsConfig.load(dataFile); }
        catch (IOException | InvalidConfigurationException e) { e.printStackTrace(); }
    }
}