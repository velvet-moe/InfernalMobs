package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity

class Storm : Power {
    override val name: String
        get() = "storm"

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e !is LivingEntity) return true
        e.world.strikeLightningEffect(e.location)
        e.damage(2.0) //TODO: balance
        e.fireTicks = 5 * 20
        return true
    }
}