package org.theclustermc.hub.gui.serverselect.items

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.theclustermc.hub.Hub
import org.theclustermc.hub.gui.InvItem
import org.theclustermc.lib.bungee.ServerTeleport
import org.theclustermc.lib.utils.ItemFactory

/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of Hub.
 * 
 * Hub can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

object BetaTestItem extends InvItem{
  val item = new ItemFactory(Material.ANVIL).setDisplayName("Beta-Testing Server").getItemStack

  override def act(player: Player, clickType: ClickType): Unit = {
    ServerTeleport.tpToServer(Hub.instance, player, "BetaTest")
  }
}
