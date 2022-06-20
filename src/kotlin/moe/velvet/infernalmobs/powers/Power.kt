package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityTargetEvent

interface Power {
    val name: String
    val scaleFactor: Int
        get() = 1

    companion object

    /// A return of `False` indicates that the event is cancelled.
    fun onSpawn(e: Entity): Boolean {
        return true
    }

    fun onDamaged(e: Entity, damager: Entity, amount: Double): Boolean {
        return true
    }

    fun onDeath(e: LivingEntity): Boolean {
        return true
    }

    fun onTarget(e: Entity, cause: EntityTargetEvent.TargetReason): Boolean {
        return true
    }

    //fun onDespawn(e: Entity): Boolean {return true}
    fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        return true
    }
}