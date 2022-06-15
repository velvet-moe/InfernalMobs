package moe.velvet.infernalmobs.powers

import org.bukkit.enchantments.EnchantmentTarget.BREAKABLE
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.meta.Damageable

class Rust : Power {
    override val name: String
        get() = "rust"

    override fun onDamageEntity(e: EntityDamageByEntityEvent) {
        if (e.entity !is LivingEntity) return
        if (!BREAKABLE.includes((e.entity as LivingEntity).equipment!!.itemInMainHand.type)) return

        ((e.entity as LivingEntity).equipment!!.itemInMainHand.itemMeta as Damageable).damage =
                ((e.entity as LivingEntity).equipment!!.itemInMainHand.itemMeta as Damageable).damage + (5 until 10 + scaleFactor).random()
    }
}