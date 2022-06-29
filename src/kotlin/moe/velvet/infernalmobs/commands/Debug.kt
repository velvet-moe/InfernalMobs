package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.LootFactory
import moe.velvet.infernalmobs.getInstance
import moe.velvet.infernalmobs.utils.Glob
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Debug : CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        p0.sendMessage(Glob.InfernalList.toString())
        p0.sendMessage(LootFactory.lootTable.keys.toString())
        getInstance().logger.info(LootFactory.lootTable.keys.toString())
        //p0.sendMessage(LootFactory.lootWeights.toString())
        return true
    }

}