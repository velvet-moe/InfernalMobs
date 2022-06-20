package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.InfernalMob
import moe.velvet.infernalmobs.utils.Glob
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player


@Suppress("NAME_SHADOWING")
class SpawnInfernal : TabExecutor {
    override fun onTabComplete(
        sender: CommandSender,
        cmd: Command,
        commandLabel: String,
        args: Array<out String>
    ): MutableList<String>? {
        val list: MutableList<String> = ArrayList()
        // Now, it's just like any other command.
        // Check if the sender is a player.
        // Now, it's just like any other command.
        // Check if the sender is a player.
        if (sender is Player) {
            // Check if the command is "something."
            if (cmd.name.lowercase() == "spawninfernal" || cmd.name.lowercase() == "si") {
                // If the player has not typed anything in
                if (args.size == 1) {
                    // Add a list of words that you'd like to show up
                    // when the player presses tab.
                    Glob.Constants.ALLOWED_MOB_TYPES.forEach {
                        list.add(it.name.lowercase())
                    }
                    // Sort them alphabetically.
                    list.sort()
                    // return the list.
                    return list
                    // If player has typed one word in.
                    // This > "/command hello " does not count as one
                    // argument because of the space after the hello.
                } else if (args.size == 2) {
                    Glob.Constants.POWERS.values.forEach {
                        list.add(it.name.lowercase())
                    }
                    // Sort them alphabetically.
                    list.sort()
                    // return the list.
                    return list
                }
            }
        }
        // return null at the end.
        // return null at the end.
        return null
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return true
        if (args.isEmpty()) return true
        val mobType: EntityType
        if (Glob.Constants.ALLOWED_MOB_TYPES.map { it.name.lowercase() }.contains(args[0].lowercase())) {
            mobType = Glob.Constants.ALLOWED_MOB_TYPES.first { it.name.lowercase() == args[0].lowercase() }
        } else return false

        val args = args.drop(1).toTypedArray()

        if (args[0] == "*") {
            val entity = sender.world.spawnEntity(sender.location, mobType) as LivingEntity
            val mob = InfernalMob(entity)
            mob.abilities = Glob.Constants.POWERS.values.toMutableList()
            for (arg in args) {
                if (arg.startsWith("!")) {
                    for ((i, power) in mob.abilities.withIndex()) {
                        if (power.name == arg.drop(1)) {
                            mob.abilities.removeAt(i)
                        }
                    }
                }
                mob.abilities.forEach {
                    it.onSpawn(
                        entity
                    )
                }
                return true
            }
        }


        val entity2 = sender.world.spawnEntity(sender.location, mobType) as LivingEntity
        val mob2 = InfernalMob(entity2)


        mob2.abilities = args.map { Glob.Constants.POWERS[it.lowercase()]!! }.toMutableList()

        mob2.abilities.forEach {
            it.onSpawn(
                entity2
            )
        }

        return true
    }
}