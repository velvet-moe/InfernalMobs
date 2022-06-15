package moe.velvet.infernalmobs.powers

import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

class Armoured : Power {
    override val name: String
        get() = "armoured"

    override fun onSpawn(e: EntitySpawnEvent) {
        if (e.entity !is LivingEntity) return
        val entity = e.entity as LivingEntity
        entity.equipment?.armorContents = arrayOf(
            ItemStack(Material.DIAMOND_BOOTS),
            ItemStack(Material.DIAMOND_LEGGINGS),
            ItemStack(Material.DIAMOND_CHESTPLATE),
            ItemStack(Material.DIAMOND_HELMET)
        )
        val dsword = ItemStack(Material.DIAMOND_SWORD)
        dsword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2 * scaleFactor)
        entity.equipment?.bootsDropChance = 0.0F
        entity.equipment?.leggingsDropChance = 0.0F
        entity.equipment?.chestplateDropChance = 0.0F
        entity.equipment?.helmetDropChance = 0.0F
        entity.equipment?.itemInMainHandDropChance = 0.0F
    }
}