package moe.velvet.infernalmobs.powers

import org.bukkit.entity.LivingEntity
import org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH

class OneUp : Power {
    override val name: String
        get() = 'oneup'

    override fun grant_power(entity: LivingEntity) {
        entity.health = entity.getAttribute(GENERIC_MAX_HEALTH).value
    }
}