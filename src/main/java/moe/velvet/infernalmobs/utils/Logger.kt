package moe.velvet.infernalmobs.utils

import org.bukkit.Bukkit

class Logger {
    private var prefix = "[InfernalMobs]"
    private val logger = Bukkit.getLogger()

    init {
        logger.info("$prefix Logger initialized") // Logger initialized
    }

    fun info(message: String) {
        logger.info("$prefix $message")
    }

    fun warn(message: String) {
        logger.warning("$prefix $message")
    }

    fun error(message: String) {
        logger.severe("$prefix $message")
    }

    fun debug(message: String) {
        logger.fine("$prefix $message")
    }

    fun trace(message: String) {
        logger.finest("$prefix $message")
    }

    fun severe(message: String) {
        logger.severe("$prefix $message")
    }

}

