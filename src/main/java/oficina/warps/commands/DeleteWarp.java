package oficina.warps.commands;

import oficina.warps.setup.WarpConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DeleteWarp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length != 1) {
            sender.sendRichMessage("<red>Incorrect usage!\nSee: <green>/deletewarp <gold><<yellow>WarpName<gold>>");
            return true;
        }

        if (WarpConfig.getInstance().getWarpLocation(args[0]) == null) {
            sender.sendRichMessage("<red>Warp not found.");
            return true;
        }

        if (WarpConfig.getInstance().deleteWarp(args[0])) sender.sendRichMessage("<green>Warp <gold>" + args[0].toLowerCase() + "<green> successfully deleted!");
        else { sender.sendRichMessage("<red>Could not delete warp! Check console for errors."); }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) return WarpConfig.getInstance().getWarps();
        else return new ArrayList<>();
    }
}