package moe.velvet.infernalmobs.powers

import moe.velvet.infernalmobs.getInfernalDataClass
import org.bukkit.entity.LivingEntity
import org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntitySpawnEvent

class ExtraLife : Power {
    override val name: String
        get() = "extra_life"

    override fun onSpawn(e: EntitySpawnEvent) {
        if (e.entity !is LivingEntity) return
        val entity = e.entity as LivingEntity

        getInfernalDataClass(entity)!!.lives = (0 until 3+scaleFactor).random()
    }

    override fun onDeath(e: EntityDeathEvent) {
        if (getInfernalDataClass(e.entity)!!.lives <= 0) return
        e.isCancelled = true
        e.entity.health = e.entity.getAttribute(GENERIC_MAX_HEALTH)!!.value

    }
}