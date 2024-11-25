package com.arrKhange1.testplugin

import com.arrKhange1.testplugin.listeners.MyEventListener
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

class TestPlugin : JavaPlugin() {

    private fun addNewRecipe() {
        val key = NamespacedKey(this, "DiamondSword")
        val diamondSword = ItemStack.of(Material.DIAMOND_SWORD)

        val diamondWorkRecipe = ShapedRecipe(key, diamondSword)
        diamondWorkRecipe.shape(" A ", " A ", " B ")
        diamondWorkRecipe.setIngredient('A', Material.DIAMOND_BLOCK)
        diamondWorkRecipe.setIngredient('B', Material.STICK)

        server.addRecipe(diamondWorkRecipe)
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(MyEventListener(), this)
        addNewRecipe()
    }

    override fun onDisable() {

    }
}
