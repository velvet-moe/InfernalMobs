package moe.velvet.infernalmobs.powers

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent
import moe.velvet.infernalmobs.utils.Glob
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.entity.EntityTargetEvent

interface Power {
    val name: String
    val scaleFactor: Int
        get() = (Glob.Constants.SCALING_FACTOR * 0.5).toInt()

    fun onSpawn(e: EntitySpawnEvent) {return}
    fun onDamage(e: EntityDamageEvent) {return}
    fun onDeath(e: EntityDeathEvent) {return}
    fun onTarget(e: EntityTargetEvent) {return}
    fun onDespawn(e: EntityRemoveFromWorldEvent) {return}
    fun onDamageEntity(e: EntityDamageByEntityEvent) {return}
}