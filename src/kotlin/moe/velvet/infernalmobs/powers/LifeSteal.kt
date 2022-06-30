package moe.velvet.infernalmobs.powers

import moe.velvet.infernalmobs.getInfernalDataClass
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity

class LifeSteal : Power {
    override val name: String
        get() = "life_steal"

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        //if ((0 until 10-scaleFactor).random() != 0 ) return true
        if (damager !is LivingEntity) return true

        val appliedHeals = if (amount > getInfernalDataClass(damager)!!.maxHealth) 0.0 else amount

        damager.health = damager.health + appliedHeals

        return true
    }
}