package moe.velvet.infernalmobs

//import com.zaxxer.hikari.HikariConfig
//import com.zaxxer.hikari.HikariDataSource
import moe.velvet.infernalmobs.commands.SpawnInfernal
import moe.velvet.infernalmobs.commands.SpawnLoot
import moe.velvet.infernalmobs.utils.EventListener
import moe.velvet.infernalmobs.utils.Logger
import moe.velvet.infernalmobs.utils.LootEffectRunner
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File


@Suppress("unused")
class InfernalMobs : JavaPlugin() {
    private val logger = Logger()
    var lootConfig: FileConfiguration? = null

    override fun onEnable() {
        logger.info("Loading InfernalMobs...")
        // Check the config directory
        if (!dataFolder.exists()) { dataFolder.mkdir() }
        // Load configs
        loadLootConfig()
        // Register listeners
        Bukkit.getPluginManager().registerEvents(EventListener(), this)
        // Register metrics
        Metrics(this, 15140)
        // Register commands
        this.getCommand("spawninfernal")?.setExecutor(SpawnInfernal())
        this.getCommand("spawnloot")?.setExecutor(SpawnLoot())
        logger.info("InfernalMobs successfully loaded!")
        // Register runners
        LootEffectRunner().runTaskTimer(this, 0, 20 * 5)
        // Broadcast to all players
        Bukkit.getOnlinePlayers().forEach {
            it.sendMessage("InfernalMobs §7v${this.description.version} §ahas been enabled!")
        }
        //logger.info(LootFactory.lootTable.toString())
    }

    override fun onDisable() {
        logger.info("Disabled")
    }
    private fun loadLootConfig() {
        val file = File(dataFolder, "loot.yml")
        if (!file.exists()) {
            saveResource("loot.yml", false)
        }
        lootConfig = YamlConfiguration()
        lootConfig?.load(file)
    }
}



fun getInstance(): InfernalMobs {
    val plugin: Plugin? = Bukkit.getServer().pluginManager.getPlugin("InfernalMobs")
    if (plugin == null || plugin !is InfernalMobs) {
        throw RuntimeException("'InfernalMobs' not found. 'InfernalMobs' plugin disabled?")
    }
    return plugin
}