package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Sapper : Power {
    override val name: String
        get() = "sapper"

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e !is LivingEntity) return true
        e.addPotionEffect(PotionEffect(PotionEffectType.HUNGER, 20 * 5, 2))

        return true
    }
}