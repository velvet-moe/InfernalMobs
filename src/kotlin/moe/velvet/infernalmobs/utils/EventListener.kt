package moe.velvet.infernalmobs.utils

import moe.velvet.infernalmobs.*
import org.bukkit.Sound
import org.bukkit.entity.Ageable
import org.bukkit.entity.Item
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.*
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import kotlin.random.Random

class EventListener : Listener {
    // For infernal mobs
    @EventHandler
    fun onEntitySpawn(e: EntitySpawnEvent) {
        // Assign infernal status if chance is met
        if ((0..100).random() >= Glob.Constants.INFERNAL_CHANCE) return
        if (e.entity is Item) return
       //if (e.entity.entitySpawnReason == CreatureSpawnEvent.SpawnReason.CUSTOM)
        if (e.entity.entitySpawnReason != CreatureSpawnEvent.SpawnReason.NATURAL) return
        if (!Glob.Constants.ALLOWED_MOB_TYPES.contains(e.entity.type)) return
        if (e.entity is Ageable) {
            (e.entity as Ageable).setAdult()
        }

        var suc = false
        var level = 1
        (1..Glob.Constants.INFERNAL_MAX_LEVEL).forEach {
            if (suc) return@forEach
            if (Random.nextBoolean()) {
                if ((0..100).random() <= 90) {
                    suc = true
                    return@forEach
                }
            }
            level = it
        }
        val infernal = InfernalMob(e.entity as LivingEntity, level)

//        infernal.abilities = mutableListOf(
//            Glob.Constants.POWERS.values.random()
//        )
        (1..level).forEach { _ ->
            infernal.abilities.add(Glob.Constants.POWERS.values.random())
        }
        // Trigger events
        //TODO: Optimize ( for all events )
        if (!(infernal.abilities.map { it.onSpawn(e.entity) }.contains(true))) {
            e.isCancelled = true
        }
        Glob.InfernalList.add(e.entity)
    }

    @EventHandler
    fun onEntityDamaged(e: EntityDamageByEntityEvent) {
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        if (!(getInfernalDataClass(e.entity)?.abilities!!.map { it.onDamaged(e.entity, e.damager, e.damage) }
                .contains(true))) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onEntityDeath(e: EntityDeathEvent) {
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        // Done differently to prevent collision of multiple events
        for (ability in getInfernalDataClass(e.entity)?.abilities!!) {
            if (!ability.onDeath(e.entity)) {
                e.isCancelled = true
                return
            }
        }
        getInfernalDataClass(e.entity)!!.bossbar.isVisible = false
        val drop = createRandomLoot()
        e.entity.world.dropItemNaturally(e.entity.location, drop)
    }

    @EventHandler
    fun onEntityTarget(e: EntityTargetEvent) {
        if (!isInfernalMob(e.entity)) return
        // Trigger events
        if (!(getInfernalDataClass(e.entity)?.abilities!!.map { it.onTarget(e.entity, e.reason) }.contains(true))) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onDamageEntity(e: EntityDamageByEntityEvent) {
        if (!isInfernalMob(e.damager)) return
        // Trigger events
        if (!(getInfernalDataClass(e.damager)?.abilities!!.map { it.onDamageEntity(e.entity, e.damager, e.damage) }
                .contains(true))) {
            e.isCancelled = true
        }
    }
    // for loot

    @EventHandler
    fun onUserDamaged(e: EntityDamageByEntityEvent) {
        if (e.entity !is Player) return
        if (e.damager !is LivingEntity) return
        (e.entity as Player).inventory.withIndex().forEach {
            if (it.value == null) return@forEach

            if (isLoot(it.value)) {
                val loot = getLoot(it.value)
//                if (loot != null) {
//                    loot.UserDamagerEffects?.let { it1 -> (e.damager as LivingEntity).addPotionEffects(it1) }
//                    loot.UserDamagedEffects?.let {it1 -> (e.entity as LivingEntity).addPotionEffects(it1)}
//                }
                if (loot != null) {
                    when (loot.type) {
                        LootType.Weapon -> {
                            if ((e.entity as Player).inventory.heldItemSlot == it.index) {
                                loot.revengeEffects?.let { it1 -> (e.damager as LivingEntity).addPotionEffects(it1) }
                            }
                        }
                        LootType.Charm -> {
                            if (Glob.Constants.ALLOWED_CHARM_SLOTS.contains(it.index)) {
                                loot.revengeEffects?.let { it1 -> (e.damager as LivingEntity).addPotionEffects(it1) }
                            }
                            if (Glob.Constants.MAINHAND_CHARMS_ENABLED && (e.entity as Player).inventory.heldItemSlot == it.index) {
                                loot.revengeEffects?.let { it1 -> (e.damager as LivingEntity).addPotionEffects(it1) }
                            }
                        }
                        LootType.Armor -> {
                            if ((e.entity as Player).equipment.armorContents.contains(it.value)) {
                                loot.revengeEffects?.let { it1 -> (e.damager as LivingEntity).addPotionEffects(it1) }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    @EventHandler
    fun onMunch(e: PlayerItemConsumeEvent) {
        if (isLoot(e.item)) {
            when (getLoot(e.item)!!.type) {
                LootType.Food -> {
                    getLoot(e.item)!!.consumeEffects?.let { e.player.addPotionEffects(it) }
                    e.player.world.playSound(e.player.location, Sound.ENTITY_GOAT_PREPARE_RAM, 1.0F, 1.0F)
                }
                LootType.Potion -> {
                    return
                }
                else -> {
                    e.player.sendMessage("§c[Infernal Mobs] Eating charms is prevented")
                    e.player.world.strikeLightningEffect(e.player.location)
                    e.isCancelled = true
                    return
                }
            }
        }
    }

    @EventHandler
    fun onHitEffects(e: EntityDamageByEntityEvent) { // Not quite sure what to call this one
        if (e.damager !is Player) return
        if (e.entity !is LivingEntity) return
        (e.damager as Player).inventory.withIndex().forEach {
            if (it.value == null) return@forEach

            if (isLoot(it.value)) {
                val loot = getLoot(it.value)
//                if (loot != null) {
//                    loot.UserDamagerEffects?.let { it1 -> (e.damager as LivingEntity).addPotionEffects(it1) }
//                    loot.UserDamagedEffects?.let {it1 -> (e.entity as LivingEntity).addPotionEffects(it1)}
//                }
                if (loot != null) {
                    when (loot.type) {
                        LootType.Weapon -> {
                            if ((e.damager as Player).inventory.heldItemSlot == it.index) {
                                loot.hitEffects?.let { it1 -> (e.entity as LivingEntity).addPotionEffects(it1) }
                            }
                        }
                        LootType.Charm -> {
                            if (Glob.Constants.ALLOWED_CHARM_SLOTS.contains(it.index)) {
                                loot.hitEffects?.let { it1 -> (e.entity as LivingEntity).addPotionEffects(it1) }
                            }
                            if (Glob.Constants.MAINHAND_CHARMS_ENABLED && (e.damager as Player).inventory.heldItemSlot == it.index) {
                                loot.hitEffects?.let { it1 -> (e.entity as LivingEntity).addPotionEffects(it1) }
                            }
                        }
                        LootType.Armor -> {
                            if ((e.damager as Player).equipment.armorContents.contains(it.value)) {
                                loot.hitEffects?.let { it1 -> (e.entity as LivingEntity).addPotionEffects(it1) }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    @EventHandler
    fun onPlace(e: PlayerInteractEvent) {
        if (e.item?.let { isLoot(it) } == true) {
            if (e.action != Action.RIGHT_CLICK_BLOCK) return
            when (getLoot(e.item!!)?.type) {
                LootType.Charm -> {
                     e.player.sendMessage("§c[Infernal Mobs] You can't place charms!")
                    e.isCancelled = true
                }
                else -> {}
            }
        }
    }
}

