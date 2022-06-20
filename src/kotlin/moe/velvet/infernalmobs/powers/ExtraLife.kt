package moe.velvet.infernalmobs.powers

import moe.velvet.infernalmobs.getInfernalDataClass
import org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity

class ExtraLife : Power {
    override val name: String
        get() = "extra_life"

    override fun onSpawn(e: Entity): Boolean {
        if (e !is LivingEntity) return true

        getInfernalDataClass(e)!!.lives = (0 until 3 + scaleFactor).random()
        return true
    }

    override fun onDeath(e: LivingEntity): Boolean {
        if (getInfernalDataClass(e)!!.lives <= 0) return true
        getInfernalDataClass(e)!!.lives--
        e.health = e.getAttribute(GENERIC_MAX_HEALTH)!!.value
        e.location.world.strikeLightningEffect(e.location)
        return false // prevent death ( Event cancelled )
    }
}