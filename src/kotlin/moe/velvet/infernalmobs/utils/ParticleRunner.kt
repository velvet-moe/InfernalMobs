package moe.velvet.infernalmobs.utils

import org.bukkit.Particle
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector

class ParticleRunner : BukkitRunnable() {
    override fun run() {
        Glob.InfernalList.forEach {
            if (it.isDead) {
                Glob.InfernalList.remove(it)
                return@forEach
            }
            it.world.spawnParticle(Particle.SOUL_FIRE_FLAME, it.location.add(Vector(0.0, 1.0, 0.0)), 10, 0.5, 0.5, 0.5, 0.2)
        }
    }
}