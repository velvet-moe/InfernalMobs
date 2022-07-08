package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.getInfernalDataClass
import moe.velvet.infernalmobs.isInfernalMob
import moe.velvet.infernalmobs.utils.Glob
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FlushBossbars : CommandExecutor {
    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cYou must be a player to use this command.")
            return true
        }

        if (!sender.hasPermission("infernalmobs.flushbossbars")) {
            sender.sendMessage("§cYou do not have permission to use this command.")
            return true
        }

        Glob.InfernalList.forEach {
            if (isInfernalMob(it)) {
                val data = getInfernalDataClass(it)!!
                data.bossbar.removePlayer(sender)
            }
        }

        return true
    }
}