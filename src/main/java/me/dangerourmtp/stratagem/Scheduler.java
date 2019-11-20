package me.dangerourmtp.stratagem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Scheduler {
    public static void startTimerAiPlayer(int time, Player p){
        new java.util.Timer().schedule(new java.util.TimerTask() {@Override public void run() {
            Cleaner.noAi(true);
            Bukkit.getConsoleSender().sendMessage(Stratagem.prefix + "Mobs Ai renabled");
            p.sendMessage(Stratagem.prefix + "Mobs Ai renabled");}}, time);

    }
    public static void startTimerAi(int time){
        new java.util.Timer().schedule(new java.util.TimerTask() {@Override public void run() {
            Cleaner.noAi(true);
            Bukkit.getConsoleSender().sendMessage(Stratagem.prefix + "Mobs Ai renabled");}}, time);
    }

    public static void startTimerSpawnPlayer(int time, Player p){
        new java.util.Timer().schedule(new java.util.TimerTask() {@Override public void run() {
            Stratagem.spawnEnabled = true;
            Bukkit.getConsoleSender().sendMessage(Stratagem.prefix + "Mobs can spawn now");
            p.sendMessage(Stratagem.prefix + "Mobs can spawn now");}}, time);
    }
    public static void startTimerSpawn(int time){
        new java.util.Timer().schedule(new java.util.TimerTask() {@Override public void run() {
            Stratagem.spawnEnabled = true;
            Bukkit.getConsoleSender().sendMessage(Stratagem.prefix + "Mobs can spawn now");}}, time);
    }
}
