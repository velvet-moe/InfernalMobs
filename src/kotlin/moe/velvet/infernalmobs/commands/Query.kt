package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.getInfernalDataClass
import moe.velvet.infernalmobs.isInfernalMob
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Query : CommandExecutor {
    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cYou must be a player to use this command.")
            return true
        }

        if (!sender.hasPermission("infernalmobs.query")) {
            sender.sendMessage("§cYou do not have permission to use this command.")
            return true
        }

        sender.getNearbyEntities(5.0, 5.0 ,5.0).forEach {
            if (it == null) return@forEach
            if (isInfernalMob(it)) {
                sender.sendMessage(it.name + " " + getInfernalDataClass(it)!!.abilities.map {e -> e.name }.toString())
            }
        }
        return true
    }
}