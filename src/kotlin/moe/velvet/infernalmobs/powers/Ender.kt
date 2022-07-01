package moe.velvet.infernalmobs.powers

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity

class Ender : Power {
    override val name: String
        get() = "ender"

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        if (damager.type == EntityType.ENDERMAN) return true
        var loc = Location(
            e.world,
            e.location.x + (0..5).random() - 2.5,
            e.location.y,
            e.location.z + (0..5).random() - 2.5
        )

        while (loc.block.type != Material.AIR && loc.add(0.0, 2.0, 0.0).block.type != Material.AIR) {
            loc = loc.add(0.0, 2.0, 0.0)
        }

        damager.teleport(
            loc.add(0.0, 0.5, 0.0)
        )
        return true
    }

    override fun onDamaged(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e.type == EntityType.ENDERMAN) return true
        if ((e as LivingEntity).health - amount <= 0) return true
        var loc = Location(
            e.world,
            e.location.x + (0..5).random() - 2.5,
            e.location.y,
            e.location.z + (0..5).random() - 2.5
        )

        while (loc.block.type != Material.AIR && loc.add(0.0, 2.0, 0.0).block.type != Material.AIR) {
            loc = loc.add(0.0, 2.0, 0.0)
        }

        e.teleport(
            loc.add(0.0, 0.5, 0.0)
        )
        return true
    }
}