package moe.velvet.infernalmobs.powers

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack

class Armoured : Power {
    override val name: String
        get() = "armoured"

    override fun onSpawn(e: Entity): Boolean {
        if (e !is LivingEntity) return true
        e.equipment?.armorContents = arrayOf(
            ItemStack(Material.DIAMOND_BOOTS),
            ItemStack(Material.DIAMOND_LEGGINGS),
            ItemStack(Material.DIAMOND_CHESTPLATE),
            ItemStack(Material.DIAMOND_HELMET)
        )
        val dsword = ItemStack(Material.DIAMOND_SWORD)
        dsword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2 * scaleFactor)
        e.equipment?.setItemInMainHand(dsword)

        e.equipment?.bootsDropChance = 0.0F
        e.equipment?.leggingsDropChance = 0.0F
        e.equipment?.chestplateDropChance = 0.0F
        e.equipment?.helmetDropChance = 0.0F
        e.equipment?.itemInMainHandDropChance = 0.0F

        return true
    }
}