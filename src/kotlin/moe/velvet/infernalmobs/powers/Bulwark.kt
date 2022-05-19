package moe.velvet.infernalmobs.powers

import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType.DAMAGE_RESISTANCE

class Bullwark : Power {
    override val name: String
        get() = "bullwark"

    override fun grant_power(entity: LivingEntity) {
        entity.addPotionEffect(PotionEffect(DAMAGE_RESISTANCE, 999999, 2).withParticles(false))
    }
}