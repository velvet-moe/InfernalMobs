package moe.velvet.infernalmobs.utils

import moe.velvet.infernalmobs.getInfernalDataClass
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class BossBarRunner : BukkitRunnable() {
    override fun run() {
        Glob.InfernalList.forEach {
            val nearbyEntites = it.getNearbyEntities(10.0 , 10.0, 10.0)
            nearbyEntites.forEach { e ->
                //getInfernalDataClass(e)?.bossbar?.removeAll()
                if (e !is Player) return@forEach
                getInfernalDataClass(it)?.bossbar?.addPlayer(e)
                getInfernalDataClass(it)?.bossbar?.progress = (it as LivingEntity).health /  getInfernalDataClass(it)!!.maxHealth
                if (it.health <= 0) getInfernalDataClass(it)?.bossbar?.removeAll()
            }
            if (!nearbyEntites.toSet().any { e -> e in getInfernalDataClass(it)!!.bossbar.players.toSet() }) {
                getInfernalDataClass(it)?.bossbar?.removeAll()
            }
        }
    }
}