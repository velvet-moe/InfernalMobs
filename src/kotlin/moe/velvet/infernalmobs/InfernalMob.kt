package moe.velvet.infernalmobs

import moe.velvet.infernalmobs.powers.Power
import moe.velvet.infernalmobs.utils.Glob
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue

data class InfernalMob(
    val entity: LivingEntity,
    val level: Int = 1
) {

    var abilities: MutableList<Power> = mutableListOf() // Default Power
    var lives = 0
    var bossbar = Bukkit.createBossBar("${Glob.Constants.INFERNAL_NAME_PREFIX}${entity.name}${Glob.Constants.INFERNAL_NAME_SUFFIX}", BarColor.RED, BarStyle.SEGMENTED_20)
    var bossbarPlayerList: MutableList<Player> = mutableListOf()

    init {
        entity.setMetadata("infernal-dataclass", FixedMetadataValue(getInstance(), this))
        entity.customName(Component.text("${Glob.Constants.INFERNAL_NAME_PREFIX}${entity.name}${Glob.Constants.INFERNAL_NAME_SUFFIX}"))
        entity.removeWhenFarAway = true
    }
}

fun isInfernalMob(entity: Entity): Boolean {
    return entity.hasMetadata("infernal-dataclass")
}

fun getInfernalDataClass(entity: Entity): InfernalMob? {
    return entity.getMetadata("infernal-dataclass").firstOrNull()?.value() as? InfernalMob
}