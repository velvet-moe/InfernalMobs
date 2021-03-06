package moe.velvet.infernalmobs.powers

import moe.velvet.infernalmobs.getInfernalDataClass
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity

class ExtraLife : Power {
    override val name: String
        get() = "extra_life"

    override fun onSpawn(e: Entity): Boolean {
        if (e !is LivingEntity) return true

        getInfernalDataClass(e)!!.lives = (1 until 2 + scaleFactor).random()
        return true
    }

    override fun onDeath(e: LivingEntity): Boolean {
        if (getInfernalDataClass(e)!!.lives <= 0) return true
        getInfernalDataClass(e)!!.lives--
        e.health = getInfernalDataClass(e)!!.maxHealth
        e.location.world.strikeLightningEffect(e.location)
        return false // prevent death ( Event cancelled )
    }
}