package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity

class Vengeance : Power {
    override val name: String
        get() = "vengeance"

    override fun onDamaged(e: Entity, damager: Entity, amount: Double): Boolean {
        if (damager !is LivingEntity) return true

        damager.damage(amount * 0.75)
        return true
    }
}