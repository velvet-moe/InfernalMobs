package moe.velvet.infernalmobs

import moe.velvet.infernalmobs.commands.SpawnInfernal
import moe.velvet.infernalmobs.utils.EventListener
import moe.velvet.infernalmobs.utils.Logger
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin


@Suppress("unused")
class InfernalMobs : JavaPlugin() {
    private val logger = Logger()

    override fun onEnable() {
        logger.info("Loading InfernalMobs...")
        // Register listeners
        Bukkit.getPluginManager().registerEvents(EventListener(), this)
        // Register metrics
        Metrics(this, 15140)
        // Register commands
        this.getCommand("spawninfernal")?.setExecutor(SpawnInfernal())
        logger.info("InfernalMobs successfully loaded!")
        // Broadcast to all players
        Bukkit.getOnlinePlayers().forEach {
            it.sendMessage("InfernalMobs §7v${this.description.version} §ahas been enabled!")
        }
    }

    override fun onDisable() {
        logger.info("Disabled")
    }
}

fun getInstance(): Plugin {
    val plugin: Plugin? = Bukkit.getServer().pluginManager.getPlugin("InfernalMobs")
    if (plugin == null || plugin !is InfernalMobs) {
        throw RuntimeException("'InfernalMobs' not found. 'InfernalMobs' plugin disabled?")
    }
    return plugin
}