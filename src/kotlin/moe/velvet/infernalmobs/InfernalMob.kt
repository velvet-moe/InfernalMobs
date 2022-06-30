package moe.velvet.infernalmobs

import moe.velvet.infernalmobs.powers.Power
import moe.velvet.infernalmobs.utils.Glob
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue

data class InfernalMob(
    val entity: LivingEntity,
    var level: Int = 1
) {

    var abilities: MutableList<Power> = mutableListOf() // Default Power
    var lives = 0
    var bossbar = Bukkit.createBossBar("${Glob.Constants.INFERNAL_NAME_PREFIX}${entity.name}${Glob.Constants.INFERNAL_NAME_SUFFIX} ${convertToRoman(level)}", Glob.Constants.BOSSBAR_COLOR, BarStyle.SEGMENTED_20)
    var bossbarPlayerList: MutableList<Player> = mutableListOf()
    var maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value * Glob.Constants.INFERNAL_HEALTH_SCALE_FACTOR * (level * Glob.Constants.INFERNAL_HEALTH_LEVEL_SCALE_FACTOR)

    init {
        if (maxHealth >= 1023.0) maxHealth = 1023.0
        entity.equipment?.setItemInMainHand(null)
        entity.setMetadata("infernal-dataclass", FixedMetadataValue(getInstance(), this))
        entity.customName(Component.text("${Glob.Constants.INFERNAL_NAME_PREFIX}${entity.name}${Glob.Constants.INFERNAL_NAME_SUFFIX} ${convertToRoman(level)}"))
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = maxHealth
        entity.health =  entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        entity.removeWhenFarAway = true
    }

    private fun convertToRoman(number: Int): String {
        val numbers = linkedMapOf(
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
        )
        for (i in numbers.keys){
            if (number >= i) {
                return numbers[i] + convertToRoman(number - i)
            }
        }
        return ""
    }
}

fun isInfernalMob(entity: Entity): Boolean {
    return entity.hasMetadata("infernal-dataclass")
}

fun getInfernalDataClass(entity: Entity): InfernalMob? {
    return entity.getMetadata("infernal-dataclass").firstOrNull()?.value() as? InfernalMob
}