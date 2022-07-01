package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.LootFactory
import moe.velvet.infernalmobs.getInfernalDataClass
import moe.velvet.infernalmobs.isLoot
import moe.velvet.infernalmobs.utils.Glob
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Debug : CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, args: Array<out String>): Boolean {
        if (args.contains("kill")) {
            Glob.InfernalList.forEach {
                getInfernalDataClass(it)!!.bossbar.removeAll()
                it.remove()
            }
            return true
        }

        if (args.contains("WIPE_ALL_LOOT")) {
            var done: MutableList<String> = mutableListOf()
            Bukkit.getOfflinePlayers().forEach {
                it.player?.inventory?.forEach { i ->
                    if (i == null) return@forEach
                    if (isLoot(i)) {
                        done.add(i.toString())
                        i.amount = 0
                        i.type = Material.AIR
                    }
                }
            }

            Bukkit.getOnlinePlayers().forEach {
                it.player?.inventory?.forEach { i ->
                    if (i == null) return@forEach
                    if (isLoot(i)) {
                        done.add(i.toString())
                        i.amount = 0
                        i.type = Material.AIR
                    }
                }
            }
            p0.sendMessage(done.toString())
            return true
        }

        if (args.contains("weights")) {
            p0.sendMessage("${LootFactory.lootTable.map {"${it.key}: ${it.value.weight}"}}")
        }
        p0.sendMessage(Glob.InfernalList.map {"$it: ${it.location.toBlockLocation()}"}.toString())
        //p0.sendMessage(LootFactory.lootTable.keys.toString())
        //getInstance().logger.info(LootFactory.lootTable.keys.toString())
        //p0.sendMessage(LootFactory.lootWeights.toString())
        return true
    }

}