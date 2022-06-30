package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.getInfernalDataClass
import moe.velvet.infernalmobs.isInfernalMob
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Query : CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (p0 !is Player) return true
        p0.getNearbyEntities(5.0, 5.0 ,5.0).forEach {
            if (it == null) return@forEach
            if (isInfernalMob(it)) {
                p0.sendMessage(it.name + " " + getInfernalDataClass(it)!!.abilities.map {e -> e.name }.toString())
            }
        }
        return true
    }
}