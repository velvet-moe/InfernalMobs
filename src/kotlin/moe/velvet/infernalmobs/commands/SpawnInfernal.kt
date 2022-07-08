package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.InfernalMob
import moe.velvet.infernalmobs.utils.Glob
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Ageable
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
                    (1..Glob.Constants.INFERNAL_MAX_LEVEL).forEach {
                        list.add(it.toString())
                    }
                    return list
                } else if (args.size >= 3) {
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
        if (sender !is Player) {
            sender.sendMessage("§cYou must be a player to use this command.")
            return true
        }

        if (!sender.hasPermission("infernalmobs.spawninfernal")) {
            sender.sendMessage("§cYou do not have permission to use this command.")
            return true
        }

        if (args.isEmpty()) {
            sender.sendMessage("§cUsage: /spawninfernal <mob> <level> <power>")
            return true
        }

        val mobType: EntityType
        if (Glob.Constants.ALLOWED_MOB_TYPES.map { it.name.lowercase() }.contains(args[0].lowercase())) {
            mobType = Glob.Constants.ALLOWED_MOB_TYPES.first { it.name.lowercase() == args[0].lowercase() }
        } else {
            sender.sendMessage("§cPlease enter a valid mob type.\n" +
                    "§cValid mob types: ${Glob.Constants.ALLOWED_MOB_TYPES.map { it.name.lowercase() + ", " }}")
            return true
        }

        var args = args.drop(1).toTypedArray()
        val level = args[0].toInt()
        if (level > Glob.Constants.INFERNAL_MAX_LEVEL) {
            sender.sendMessage("§cPlease enter a valid level.\n" +
                    "§cValid levels: 1-${Glob.Constants.INFERNAL_MAX_LEVEL}")
            return true
        }
        args = args.drop(1).toTypedArray()

        if (args[0] == "*") {
            val entity = sender.world.spawnEntity(sender.location, mobType) as LivingEntity
            if (entity is Ageable) {
                entity.setAdult()
            }
            val mob = InfernalMob(entity, level)
            mob.abilities = Glob.Constants.POWERS.values.toMutableList()

            Glob.InfernalList.add(mob.entity)
        }
        if (args[0] == "r") {
            val entity = sender.world.spawnEntity(sender.location, mobType) as LivingEntity
            if (entity is Ageable) {
                entity.setAdult()
            }
            val mob = InfernalMob(entity, level)

            (1..level).forEach {
                var cPower = Glob.Constants.POWERS.values.random()
                while (mob.abilities.size < it) {
                    if (cPower !in mob.abilities) mob.abilities.add(cPower)
                    cPower = Glob.Constants.POWERS.values.random()
                }
            }
            mob.abilities.forEach {
                it.onSpawn(
                    entity
                )
            }
            Glob.InfernalList.add(mob.entity)
            return true
        }

        val entity = sender.world.spawnEntity(sender.location, mobType) as LivingEntity
        if (entity is Ageable) {
            entity.setAdult()
        }
        val mob = InfernalMob(entity, level)


        mob.abilities = args.map { Glob.Constants.POWERS[it.lowercase()]!! }.toMutableList()

        mob.abilities.forEach {
            it.onSpawn(
                entity
            )
        }
        Glob.InfernalList.add(mob.entity)
        return true
    }
}