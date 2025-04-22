package fr.citedesiles.cdiland.commands;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.corruption.CorruptionEntities;
import fr.citedesiles.cdiland.npc.RegisterNPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String name, @NotNull String[] args) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande.");
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "startcorrupt":
                CDILandPlugin.instance().corruptionManager().createCorruption("test", ((Player) commandSender).getLocation());
                break;
            case "stopcorrupt":
                CDILandPlugin.instance().corruptionManager().getCorruption("test").stopCorruption();
                break;
            case "corruptionpause":
                CDILandPlugin.instance().corruptionManager().getCorruption("test").setPaused(Boolean.parseBoolean(args[1]));
                break;
            case "corruptionbpt":
                CDILandPlugin.instance().corruptionManager().getCorruption("test").setBlocksPerTick(Integer.parseInt(args[1]));
                break;
            case "spawnnpc":
                RegisterNPC.createNPC(((Player) commandSender).getUniqueId(), args[1]);
                break;
            case "spawnwarden":
                CorruptionEntities.spawnCorruptedWarden(((Player) commandSender).getLocation());
                break;
            default:
                commandSender.sendMessage("§cUsage: /admin <startcorrupt|stopcorrupt|corruptionspeed|corruptionpause|spawnnpc>");
                break;
        }
        return true;
    }
}
