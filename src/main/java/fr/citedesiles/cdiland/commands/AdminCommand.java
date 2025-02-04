package fr.citedesiles.cdiland.commands;

import fr.citedesiles.cdiland.CDILandPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }
        switch (args[0]) {
            case "startCorrupt":
                CDILandPlugin.instance().corruptionManager().createCorruption("test", ((Player) commandSender).getLocation(), 10);
                break;
        }
        return true;
    }
}
