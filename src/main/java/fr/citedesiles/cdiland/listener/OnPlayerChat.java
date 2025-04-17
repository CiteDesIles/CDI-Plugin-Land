package fr.citedesiles.cdiland.listener;

import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.citedesiles.cdiland.CDILandPlugin;
import fr.citedesiles.cdiland.objects.CDITeam;
import fr.citedesiles.cdiland.utils.DiscordWebhookUtility;
import io.papermc.paper.event.player.AsyncChatEvent;

public class OnPlayerChat implements Listener {
        @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        CompletableFuture.runAsync(() -> {
            DiscordWebhookUtility discordWebhooksUtility = new DiscordWebhookUtility(CDILandPlugin.instance());
            discordWebhooksUtility.onPlayerSendMessage(event.getPlayer(), event.signedMessage().message());
        });
        event.setCancelled(true);
        Player player = event.getPlayer();
        String team = CDILandPlugin.instance().playerManager().get(player).getTeam();
        CDITeam cdiTeam = CDILandPlugin.instance().teamManager().getTeam(team);
        if (cdiTeam == null) {
            return;
        }
        String teamColor = cdiTeam.getColor();
        String playerName = player.getName();
        String message = event.signedMessage().message();
        String chat = "";
        chat+= teamColor + playerName + "§7: §f" + message;
        for(Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(chat);
        }
    }
}
