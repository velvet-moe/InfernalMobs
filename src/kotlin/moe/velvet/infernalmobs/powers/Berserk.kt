package moe.velvet.infernalmobs.powers

import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Berserk : Power {
    override val name: String
        get() = "berserk"

    override fun onSpawn(e: Entity): Boolean {
        if (e !is LivingEntity) return true

        e.addPotionEffect(
            PotionEffect(
                PotionEffectType.INCREASE_DAMAGE,
                Integer.MAX_VALUE,
                1 * scaleFactor,
                false,
                false
            )
        )
        return true
    }
}