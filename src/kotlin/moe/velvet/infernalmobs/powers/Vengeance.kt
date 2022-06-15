package moe.velvet.infernalmobs.powers

import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

class Vengeance : Power {
    override val name: String
        get() = "vengeance"

    override fun onDamage(e: EntityDamageEvent) {
        e.damage
    }
}