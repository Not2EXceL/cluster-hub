package org.clustermc.hub.disguise.menu

import io.mazenmc.menuapi.menu.Menu
import org.bukkit.entity.Player
import org.clustermc.hub.DisguiseEnum

/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of Hub.
 * 
 * Hub can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

class DisguiseMenu(player: Player) extends Menu("Turn Into Your Favorite Creature", 54) {

  setItem(9, ) //todo so not doing this tonight
  setItem(0, new DisguiseItem(DisguiseEnum.PIG))

    //Horses

    //Zombies


}
