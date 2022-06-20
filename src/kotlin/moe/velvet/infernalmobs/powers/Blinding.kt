package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Blinding : Power {
    override val name: String
        get() = "blinding"

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e !is LivingEntity) return true

        e.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 20 * 15 * scaleFactor, 1 * scaleFactor - 1))
        return true
    }
}