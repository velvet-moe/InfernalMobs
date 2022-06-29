package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.LootFactory
import moe.velvet.infernalmobs.createLoot
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class SpawnLoot : TabExecutor {
    override fun onCommand(sender: org.bukkit.command.CommandSender, command: org.bukkit.command.Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        if (args.isEmpty()) return false
        val lootType = args[0]
        if (lootType == "*") {
            LootFactory.lootTable.forEach {
                sender.world.dropItem(sender.location, createLoot(it.value))
            }
        }
        //getInstance().logger.info(LootFactory.lootTable[lootType.lowercase()]?.id ?: "")
        val item = LootFactory.lootTable[lootType.lowercase()]?.let { createLoot(it) } ?: return false
        sender.inventory.addItem(item)
        return true
    }

    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>
    ): MutableList<String>? {
        return LootFactory.lootWeights.toSet().toMutableList()
    }
}