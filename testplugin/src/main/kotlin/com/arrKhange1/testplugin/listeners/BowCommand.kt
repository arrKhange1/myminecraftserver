package com.arrKhange1.testplugin.listeners

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class BowCommand(name: String) : Command(name) {
    override fun execute(commandSender: CommandSender, commandAlias: String, args: Array<String>): Boolean {
        if (commandSender is Player) {
            val bow = ItemStack.of(Material.BOW)
            bow.lore(listOf(Component.text("Teleport Bow")))
            commandSender.inventory.addItem(bow)
            args.forEach { arg -> println(arg) }
            return true
        }
        return false
    }

}