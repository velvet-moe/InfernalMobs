package moe.velvet.infernalmobs.powers

import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Wraith : Power {
    override val name: String
        get() = "wraith"

    override fun onSpawn(e: Entity): Boolean {
        if (e !is LivingEntity) return true

        e.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false))
        e.addPotionEffect(PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false))
        e.equipment?.armorContents = arrayOf(
            ItemStack(Material.AIR),
            ItemStack(Material.AIR),
            ItemStack(Material.AIR),
            ItemStack(Material.CARVED_PUMPKIN)
        )
        return true
    }
}