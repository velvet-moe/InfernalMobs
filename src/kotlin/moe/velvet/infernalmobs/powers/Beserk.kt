package moe.velvet.infernalmobs.powers

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Beserk : Power {
    override val name: String
        get() = "beserk"

    override fun onSpawn(e: EntitySpawnEvent) {
        if (e.entity !is LivingEntity) return
        val entity = e.entity as LivingEntity

        entity.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1 * scaleFactor, false, false))
    }
}