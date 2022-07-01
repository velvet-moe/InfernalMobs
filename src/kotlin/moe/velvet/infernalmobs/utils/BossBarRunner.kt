package moe.velvet.infernalmobs.utils

import moe.velvet.infernalmobs.getInfernalDataClass
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class BossBarRunner : BukkitRunnable() {
    override fun run() {
        Glob.InfernalList.forEach {
            val nearbyEntites = it.getNearbyEntities(Glob.Constants.BOSSBAR_RANGE, Glob.Constants.BOSSBAR_RANGE, Glob.Constants.BOSSBAR_RANGE)
            nearbyEntites.forEach {e ->
                if (!getInfernalDataClass(it)!!.bossbar.players.contains(e)) {
                    getInfernalDataClass(it)?.bossbar?.removeAll()
                }
            }
//            if ((it as LivingEntity).health >= 0) {
//                getInfernalDataClass(it)?.bossbar?.removeAll()
//            }
            it as LivingEntity
            nearbyEntites.forEach { e ->
                //getInfernalDataClass(e)?.bossbar?.removeAll()
                if (e !is Player) return@forEach
                if (it.isDead) getInfernalDataClass(it)?.bossbar?.removeAll()
                getInfernalDataClass(it)?.bossbar?.removeAll()
                getInfernalDataClass(it)?.bossbar?.addPlayer(e)
                getInfernalDataClass(it)?.bossbar?.progress = it.health /  getInfernalDataClass(it)!!.maxHealth
            }
        }
    }
}