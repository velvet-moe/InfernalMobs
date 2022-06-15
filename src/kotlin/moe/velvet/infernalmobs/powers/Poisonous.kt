package moe.velvet.infernalmobs.powers

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Poisonous : Power {
    override val name: String
        get() = "poisonous"

    override fun onDamageEntity(e: EntityDamageByEntityEvent) {
        if (e.entity !is LivingEntity) return

        (e.entity as LivingEntity).addPotionEffect(PotionEffect(PotionEffectType.POISON, 20 * 5 * scaleFactor, 1))
    }
}