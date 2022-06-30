package moe.velvet.infernalmobs

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
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
                    if (!items.contains("type")) {
                        getInstance().logger.severe("type not found in loot $id")
                    }
                    if (!items.contains("item")) {
                        getInstance().logger.severe("item not found in loot $id")
                    }
                    var weight = 1
                    if (items.contains("weight")) { weight = items["weight"] as Int }
                    var amount: List<Int> = listOf(1)
                    if (items.contains("amount")) {
                        val data = items["amount"].toString()
                        if (data.contains("-")) {
                            var p = data.split("-")
                            amount = (p[0].toInt()..p[1].toInt()).toList()
                        } else {
                            amount = listOf(data.toIntOrNull() ?: 1)
                        }
                    }
                    val type = enumValueOf<LootType>((items["type"] as String?)!!.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                    val itemMaterial = enumValueOf<Material>((items["item"] as String?)!!.uppercase())
                    var lore: MutableList<Component>? = null

                    var name: String? = null
                    if (items.contains("name")) { name = items["name"] as String? }

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
                                val effectType = PotionEffectType.getByKey(NamespacedKey.minecraft((l.key as String?)!!.lowercase()))
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                userEffects.add(PotionEffect(effectType!!, 20 * 10, strength!! - 1))
                            }
                        }
                    }
                    val revengeEffects: MutableList<PotionEffect> = mutableListOf()
                    if (items.contains("revenge_effects")) {
                        for (k in items["revenge_effects"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                //logger.info(l.key.toString())
                                val effectType = PotionEffectType.getByKey(NamespacedKey.minecraft((l.key as String?)!!.lowercase()))
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                val duration = (l.value as Map<*, *>)["duration"] as Int?
                                revengeEffects.add(PotionEffect(effectType!!, 20 * duration!!, strength!! - 1))
                            }
                        }
                    }
                    val hitEffects: MutableList<PotionEffect> = mutableListOf()
                    if (items.contains("hit_effects")) {
                        for (k in items["hit_effects"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                //logger.info(l.key.toString())
                                val effectType = PotionEffectType.getByKey(NamespacedKey.minecraft((l.key as String?)!!.lowercase()))
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                val duration = (l.value as Map<*, *>)["duration"] as Int?
                                hitEffects.add(PotionEffect(effectType!!, 20 * duration!!, strength!! - 1))
                            }
                        }
                    }
                    val potionEffects: MutableList<PotionEffect> = mutableListOf()
                    if (items.contains("potion_effects")) {
                        for (k in items["potion_effects"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                //logger.info(l.key.toString())
                                val effectType = PotionEffectType.getByKey(NamespacedKey.minecraft((l.key as String?)!!.lowercase()))
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                val duration = (l.value as Map<*, *>)["duration"] as Int?
                                potionEffects.add(PotionEffect(effectType!!, 20 * duration!!, strength!! - 1))
                            }
                        }
                    }
                    val consumeEffects: MutableList<PotionEffect> = mutableListOf()
                    if (items.contains("consume_effects")) {
                        for (k in items["consume_effects"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                //logger.info(l.key.toString())
                                val effectType = PotionEffectType.getByKey(NamespacedKey.minecraft((l.key as String?)!!.lowercase()))
                                val strength = (l.value as Map<*, *>)["level"] as Int?
                                val duration = (l.value as Map<*, *>)["duration"] as Int?
                                consumeEffects.add(PotionEffect(effectType!!, 20 * duration!!, strength!! - 1))
                            }
                        }
                    }
                    // type, (chance, (range of possible leves))
                    val enchantments: MutableList<Pair<Enchantment, Pair<Int, List<Int>>>> = mutableListOf()
                    if (items.contains("enchantments")) {
                        for (k in items["enchantments"] as List<*>) {
                            for (l in k as Map<*, *>) {
                                val enchantmentType = Enchantment.getByKey(NamespacedKey.minecraft((l.key as String?)!!.lowercase()))
                                //getInstance().logger.info(enchantmentType.toString())
                                var level: MutableList<Int>
                                val data = ((l.value as Map<*, *>)["level"].toString())
//                                if ((l.value as Map<*, *>).contains("chance")) {
//                                    if ((0..100).random() >= ((l.value as Map<*, *>)["chance"] as Int)) {
//                                        continue
//                                    }
//                                }
//                                    var enchantmentLevel = ((l.value as Map<*, *>)["level"] as String)
//                                    val l = enchantmentLevel.split("-")
//                                    ((l[0].toInt())..(l[1].toInt())).random()
//
//                                    var enchantmentLevel = ((l.value as Map<*, *>)["level"] as String).toInt()
//                                    level = enchantmentLevel
//                                }
                                if (data.contains("-")) {
                                    var p = data.split("-")
                                    level = (p[0].toInt()..p[1].toInt()).toMutableList()
                                } else {
                                    level = mutableListOf(data.toIntOrNull() ?: 1)
                                }
                                var chance = 100
                                if ((l.value as Map<*, *>).contains("chance")) {
                                    chance = ((l.value as Map<*, *>)["chance"] as Int)
                                }
                                enchantments.add(Pair(enchantmentType!!, Pair(chance, level.toList())))
                            }
                        }
                    }
                    //logger.info(userEffects.toString())
                    val loot = Loot()
                    loot.id = id!!.lowercase()
                    loot.lore = lore
                    loot.name = name
                    loot.amount = amount
                    loot.weight = weight
                    loot.type = type
                    loot.material = itemMaterial
                    loot.potioneffects = potionEffects
                    loot.userEffects = userEffects
                    loot.revengeEffects = revengeEffects
                    loot.consumeEffects = consumeEffects
                    loot.hitEffects = hitEffects
                    loot.enchantments = enchantments
                    table[id.lowercase()] = loot
                    (0 until weight).forEach { _ -> weights.add(id.lowercase()) }
                }
            }
        }
        lootTable = table
        lootWeights = weights
    }
}