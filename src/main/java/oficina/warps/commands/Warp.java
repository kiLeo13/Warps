package oficina.warps.commands;

import oficina.warps.setup.WarpConfig;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Warp implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendRichMessage("<red>Only players can run this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendRichMessage("<red>Incorrect usage!\n<green>/warp <gold><<yellow>WarpName<gold>>");
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

        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return WarpConfig.getInstance().getWarps();
        else return new ArrayList<>();
    }
}