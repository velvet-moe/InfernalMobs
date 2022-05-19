package moe.velvet.infernalmobs.utils

import moe.velvet.infernalmobs.InfernalMob

import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent

class EventListner(val mob: InfernalMob) : Listener {

    fun onEntitySpawn(e: EntitySpawnEvent) {
        e
    }
}