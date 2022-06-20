package moe.velvet.infernalmobs.utils

import moe.velvet.infernalmobs.InfernalMob
import moe.velvet.infernalmobs.getInfernalDataClass
import moe.velvet.infernalmobs.isInfernalMob
import org.bukkit.entity.Item
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler

import org.bukkit.event.Listener
import org.bukkit.event.entity.*

class EventListener : Listener {
    @EventHandler
    fun onEntitySpawn(e: EntitySpawnEvent) {
        // Assign infernal status if chance is met
        if ((0..100).random() > Glob.Constants.INFERNAL_CHANCE) return
        if (e.entity is Item) return
        if (e.entity.entitySpawnReason == CreatureSpawnEvent.SpawnReason.CUSTOM) return
        if (!Glob.Constants.ALLOWED_MOB_TYPES.contains(e.entity.type)) return
        val infernal = InfernalMob(e.entity as LivingEntity)

        infernal.abilities = mutableListOf(
            Glob.Constants.POWERS.values.random()
        )

        // Trigger events
        //TODO: Optimize ( for all events )
        if (!(infernal.abilities.map { it.onSpawn(e.entity) }.contains(true))) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onEntityDamaged(e: EntityDamageByEntityEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        if (!(getInfernalDataClass(e.entity)?.abilities!!.map { it.onDamaged(e.entity, e.damager, e.damage) }
                .contains(true))) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onEntityDeath(e: EntityDeathEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        //if (!(getInfernalDataClass(e.entity)?.abilities!!.map { it.onDeath(e.entity) }.contains(true))) { e.isCancelled = true}

        // Done differently to prevent a bug
        for (ability in getInfernalDataClass(e.entity)?.abilities!!) {
            if (ability.onDeath(e.entity)) {
                e.isCancelled
                break
            }
        }
    }

    @EventHandler
    fun onEntityTarget(e: EntityTargetEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        if (!(getInfernalDataClass(e.entity)?.abilities!!.map { it.onTarget(e.entity, e.reason) }.contains(true))) {
            e.isCancelled = true
        }
    }

//    @EventHandler
//    fun onEntityDespawn(e: EntityRemoveFromWorldEvent) {
//        // Check if entity is infernal
//        if (!isInfernalMob(e.entity)) return
//        // Trigger events
//        getInfernalDataClass(e.entity)?.abilities!!.map { it.onDespawn(e.entity) }
//    }

    @EventHandler
    fun onDamageEntity(e: EntityDamageByEntityEvent) {
        // Check if entity is infernal
        if (!isInfernalMob(e.damager)) return
        // Trigger events
        if (!(getInfernalDataClass(e.damager)?.abilities!!.map { it.onDamageEntity(e.entity, e.damager, e.damage) }
                .contains(true))) {
            e.isCancelled = true
        }
    }
}

