package moe.velvet.infernalmobs.powers

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Quicksand : Power {
    override val name: String
        get() = "quicksand"

    override fun onDamageEntity(e: EntityDamageByEntityEvent) {
        if (e.entity !is LivingEntity) return
        (e.entity as LivingEntity).addPotionEffect(PotionEffect(PotionEffectType.SLOW, 20 * 5 * scaleFactor, 1))
    }
}