package moe.velvet.infernalmobs.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SpawnZombie : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("You must be a player to use this command.")
            return true
        }

        if (!sender.hasPermission("infernalmobs.spawnzombie")) {
            sender.sendMessage("You do not have permission to use this command.")
            return true
        }

        return true
    }
}