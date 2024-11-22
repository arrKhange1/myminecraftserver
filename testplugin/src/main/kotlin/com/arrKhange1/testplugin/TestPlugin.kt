package com.arrKhange1.testplugin

import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    override fun onEnable() {
        println("Plugin enabled")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
