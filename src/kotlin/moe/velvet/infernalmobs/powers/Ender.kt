package moe.velvet.infernalmobs.powers

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType

class Ender : Power {
    override val name: String
        get() = "ender"

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        if (damager.type == EntityType.ENDERMAN) return true
        damager.teleport(
            Location(
                e.world,
                e.location.x + (0..5).random() - 2.5,
                e.location.y,
                e.location.z + (0..5).random() - 2.5
            ).toHighestLocation().add(0.0, 0.5, 0.0)
        )
        return true
    }

    override fun onDamaged(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e.type == EntityType.ENDERMAN) return true
        e.teleport(
            Location(
                e.world,
                e.location.x + (0..5).random() - 2.5,
                e.location.y,
                e.location.z + (0..5).random() - 2.5
            ).toHighestLocation().add(0.0, 0.5, 0.0)
        )
        return true
    }
}