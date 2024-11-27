package com.arrKhange1.testplugin.listeners

import com.arrKhange1.testplugin.TestPlugin
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class BowCommand(private val testPlugin: TestPlugin, name: String) : Command(name) {
    override fun execute(commandSender: CommandSender, commandAlias: String, args: Array<String>): Boolean {
        if (commandSender is Player) {
            val bow = ItemStack.of(Material.BOW)
            bow.lore(listOf(Component.text("Teleportation")))
            val bowItemMeta = bow.itemMeta
            bowItemMeta.persistentDataContainer.set(testPlugin.teleportBowKey, PersistentDataType.STRING, testPlugin.teleportBowKey.value())
            bow.setItemMeta(bowItemMeta)
            commandSender.inventory.addItem(bow)
            return true
        }
        return false
    }

}