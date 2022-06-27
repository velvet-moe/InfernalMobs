package moe.velvet.infernalmobs.utils

import moe.velvet.infernalmobs.LootType
import moe.velvet.infernalmobs.getLoot
import moe.velvet.infernalmobs.isLoot
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class LootEffectRunner : BukkitRunnable(){
    override fun run() {
        if (Bukkit.getOnlinePlayers().isEmpty()) return
        Bukkit.getOnlinePlayers().forEach { p ->
            if (p.inventory.isEmpty) return@forEach
            p.inventory.withIndex().forEach {
                if (it.value == null) return@forEach

                if (isLoot(it.value)) {
                    val loot = getLoot(it.value)
                    when (loot!!.type) {
                        LootType.Charm -> {
                            if (Glob.Constants.ALLOWED_CHARM_SLOTS.contains(it.index)) {
                                loot.userEffects?.let { it1 -> p.addPotionEffects(it1) }
                            }
                            if (Glob.Constants.MAINHAND_CHARMS_ENABLED && p.inventory.heldItemSlot == it.index) {
                                loot.userEffects?.let { it1 -> p.addPotionEffects(it1) }
                            }
                        }
                        LootType.Potion -> {
                            //loot.UserEffects?.let { it1 -> p.addPotionEffects(it1) }
                        }
                        LootType.Weapon -> {
                            if (it.index == p.inventory.heldItemSlot) {
                                loot.userEffects?.let { it1 -> p.addPotionEffects(it1) }
                            }
                        }
                        LootType.Armor -> {
                            if (p.equipment.armorContents.contains(it.value)) {
                                loot.userEffects?.let { it1 -> p.addPotionEffects(it1)}
                            }
                        }
                        LootType.Item -> {

                        }
                    }
                }
            }
        }
    }
}