package moe.velvet.infernalmobs

//import com.zaxxer.hikari.HikariConfig
//import com.zaxxer.hikari.HikariDataSource
import moe.velvet.infernalmobs.commands.*
import moe.velvet.infernalmobs.utils.*
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
        loadPluginConfig()
        // Register listeners
        Bukkit.getPluginManager().registerEvents(EventListener(), this)
        // Register metrics
        Metrics(this, 15140)
        // Register commands
        this.getCommand("spawninfernal")?.setExecutor(SpawnInfernal())
        this.getCommand("spawnloot")?.setExecutor(SpawnLoot())
        this.getCommand("idebug")?.setExecutor(Debug())
        this.getCommand("flushbossbars")?.setExecutor(FlushBossbars())
        this.getCommand("iquery")?.setExecutor(Query())
        logger.info("InfernalMobs successfully loaded!")
        // Register runners
        LootEffectRunner().runTaskTimer(this, 0, 20 * 5)
        ParticleRunner().runTaskTimerAsynchronously(this, 0, 5)
        BossBarRunner().runTaskTimer(this, 0, 5)
        // Broadcast to all players
        Bukkit.getOnlinePlayers().forEach {
            it.sendMessage("InfernalMobs §7v${this.description.version} §ahas been enabled!")
        }
    }

    override fun onDisable() {
        // Kill all infernals
        Glob.InfernalList.forEach {
            if (isInfernalMob(it)) {
                val data = getInfernalDataClass(it)!!
                data.bossbar.removeAll()
                data.bossbarPlayerList.clear()
            }
            it.remove()
        }
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

    private fun loadPluginConfig() {
        config.addDefault("infernalSpawnChance", 10)
        config.addDefault("globalLootDropChance", 99)
        config.addDefault("infernalNamePrefix", "§cInfernal ")
        config.addDefault("infernalNameSuffix", "")
        config.addDefault("allowedInfernalMobTypes", listOf(
            "ZOMBIE",
            "SPIDER",
            "CAVE_SPIDER",
            "CREEPER",
            "DROWNED",
            "SKELETON",
            "WITHER_SKELETON",
            "BLAZE",
            "ENDERMAN",
            "ENDERMITE",
            "EVOKER",
            "GHAST",
            "GUARDIAN",
            "HOGLIN",
            "HUSK",
            "MAGMA_CUBE",
            "PHANTOM",
            "PIGLIN",
            "PIGLIN_BRUTE",
            "PILLAGER",
            "PUFFERFISH",
            "RAVAGER",
            "SHULKER",
            "SILVERFISH",
            "SLIME",
            "STRAY",
            "VEX",
            "VINDICATOR",
            "WARDEN",
            "WITCH",
            "WITHER_SKELETON",
            "ZOGLIN",
            "ZOMBIE_VILLAGER",
            "ZOMBIFIED_PIGLIN",
            "ENDER_DRAGON",
            "WITHER",
        ))
        config.addDefault("allowedInfernalTypes", listOf(
            "Armoured",
            "Berserk",
            "Blinding",
            "Bulwark",
            "Extra_Life",
            "Life_Steal",
            "Poisonous",
            "Quicksand",
            "Rust",
            "Sapper",
            "Sprint",
            "Vengeance",
            "Storm",
            "Ender",
            "Potions",
            "Webber",
            "Weakness",
            "Wraith",
            "Withering",
        ))
        config.addDefault("allowedCharmSlots", listOf(
            0, 1, 2, 3, 4, 8, 7, 6, 5, 17, 40
        ))
        config.addDefault("mainHandCharmsEnabled", true)
        config.addDefault("bossBarColor", "RED")
        config.addDefault("infernalHealthScaleFactor", 2.0)
        config.addDefault("infernalMaxLevel", 10)
        config.addDefault("infernalHealthLevelScaleFactor", 1.0)
        config.options().copyDefaults(true)
        saveConfig()
    }
}



fun getInstance(): InfernalMobs {
    val plugin: Plugin? = Bukkit.getServer().pluginManager.getPlugin("InfernalMobs")
    if (plugin == null || plugin !is InfernalMobs) {
        throw RuntimeException("'InfernalMobs' not found. 'InfernalMobs' plugin disabled?")
    }
    return plugin
}