package oficina.warps;

import oficina.warps.setup.WarpConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreateWarp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendRichMessage("<red>Only players can run this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendRichMessage("<red>Incorrect usage!\n<yellow>/createwarp <gold><<yellow>WarpName<gold>>");
            return true;
        }

        Player player = (Player) sender;

        String warpName = args[0].toLowerCase();
        FileConfiguration fileConfig = WarpConfig.getInstance().getWarpsConfig();
        ConfigurationSection section = fileConfig.getConfigurationSection(warpName);

        if (section == null) section = fileConfig.createSection(warpName);

        section.set("position", player.getLocation());

        player.sendRichMessage("<green>Warp <light_purple>" + warpName + "<green> has been successfully created.");

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}