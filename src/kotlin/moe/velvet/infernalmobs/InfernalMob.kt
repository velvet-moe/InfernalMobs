package moe.velvet.infernalmobs

import org.bukkit.entity.Mob
import org.bukkit.potion.PotionEffect

class InfernalMob(mob: Class<out Mob>, abilities: Array<String>, effects: Array<PotionEffect>) {
    private val mob: Class<out Mob>
    private val abilities: Array<String>
    private val effects: Array<PotionEffect>

    init {
        this.mob = mob
        this.abilities = abilities
        this.effects = effects
    }

    fun getMob(): Class<out Mob> {
        return mob.interfaces.first { it == Mob::class.java } as Class<out Mob>
    }
}
