package com.arrKhange1.testplugin.listeners

import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

data class PlayerShootingWithBow(val player: Player, val bow: ItemStack, val arrow: Arrow)
