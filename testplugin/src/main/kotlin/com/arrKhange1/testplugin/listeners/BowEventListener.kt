package com.arrKhange1.testplugin.listeners

import com.arrKhange1.testplugin.TestPlugin
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.persistence.PersistentDataType

class BowEventListener(private val testPlugin: TestPlugin) : Listener {
    @EventHandler
    fun onHitArrow(event: ProjectileHitEvent) {
        if (event.entity.shooter !is Player || event.entity !is Arrow) return
        val player = event.entity.shooter as Player
        val arrow = event.entity as Arrow
        val arrowStore = arrow.persistentDataContainer
        if (arrowStore.has(testPlugin.teleportBowKey, PersistentDataType.STRING)) {
            player.teleport(arrow.location)
        }
    }

    @EventHandler
    fun onShootArrow(event: EntityShootBowEvent) {
        val shootingEntity = event.entity
        val bow = event.bow
        val projectile = event.projectile
        if (shootingEntity is Player && bow != null && projectile is Arrow) {
            println("onShoot ${bow.itemMeta.persistentDataContainer.has(testPlugin.teleportBowKey, PersistentDataType.STRING)}")
            val bowStore = bow.itemMeta.persistentDataContainer
            if (bowStore.has(testPlugin.teleportBowKey, PersistentDataType.STRING)) {
                projectile.persistentDataContainer.set(testPlugin.teleportBowKey, PersistentDataType.STRING, testPlugin.teleportBowKey.value())
            }
        }
    }


}