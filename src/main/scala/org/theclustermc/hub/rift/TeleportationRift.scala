package org.theclustermc.hub.rift

import java.util.UUID

import org.bukkit.entity.Player
import org.bukkit.{Bukkit, Location, Material}
import org.theclustermc.hub.Hub
import org.theclustermc.lib.bungee.ServerTeleport
import org.theclustermc.lib.utils.cooldown.CooldownExecutor
import org.theclustermc.lib.utils.math.LocationIterator
import scala.collection.mutable

/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of Hub.
 * 
 * Hub can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

object TeleportationRift {
  private final val openRifts: mutable.Map[UUID, (String, List[Location])] = mutable.HashMap()

  private final val riftCloser: CooldownExecutor = new CooldownExecutor {
    override def use(player: Player, ability: String): Unit =
      if(has(player.getUniqueId)){
        TeleportationRift.get(player.getUniqueId)._2.foreach(_.getBlock.getState.update())
        remove(player.getUniqueId)
      }
  }

  def open(player: Player, server: String): Unit = {
    if(!has(player.getUniqueId) && !Hub.instance.cooldowns.isCooling(player.getUniqueId, "rift")){
      Hub.instance.cooldowns.add(player.getUniqueId, "rift", 7, riftCloser)
      val blocks: List[Location] = riftLocations(player.getLocation)
      blocks.foreach(player.sendBlockChange(_, Material.ENDER_PORTAL, 0.toByte))
      openRifts.put(player.getUniqueId, (server, blocks))
    }else{
      player.sendMessage(Hub.instance.msg.get("serverSelector.waitToUse"))
    }
  }

  def use(uuid: UUID): Unit = {
    Bukkit.getScheduler.runTaskLater(Hub.instance, new Runnable {
        override def run(): Unit = {
            ServerTeleport.tpToServer(Hub.instance, Bukkit.getPlayer(uuid), get(uuid)._1)
            remove(uuid)
        }
    }, 1L)
  }

  def get(uuid: UUID) = openRifts.get(uuid).get
  def has(uuid: UUID) = openRifts.contains(uuid)
  def remove(uuid: UUID)= if(has(uuid)) openRifts.remove(uuid)

  def riftLocations(loc: Location): List[Location] = {
    val iter = new LocationIterator(loc.getWorld, loc.toVector, loc.clone.getDirection.setY(0), 0, 4)
    val finalBlocks: List[Location] = List()
    while(iter.hasNext) finalBlocks.::(iter.next().subtract(0,1,0))
    finalBlocks
  }

}
