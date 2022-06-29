package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.getInfernalDataClass
import moe.velvet.infernalmobs.isInfernalMob
import moe.velvet.infernalmobs.utils.Glob
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FlushBossbars : CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (p0 !is Player) return true
        Glob.InfernalList.forEach {
            if (isInfernalMob(it)) {
                val data = getInfernalDataClass(it)!!
                data.bossbar.removePlayer(p0)
                data.bossbarPlayerList.remove(p0)
            }
        }
        return true
    }
}