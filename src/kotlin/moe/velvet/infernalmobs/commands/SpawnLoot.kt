package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.LootFactory
import moe.velvet.infernalmobs.createLoot
import org.bukkit.command.CommandExecutor
import org.bukkit.entity.Player

class SpawnLoot : CommandExecutor {
    override fun onCommand(sender: org.bukkit.command.CommandSender, command: org.bukkit.command.Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        if (args.isEmpty()) return false
        val lootType = args[0]
        //getInstance().logger.info(LootFactory.lootTable[lootType.lowercase()]?.id ?: "")
        val item = LootFactory.lootTable[lootType.lowercase()]?.let { createLoot(it) } ?: return false
        sender.inventory.addItem(item)
        return true
    }
}