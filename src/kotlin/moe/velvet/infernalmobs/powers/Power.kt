package moe.velvet.infernalmobs.powers

import org.bukkit.entity.LivingEntity

interface Powers {
    val name: String

    fun grant(entity: LivingEntity)
    fun envoke_suffering(entity: LivingEntity)
}