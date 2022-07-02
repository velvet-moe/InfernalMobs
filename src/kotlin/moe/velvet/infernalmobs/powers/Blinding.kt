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

        e.addPotionEffect(PotionEffect(PotionEffectType.DARKNESS, 20 * 5, 0))
        return true
    }
}