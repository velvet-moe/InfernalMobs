package moe.velvet.infernalmobs

import moe.velvet.infernalmobs.powers.Power
import moe.velvet.infernalmobs.utils.Glob
import net.kyori.adventure.text.Component
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.metadata.FixedMetadataValue

data class InfernalMob(
    val entity: LivingEntity,
    val level: Int = 1
) {

    var abilities: MutableList<Power> = mutableListOf() // Default Power
    var lives = 0

    init {
        entity.setMetadata("${Glob.Constants.INFERNAL_META_KEY}-dataclass", FixedMetadataValue(getInstance(), this))
        entity.customName(Component.text("${Glob.Constants.INFERNAL_NAME_PREFIX}${entity.name}"))
    }
}

fun isInfernalMob(entity: Entity): Boolean {
    return entity.hasMetadata("${Glob.Constants.INFERNAL_META_KEY}-dataclass")
}

fun getInfernalDataClass(entity: Entity): InfernalMob? {
    return entity.getMetadata("${Glob.Constants.INFERNAL_META_KEY}-dataclass").firstOrNull()?.value() as? InfernalMob
}