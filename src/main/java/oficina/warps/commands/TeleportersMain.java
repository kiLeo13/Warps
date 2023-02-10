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

public class TeleportersMain implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) {
            sender.sendRichMessage("<red>Incorrect usage! Please specify one argument.");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("teleporters.reload")) {
                sender.sendRichMessage("<red>You do not have permission to use this command.");
                return true;
            }

            if (WarpConfig.getInstance().reloadWarpConfig()) sender.sendRichMessage("<green>Configuration successfully reloaded!");
            else sender.sendRichMessage("<red>Could not reload configuration! See console for errors.");
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            return new ArrayList<String>() {
                {
                    add("reload");
                }
            };
        } else return new ArrayList<>();
    }
}