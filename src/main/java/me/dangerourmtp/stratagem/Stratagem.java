package me.dangerourmtp.stratagem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Stratagem extends JavaPlugin {
    public static String prefix = "§7[§b§lStratagem§7] ";
    public static boolean spawnEnabled = true;
    public static boolean aiEnabled = true;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "Stratagem has Started!");
        this.getCommand("stratagem").setExecutor(new EventListener());
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
