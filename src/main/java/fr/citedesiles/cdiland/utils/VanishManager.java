package fr.citedesiles.cdiland.utils;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishManager {

    private static final List<UUID> vanishedPlayers = new ArrayList<>();

    public static boolean isVanished(UUID uuid) {
        return vanishedPlayers.contains(uuid);
    }

    public static void setVanished(UUID uuid, boolean vanished) {
        if (vanished) {
            if (!vanishedPlayers.contains(uuid)) vanishedPlayers.add(uuid);
        } else {
            vanishedPlayers.remove(uuid);
        }
    }

    public static void command(CommandSender commandSender, String[] args) {
        if (args.length > 2) {
            commandSender.sendMessage("§cUsage: /vanish [player]");
            return;
        }

        if (!(commandSender instanceof Player) && args.length == 1) {
            commandSender.sendMessage("§cLa commande doit être exécutée par un joueur.");
            return;
        }

        OfflinePlayer player;
        if (args.length == 1) player = (Player) commandSender;
        else player = commandSender.getServer().getOfflinePlayerIfCached(args[1]);

        if (player == null) {
            commandSender.sendMessage("§cCe joueur n'existe pas ou ne s'est jamais connecté.");
            return;
        }

        if (isVanished(player.getUniqueId())) {
            setVanished(player.getUniqueId(), false);
            commandSender.sendMessage("§aLe joueur " + player.getName() + " n'est plus invisible.");
        } else {
            setVanished(player.getUniqueId(), true);
            commandSender.sendMessage("§aLe joueur " + player.getName() + " est maintenant invisible.");
        }
    }
}
