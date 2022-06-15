package moe.velvet.infernalmobs.powers

import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Blinding : Power {
    override val name: String
        get() = "blinding"

    override fun onDamageEntity(e: EntityDamageByEntityEvent) {
        if (e.entity !is LivingEntity) return
        val entity = e.entity as LivingEntity

        entity.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 20 * 15 * scaleFactor, 1 * scaleFactor - 1))
    }
}