package moe.velvet.infernalmobs

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*


object LootFactory {
    var lootTable: HashMap<String, Loot> = HashMap()
    var lootWeights: MutableList<String> = mutableListOf()
    init {
        val lootConfig = getInstance().lootConfig!!
        val weights: MutableList<String> = mutableListOf()
        val table: HashMap<String, Loot> = HashMap()
        val list = lootConfig.getList("loot")

        // There is no sane way I can document this.
        if (list != null) {
            for (element in list) {
                for (i in element as Map<*, *>) {
                    val id = i.key as String?
                    val items = i.value as Map<*, *>
                    val name = items["name"] as String?
                    var weight = 1
                    if (items.contains("weight")) { weight = items["weight"] as Int }
                    val type = enumValueOf<LootType>((items["type"] as String?)!!.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                    val itemMaterial = enumValueOf<Material>((items["item"] as String?)!!.uppercase())
                    var lore: MutableList<Component>? = null
                    if (items.contains("lore")) {
                        lore = mutableListOf()
                        (items["lore"] as List<String>).forEach {
                            //getInstance().logger.info(it)
                            lore.add(Component.text(it))
                        }
                    }
                    //getInstance().logger.info(lore.toString())
                    val userEffects: MutableList<PotionEffect> = mutableListOf()
                    if (items.contains("user_effects")) {
                        for (k in items["user_effects"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                //logger.info(l.key.toString())
                                val effectType = PotionEffectType.getByName((l.key as String?)!!.uppercase())
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                userEffects.add(PotionEffect(effectType!!, 20 * 10, strength!! - 1, true, true))
                            }
                        }
                    }
                    val revengeEffects: MutableList<PotionEffect> = mutableListOf()
                    if (items.contains("revenge_effects")) {
                        for (k in items["revenge_effects"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                //logger.info(l.key.toString())
                                val effectType = PotionEffectType.getByName((l.key as String?)!!.uppercase())
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                val duration = (l.value as Map<*, *>)["duration"] as Int?
                                revengeEffects.add(PotionEffect(effectType!!, 20 * duration!!, strength!! - 1, true, true))
                            }
                        }
                    }
                    val hitEffects: MutableList<PotionEffect> = mutableListOf()
                    if (items.contains("hit_effects")) {
                        for (k in items["hit_effects"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                //logger.info(l.key.toString())
                                val effectType = PotionEffectType.getByName((l.key as String?)!!.uppercase())
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                val duration = (l.value as Map<*, *>)["duration"] as Int?
                                hitEffects.add(PotionEffect(effectType!!, 20 * duration!!, strength!! - 1, true, true))
                            }
                        }
                    }
                    val potionEffects: MutableList<PotionEffect> = mutableListOf()
                    if (items.contains("potion_effects")) {
                        for (k in items["potion_effects"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                //logger.info(l.key.toString())
                                val effectType = PotionEffectType.getByName((l.key as String?)!!.uppercase())
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                val duration = (l.value as Map<*, *>)["duration"] as Int?
                                potionEffects.add(PotionEffect(effectType!!, 20 * duration!!, strength!! - 1, true, true))
                            }
                        }
                    }
                    val enchantments: MutableList<Pair<Enchantment, Int>> = mutableListOf()
                    if (items.contains("enchantments")) {
                        for (k in items["enchantments"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                val enchantmentType = Enchantment.getByName((l.key as String?)!!.uppercase())
                                //getInstance().logger.info(enchantmentType.toString())
                                enchantments.add(Pair(enchantmentType!!, ((l.value as Map<*,*>)["level"] as Int?)!!))
                            }
                        }
                    }
                    //logger.info(userEffects.toString())
                    val loot = Loot()
                    loot.id = id!!.lowercase()
                    loot.lore = lore
                    loot.name = name!!
                    loot.weight = weight!!
                    loot.type = type
                    loot.material = itemMaterial
                    loot.potioneffects = potionEffects
                    loot.userEffects = userEffects
                    loot.revengeEffects = revengeEffects
                    loot.hitEffects = hitEffects
                    loot.enchantments = enchantments
                    table[id.lowercase()] = loot
                    (0..weight).forEach { _ -> weights.add(id.lowercase()) }
                }
            }
        }
        lootTable = table
        lootWeights = weights
    }
}