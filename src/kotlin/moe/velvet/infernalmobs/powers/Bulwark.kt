package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType.DAMAGE_RESISTANCE

class Bulwark : Power {
    override val name: String
        get() = "bulwark"

    override fun onSpawn(e: Entity): Boolean {
        if (e !is LivingEntity) return true

        e.addPotionEffect(PotionEffect(DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2 * scaleFactor).withParticles(false))
        return true
    }
}