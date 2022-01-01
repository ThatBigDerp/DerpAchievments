package net.derp.derpachievments;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DerpAchievments extends JavaPlugin implements Listener {

    private AdvancementTab advancementTab;
    private RootAdvancement root;
    private UltimateAdvancementAPI api;

    @Override
    public void onEnable() {
        api = UltimateAdvancementAPI.getInstance(this);

        advancementTab = api.createAdvancementTab("derp");
        System.out.println("tab created");
        AdvancementDisplay rootDisplay = new AdvancementDisplay(Material.SALMON, "Be a derp", AdvancementFrameType.TASK, true, true, 0, 0, "Slap a player with raw salmon...");

        root = new RootAdvancement(advancementTab, "root", rootDisplay, "textures/block/beehive_end.png");

        advancementTab.registerAdvancements(root);

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerLoadingCompletedEvent e) {
        // Called after a player has successfully been loaded by the API
        Player p = e.getPlayer();
        // Here you can show tabs to players
        System.out.println("player joined");
        advancementTab.showTab(p);
        System.out.println("tab showed");
        root.grant(p);
        System.out.println("achievment granted");
    }
}
