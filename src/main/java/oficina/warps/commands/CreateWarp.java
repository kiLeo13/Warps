package oficina.warps.commands;

import oficina.warps.setup.WarpConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CreateWarp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendRichMessage("<red>Only players can run this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendRichMessage("<red>Incorrect usage!\n<green>/createwarp <gold><<yellow>WarpName<gold>>");
            return true;
        }

        Player player = (Player) sender;
        boolean warpExists = WarpConfig.getInstance().warpExists(args[0]);

        // Creating Warp.

        WarpConfig.getInstance().addWarp(args[0], player.getLocation());

        if (!warpExists) player.sendRichMessage("<green>Warp <light_purple>" + args[0].toLowerCase() + "<green> has been successfully created.");
        else player.sendRichMessage("<yellow>Warp <light_purple>" + args[0].toLowerCase() + "<yellow> has been overridden!");

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>();
    }
}