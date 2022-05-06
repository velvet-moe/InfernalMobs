package moe.velvet.infernalmobs.commands

import moe.velvet.infernalmobs.InfernalMob
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

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

        val zombie = InfernalMob(Zombie::class.java, arrayOf("thing"), arrayOf(PotionEffect(PotionEffectType.SPEED, 100, 1)))
        zombie.spawn(sender.location)

        sender.sendMessage("Spawned a zombie.")
        sender.sendMessage(zombie.getString())

        return true
    }
}