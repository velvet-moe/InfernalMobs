package moe.velvet.infernalmobs.utils

import moe.velvet.infernalmobs.getInstance
import moe.velvet.infernalmobs.powers.*
import org.bukkit.boss.BarColor
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType

object Glob {
    object Constants {
        val INFERNAL_CHANCE: Int
        val LOOT_DROP_CHANCE: Int
        val INFERNAL_NAME_PREFIX: String
        val INFERNAL_NAME_SUFFIX: String
        // const val SCALING_FACTOR = 1
        val ALLOWED_MOB_TYPES: List<EntityType>
        val POWERS: HashMap<String, Power>
        val ALLOWED_CHARM_SLOTS: List<Int> // 40 is offhand
        val MAINHAND_CHARMS_ENABLED: Boolean
        val BOSSBAR_COLOR: BarColor
        val INFERNAL_HEALTH_SCALE_FACTOR: Double
        val INFERNAL_MAX_LEVEL: Int
        val INFERNAL_HEALTH_LEVEL_SCALE_FACTOR: Double

        init {
            val config = getInstance().config
            this.INFERNAL_CHANCE = config.getInt("infernalSpawnChance")
            this.LOOT_DROP_CHANCE = config.getInt("globalLootDropChance")
            this.INFERNAL_NAME_PREFIX = config.getString("infernalNamePrefix") ?: ""
            this.INFERNAL_NAME_SUFFIX = config.getString("infernalNameSuffix") ?: ""
            this.ALLOWED_MOB_TYPES = config.getStringList("allowedInfernalMobTypes").map { enumValueOf(it) }
            this.POWERS = hashMapOf(
                Armoured().name to Armoured(),
                Berserk().name to Berserk(),
                Blinding().name to Blinding(),
                Bulwark().name to Bulwark(),
                ExtraLife().name to ExtraLife(),
                LifeSteal().name to LifeSteal(),
                Poisonous().name to Poisonous(),
                Quicksand().name to Quicksand(),
                Rust().name to Rust(),
                Sapper().name to Sapper(),
                Sprint().name to Sprint(),
                Vengeance().name to Vengeance(),
                Storm().name to Storm(),
                Ender().name to Ender(),
                Potions().name to Potions(),
                Webber().name to Webber(),
                Weakness().name to Weakness(),
                Withering().name to Withering(),
                Wraith().name to Wraith()
            )
            val confPowers = config.getStringList("allowedInfernalTypes")
            this.POWERS.forEach {
                if (confPowers.map { it.lowercase() }.contains(it.key)) return@forEach
                this.POWERS.remove(it.key)
            }
            this.ALLOWED_CHARM_SLOTS = config.getIntegerList("allowedCharmSlots")
            this.MAINHAND_CHARMS_ENABLED = config.getBoolean("mainHandCharmsEnabled")
            this.BOSSBAR_COLOR = enumValueOf(config.getString("bossBarColor")!!)
            this.INFERNAL_HEALTH_SCALE_FACTOR = config.getDouble("infernalHealthScaleFactor")
            this.INFERNAL_MAX_LEVEL = config.getInt("infernalMaxLevel")
            this.INFERNAL_HEALTH_LEVEL_SCALE_FACTOR = config.getDouble("infernalHealthLevelScaleFactor")
        }
    }
    var InfernalList: MutableList<Entity> = mutableListOf()
}