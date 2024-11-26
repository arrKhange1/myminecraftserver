package com.arrKhange1.testplugin.listeners

import net.kyori.adventure.text.TextComponent
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent

class BowEventListener : Listener {

    private lateinit var playerShootingWithBow: PlayerShootingWithBow

    @EventHandler
    fun onHitArrow(event: ProjectileHitEvent) {
        val projectileOnFinish = event.entity
        if (projectileOnFinish.entityId == playerShootingWithBow?.arrow?.entityId) {
            val bowLore = playerShootingWithBow.bow.lore() ?: return
            if (bowLore.map { cmp -> (cmp as TextComponent).content() }.contains("Teleport Bow"))
                playerShootingWithBow.player.teleport(projectileOnFinish.location)
        }
    }

    @EventHandler
    fun onShootArrow(event: EntityShootBowEvent) {
        val shootingEntity = event.entity
        val bow = event.bow
        val projectile = event.projectile
        if (shootingEntity is Player && bow != null && projectile is Arrow) {
            playerShootingWithBow = PlayerShootingWithBow(shootingEntity, bow, projectile)
        }
    }


}