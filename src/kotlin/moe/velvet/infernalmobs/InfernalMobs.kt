package moe.velvet.infernalmobs

import moe.velvet.infernalmobs.commands.SpawnZombie
import moe.velvet.infernalmobs.utils.Logger
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class InfernalMobs : JavaPlugin() {
    private val logger = Logger()

    override fun onEnable() {
        logger.info("Loading InfernalMobs...")
        // Register commands
        getCommand("spawnzombie")?.setExecutor(SpawnZombie)
        // Register metrics
        Metrics(this, 15140)
        logger.info("InfernalMobs successfully loaded!")
    }

    override fun onDisable() {
        logger.info("Disabled")
    }
}