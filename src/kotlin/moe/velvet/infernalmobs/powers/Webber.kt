package moe.velvet.infernalmobs.powers

import moe.velvet.infernalmobs.getInstance
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class Webber : Power {
    override val name: String
        get() = "webber"
    //private var webs: HashMap<Location, Pair<Material, BlockData>> = HashMap()

    override fun onDamageEntity(e: Entity, damager: Entity, amount: Double): Boolean {
        if (e !is LivingEntity) return true
        if (e !is Player) return true

        val loc = e.location
        if (loc.y % 1.0 != 0.0) {
            loc.y++
        }

        if (e.world.getBlockAt(loc).type == Material.COBWEB) return true

        while (loc.block.type != Material.AIR) {
            if (loc.y > 320) return true
            loc.y++
        }

        //webs[loc] = Pair(e.world.getBlockAt(loc).type, e.world.getBlockAt(loc).blockData.clone())

        Bukkit.getScheduler().runTaskLater(getInstance(), Runnable {
            //getInstance().logger.info("webber")
            loc.block.type = Material.AIR
        }, 20)

        e.world.getBlockAt(loc).type = Material.COBWEB
        return true
    }

//    override fun onDeath(e: LivingEntity): Boolean {
//        webs.forEach {
//            it.key.block.type = it.value.first
//            it.key.block.blockData = it.value.second
//            webs.remove(it.key)
//        }
//        //e.damage(e.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value + 1)
//        return true
//    }
}