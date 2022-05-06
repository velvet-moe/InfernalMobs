package moe.velvet.infernalmobs

import moe.velvet.infernalmobs.commands.SpawnZombie
import moe.velvet.infernalmobs.utils.Logger
import org.bukkit.plugin.java.JavaPlugin

class InfernalMobs : JavaPlugin() {
    private val logger = Logger()

    override fun onEnable() {
        logger.info("InfernalMobs hath enabled!")
        getCommand("spawn_zombie")?.setExecutor(SpawnZombie)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}