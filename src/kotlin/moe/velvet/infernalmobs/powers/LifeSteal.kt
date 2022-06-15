package moe.velvet.infernalmobs.powers

import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent

class LifeSteal : Power {
    override val name: String
        get() = "life_steal"

    override fun onDamageEntity(e: EntityDamageByEntityEvent) {
        if ((0 until 10-scaleFactor).random() != 0 ) return
        if (e.entity !is LivingEntity) return
        val entity = e.entity as LivingEntity

        val appliedHeals = if (e.damage > entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value) 0.0 else e.damage

        entity.health = entity.health + appliedHeals
    }
}