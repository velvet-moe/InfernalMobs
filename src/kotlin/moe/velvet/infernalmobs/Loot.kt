package moe.velvet.infernalmobs

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionEffect
import kotlin.properties.Delegates

class Loot {
    lateinit var id: String
    var name: String? = null
    lateinit var type: LootType
    lateinit var amount: List<Int>
    var weight by Delegates.notNull<Int>()
    var lore: List<Component>? = null
    var userEffects: List<PotionEffect>? = null // effects on the user of the loot
    var revengeEffects: List<PotionEffect>? = null // effects on people who damage the user
    var hitEffects: List<PotionEffect>? = null // effects on people who are damaged by the user
    var consumeEffects: List<PotionEffect>? = null
    var enchantments: List<Pair<Enchantment, Pair<Int, List<Int>>>>? = null
    var potioneffects: List<PotionEffect>? = null
    lateinit var material: Material // the material (icon) of the loot
}

fun createLoot(type: Loot, amount: Int = 1): ItemStack {
    val item = ItemStack(type.material, amount * type.amount.random())
    val meta = item.itemMeta
    if (meta is PotionMeta) {
        type.potioneffects?.forEach {
            meta.addCustomEffect(it, true)
        }
    }
    type.name?.let { meta.displayName(Component.text(it))}
    //getInstance().logger.info(type.Enchantments.toString())
    type.enchantments?.forEach {
        if ((0..100).random() >= it.second.first) return@forEach
        meta.addEnchant(it.first, it.second.second.random(), true)
    }
    //getInstance().logger.info(type.lore.toString())
    meta.lore(type.lore)
    meta.persistentDataContainer.set(NamespacedKey(getInstance(), "loot-type"), PersistentDataType.STRING, type.id.lowercase())
    item.setItemMeta(meta)
    return item
}

fun createRandomLoot(weight: Int, amount: Int = 1): ItemStack {
//    val lootType = LootFactory.lootWeights.random()
    //var possibleLoot: Pair<Int, String> = Pair(0, "")
//    (0..(if (weight == 1) weight else weight * 2)).forEach { _ ->
//        val l = LootFactory.lootWeights.random()
//        var c = LootFactory.lootWeights.count { it == l}
//        possibleLoot = if (c > possibleLoot.first) possibleLoot else Pair(c, l)
//    }
    var loot: MutableList<Loot> = mutableListOf()
    (1..(if (weight == 1) weight else weight * 2)).forEach{ _ -> loot.add(LootFactory.lootTable.values.random()) }
    loot.removeIf { it.weight == 0 }
    loot.sortBy { it.weight }
    return createLoot(loot[0], amount)
}

fun isLoot(item: ItemStack): Boolean {
    //getInstance().logger.info(item.itemMeta.persistentDataContainer.keys.toString())
    return item.itemMeta.persistentDataContainer.has(NamespacedKey(getInstance(), "loot-type"))
}

fun getLoot(item: ItemStack): Loot? {
    //if (!isLoot(item)) return null
    val type = item.itemMeta.persistentDataContainer.get(NamespacedKey(getInstance(), "loot-type"), PersistentDataType.STRING)
    //getInstance().logger.info(LootFactory.lootTable[type!!.lowercase()]?.name ?: "null")
    return LootFactory.lootTable[type!!.lowercase()]
}

enum class LootType {
    Charm,
    Weapon,
    Armor,
    Potion,
    Item,
    Food
}