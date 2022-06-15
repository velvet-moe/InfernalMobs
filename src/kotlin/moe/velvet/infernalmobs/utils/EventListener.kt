package moe.velvet.infernalmobs.utils

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent
import moe.velvet.infernalmobs.InfernalMob
import moe.velvet.infernalmobs.getInfernalDataClass
import moe.velvet.infernalmobs.isInfernalMob
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler

import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.entity.EntityTargetEvent

class EventListener : Listener {
    @EventHandler
    fun onEntitySpawn(e: EntitySpawnEvent) {
        // Assign infernal status if chance is met
        if ((0 .. 100).random() > Glob.Constants.INFERNAL_CHANCE) return
        val infernal = InfernalMob(e.entity as LivingEntity)
        // Trigger events
        infernal.abilities.forEach { it.onSpawn(e) }
    }

    @EventHandler
    fun onEntityDamage(e: EntityDamageEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        getInfernalDataClass(e.entity)?.abilities?.forEach { it.onDamage(e) }
    }

    @EventHandler
    fun onEntityDeath(e: EntityDeathEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        getInfernalDataClass(e.entity)?.abilities?.forEach { it.onDeath(e) }
    }

    @EventHandler
    fun onEntityTarget(e: EntityTargetEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        getInfernalDataClass(e.entity)?.abilities?.forEach { it.onTarget(e) }
    }

    @EventHandler
    fun onEntityDespawn(e: EntityRemoveFromWorldEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        getInfernalDataClass(e.entity)?.abilities?.forEach { it.onDespawn(e) }
    }

    @EventHandler
    fun onDamageEntity(e: EntityDamageByEntityEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.damager)) return
        // Trigger events
        getInfernalDataClass(e.damager)?.abilities?.forEach { it.onDamageEntity(e) }
    }
}

