package moe.velvet.infernalmobs.powers

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType.DAMAGE_RESISTANCE

class Bulwark : Power {
    override val name: String
        get() = "bulwark"

    override fun onSpawn(e: EntitySpawnEvent) {
        if (e.entity !is LivingEntity) return
        val entity = e.entity as LivingEntity

        entity.addPotionEffect(PotionEffect(DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2 * scaleFactor).withParticles(false))
    }
}