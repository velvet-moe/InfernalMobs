package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Sprint : Power {
    override val name: String
        get() = "sprint"

    override fun onSpawn(e: Entity): Boolean {
        (e as LivingEntity).addPotionEffect(
            PotionEffect(
                PotionEffectType.SPEED,
                Integer.MAX_VALUE,
                0 + scaleFactor,
                true,
                false
            )
        )
        return true
    }
}