package me.dangerourmtp.stratagem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EventListener implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender || sender.isOp()) {
            int time = 0;
            if(args.length > 0) {
                if(args.length > 1) {
                    if (!args[1].isEmpty()) {
                        time = Integer.valueOf(args[1]) * 1000 * 60;
                    }
                }

                if (args[0].equalsIgnoreCase("clear")) {
                    Cleaner.clearEntities();
                    sender.sendMessage(Stratagem.prefix + "Cleaning FALLING_BLOCK, WITHER_SKULL, SHULKER_BULLET, SMALL_FIREBALL, FIREBALL, EVOKER_FANGS & LLAMA_SPIT...");
                }

                else if (args[0].equalsIgnoreCase("nospawn")) {
                    if(Stratagem.spawnEnabled == true) {
                        if (time > 0) {
                            Stratagem.spawnEnabled = false;
                            if(sender instanceof Player){
                                Bukkit.getConsoleSender().sendMessage(Stratagem.prefix + "Disabling mob Spawning during the next " + time / 1000 / 60 + " minute(s)");
                                Scheduler.startTimerSpawnPlayer(time,(Player)sender);
                            } else {
                                Scheduler.startTimerSpawn(time);
                            }
                            sender.sendMessage(Stratagem.prefix + "Disabling mob Spawning during the next " + time / 1000 / 60 + " minute(s)");
                        } else if (time < 0) {
                            sender.sendMessage(Stratagem.prefix + "Can't disable mob spawning for negative time. Come on, I am a programmer, not a time traveler");
                        } else if (time == 0) {
                            sender.sendMessage(Stratagem.prefix + "Specify a time please (in minutes)");
                        }
                    }else{
                        sender.sendMessage(Stratagem.prefix + "Mob spawns already disabled");
                    }
                }

                else if (args[0].equalsIgnoreCase("noai")) {
                    if(Stratagem.aiEnabled == true) {
                        if (time > 0) {
                            Cleaner.noAi(false);
                            if(sender instanceof Player){
                                Scheduler.startTimerAiPlayer(time,(Player)sender);
                                Bukkit.getConsoleSender().sendMessage(Stratagem.prefix + "Disabling mob Ai during the next " + time / 1000 / 60 + " minute(s)");
                            } else {
                                Scheduler.startTimerAi(time);
                            }
                            sender.sendMessage(Stratagem.prefix + "Disabling mob Ai during the next " + time / 1000 / 60 + " minute(s)");
                        } else if (time < 0) {
                            sender.sendMessage(Stratagem.prefix + "Can't disable mob Ai for negative time. Come on, I am a programmer, not a time traveler");
                        } else if (time == 0) {
                            sender.sendMessage(Stratagem.prefix + "Specify a time please (in minutes)");
                        }
                    } else {
                        sender.sendMessage(Stratagem.prefix + "Mob Ai already disabled");
                    }
                }

                else if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage("§7================ [§b§lStratagem§7] ================");
                    sender.sendMessage("§b/stratagem help: §7Displays this");
                    sender.sendMessage("§b/stratagem clear: §7Kill entities");
                    sender.sendMessage("§b/stratagem nospawn {Time in minutes}: §7Disables mob Spawning during the specified time");
                    sender.sendMessage("§b/stratagem noai {Time in minutes}: §7Disables mob Ai during the specified time");
                }

                else {
                    sender.sendMessage("§7================ [§b§lStratagem§7] ================");
                    sender.sendMessage("§b/stratagem help: §7Displays this");
                    sender.sendMessage("§b/stratagem clear: §7Kill entities");
                    sender.sendMessage("§b/stratagem nospawn {Time in minutes}: §7Disables mob Spawning during the specified time");
                    sender.sendMessage("§b/stratagem noai {Time in minutes}: §7Disables mob Ai during the specified time");
                }

            } else {
                sender.sendMessage("§7================ [§b§lStratagem§7] ================");
                sender.sendMessage("§b/stratagem help: §7Displays this");
                sender.sendMessage("§b/stratagem clear: §7Kill entities");
                sender.sendMessage("§b/stratagem nospawn {Time in minutes}: §7Disables mob Spawning during the specified time");
                sender.sendMessage("§b/stratagem noai {Time in minutes}: §7Disables mob Ai during the specified time");
            }
        } else {
            sender.sendMessage(Stratagem.prefix +  "This command can only be used by the console or by an operator");
        }
        return true;
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event){
        if((event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CHUNK_GEN || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DEFAULT || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DROWNED || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.REINFORCEMENTS || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SLIME_SPLIT || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.VILLAGE_INVASION) && Stratagem.spawnEnabled == false){
            event.setCancelled(true);
        }
    }
}
