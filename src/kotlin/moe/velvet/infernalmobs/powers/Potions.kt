package moe.velvet.infernalmobs.powers

import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.ThrownPotion
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector


class Potions : Power {
    override val name: String
        get() = "potions"

    override fun onDamaged(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e !is LivingEntity) return true

        val stack = ItemStack(Material.POTION)
        val potion = stack.itemMeta as PotionMeta
        potion.addCustomEffect(PotionEffect(PotionEffectType.values().random(), 20 * 60, 0), true)
        stack.itemMeta = potion

        val sploc = e.location.clone()
        sploc.y = sploc.y + 3.0
        val thrown = damager.world.spawnEntity(sploc, EntityType.SPLASH_POTION) as ThrownPotion
        thrown.item = stack

        val direction = e.location.direction.also {
            it.normalize()
            it.add(Vector(0.0, 0.2, 0.0))
        }

        var dist = e.location.distance(damager.location)

        dist /= 15.0
        direction.multiply(dist)
        thrown.velocity = direction

//        potion.effects.clear()
//
//        potion.effects.add(PotionEffect(PotionEffectType.values().random(), 100, 0))
//        potion.velocity = pLoc.direction.multiply(1.5)


        return true
    }
}