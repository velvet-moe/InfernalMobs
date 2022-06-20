package moe.velvet.infernalmobs.utils

import moe.velvet.infernalmobs.powers.*
import org.bukkit.entity.EntityType

object Glob {
    object Constants {
        const val INFERNAL_META_KEY = "infernal_meta"
        const val INFERNAL_CHANCE = 10 // out of 100

        // const val SCALING_FACTOR = 1
        val ALLOWED_MOB_TYPES = listOf(
            EntityType.ZOMBIE,
            EntityType.SPIDER,
            EntityType.CAVE_SPIDER,
            EntityType.CREEPER,
            EntityType.DROWNED,
            EntityType.SKELETON,
            EntityType.WITHER_SKELETON,
            EntityType.BLAZE,
            EntityType.ENDERMAN,
            EntityType.ENDERMITE,
            EntityType.EVOKER,
            EntityType.GHAST,
            EntityType.GUARDIAN,
            EntityType.HOGLIN,
            EntityType.HUSK,
            EntityType.MAGMA_CUBE,
            EntityType.PHANTOM,
            EntityType.PIGLIN,
            EntityType.PIGLIN_BRUTE,
            EntityType.PILLAGER,
            EntityType.PUFFERFISH,
            EntityType.RAVAGER,
            EntityType.SHULKER,
            EntityType.SILVERFISH,
            EntityType.SLIME,
            EntityType.STRAY,
            EntityType.VEX,
            EntityType.VINDICATOR,
            EntityType.WARDEN,
            EntityType.WITCH,
            EntityType.WITHER_SKELETON,
            EntityType.ZOGLIN,
            EntityType.ZOMBIE_VILLAGER,
            EntityType.ZOMBIFIED_PIGLIN,
            EntityType.ENDER_DRAGON,
            EntityType.WITHER,
        )
        val POWERS = hashMapOf(
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
    }
}