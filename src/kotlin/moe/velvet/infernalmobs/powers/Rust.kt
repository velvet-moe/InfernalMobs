package moe.velvet.infernalmobs.powers

import org.bukkit.enchantments.EnchantmentTarget.BREAKABLE
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.meta.Damageable

class Rust : Power {
    override val name: String
        get() = "rust"

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e !is LivingEntity) return true
        if (!BREAKABLE.includes(e.equipment!!.itemInMainHand.type)) return true

        (e.equipment!!.itemInMainHand.itemMeta as Damageable).damage =
            (e.equipment!!.itemInMainHand.itemMeta as Damageable).damage + (5 until 10 + scaleFactor).random()

        return true
    }
}