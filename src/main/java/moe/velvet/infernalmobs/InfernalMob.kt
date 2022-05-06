package moe.velvet.infernalmobs

import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Mob
import org.bukkit.potion.PotionEffect

class InfernalMob(
    private var entity: Class<out Mob>,
    var abilityList: Array<String>,
    var effects: Array<PotionEffect>,
) {


    fun spawn(location: Location) {
        val mob = location.world.spawn(location, entity)

        // TODO: Add abilities
//        for (ability in abilityList) {
//            mob.addPotionEffect(PotionEffect(PotionEffectType.getByName(ability), Integer.MAX_VALUE, 1))
//        }
        for (effect in effects) {
            mob.addPotionEffect(effect)
        }

        // set mob name
        mob.customName = ("Infernal" + mob.type.name.uppercase())
        mob.isCustomNameVisible = true

        // set mob health
        mob.health = 2.0 * mob.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value!!
    }
    //}

    fun getString(): String {
        val effects = effects.joinToString(", ") { it.type.name }
        return "InfernalMob(entity=${entity.name}, abilityList=${abilityList.joinToString(", ")}, effects=[$effects])"
    }
}