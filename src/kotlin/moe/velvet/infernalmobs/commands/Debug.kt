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
import org.bukkit.entity.Player

class Debug : CommandExecutor {
    override fun onCommand(sender: CommandSender, p1: Command, p2: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cYou must be a player to use this command.")
            return true
        }

        if (!sender.hasPermission("infernalmobs.debug")) {
            sender.sendMessage("§cYou do not have permission to use this command.")
            return true
        }

        if (args.contains("kill")) {
            Glob.InfernalList.forEach {
                getInfernalDataClass(it)!!.bossbar.removeAll()
                it.remove()
            }
            return true
        }

        if (args.contains("WIPE_ALL_LOOT")) {
            val done: MutableList<String> = mutableListOf()
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
            sender.sendMessage(done.toString())
            return true
        }

        if (args.contains("weights")) {
            sender.sendMessage("${LootFactory.lootTable.map {"${it.key}: ${it.value.weight}"}}")
        }
        sender.sendMessage(Glob.InfernalList.map {"$it: ${it.location.toBlockLocation()}"}.toString())
        //p0.sendMessage(LootFactory.lootTable.keys.toString())
        //getInstance().logger.info(LootFactory.lootTable.keys.toString())
        //p0.sendMessage(LootFactory.lootWeights.toString())
        return true
    }

}