package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Weakness : Power {
    override val name: String
        get() = "weakness"

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e !is LivingEntity) return true
        e.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 20 * 5, 0))

        return true
    }
}