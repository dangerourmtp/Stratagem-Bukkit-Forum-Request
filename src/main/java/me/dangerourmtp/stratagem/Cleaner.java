package me.dangerourmtp.stratagem;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;
import java.util.List;

public class Cleaner {
    public static void clearEntities(){
        Bukkit.getWorlds().forEach(world -> world.getEntities().stream().filter(entity -> (entity.getType().equals(EntityType.FALLING_BLOCK) || entity.getType().equals(EntityType.WITHER_SKULL) || entity.getType().equals(EntityType.SHULKER_BULLET) || entity.getType().equals(EntityType.SMALL_FIREBALL) || entity.getType().equals(EntityType.FIREBALL) || entity.getType().equals(EntityType.EVOKER_FANGS) || entity.getType().equals(EntityType.LLAMA_SPIT))).forEach(Entity::remove));
    }

    public static void noAi(boolean enable){
        List<World> world = Bukkit.getWorlds();
            for(int i = 0; i < world.size(); i++) {
                List<LivingEntity> ent = world.get(i).getLivingEntities();
                for(int z = 0; i < ent.size(); i++){
                    ent.get(i).setAI(enable);
                }
            }
            Stratagem.aiEnabled = enable;
    }
}
