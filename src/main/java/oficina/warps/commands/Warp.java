package oficina.warps.commands;

import oficina.warps.setup.WarpConfig;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Warp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendRichMessage("<red>Only players can run this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendRichMessage("<red>Incorrect usage!\n<yellow>/warp <gold><<yellow>WarpName<gold>>");
            return true;
        }

        Player player = (Player) sender;
        Location location = WarpConfig.getInstance().getWarpLocation(args[0]);

        if (WarpConfig.getInstance().getWarpLocation(args[0]) == null) {
            sender.sendRichMessage("<red>Warp not found.");
            return true;
        }

        player.teleportAsync(location);
        player.sendRichMessage("<green>Successfully teleported to <gold>" + args[0].toLowerCase() + "<green>.");

        player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return WarpConfig.getInstance().getWarps();
        else return new ArrayList<>();
    }
}