package org.clustermc.hub.features.lottery

import java.util.UUID

import org.clustermc.hub.player.HubPlayer

/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of Hub.
 * 
 * Hub can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

object ClusterLottery extends Lottery {
    override val minutes: Int = 60 * 24

    override def giveRewardTo(winner: UUID): Unit = {
        HubPlayer.get(winner).bank.getClusterWallet.deposit(lottery.getTotal)
    }

    override def sendMessages(): Unit = {

    }
}
