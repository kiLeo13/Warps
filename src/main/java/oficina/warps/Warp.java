package oficina.warps;

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

import java.util.List;

public class Warp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendRichMessage("<red>Only players can run this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendRichMessage("<red>Incorrect usage!");
            return false;
        }

        Player player = (Player) sender;
        Location location = WarpConfig.getInstance().getWarpsConfig().getLocation("warp");

        if (location == null) {
            sender.sendRichMessage("<red>Warp not found.");
            return true;
        }

        player.teleportAsync(location);
        player.sendRichMessage("<green>Successfully teleported to <gold>" + args[0] + "<green>.");

        player.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 1, 1);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}