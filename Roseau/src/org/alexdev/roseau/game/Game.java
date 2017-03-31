package org.alexdev.roseau.game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.alexdev.roseau.dao.Dao;
import org.alexdev.roseau.game.player.PlayerManager;
import org.alexdev.roseau.game.room.RoomManager;
import org.alexdev.roseau.log.Log;

public class Game {

	PlayerManager playerManager;
	RoomManager roomManager;
	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(8);
	
	public Game(Dao dao) throws Exception {
		this.playerManager = new PlayerManager();
		this.roomManager = new RoomManager();
	}
	
	public void load() {
		
		try {
			this.roomManager.load();
		} catch (Exception e) {
			Log.exception(e);
		}
	}
	
	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public RoomManager getRoomManager() {
		return roomManager;
	}

	public ScheduledExecutorService getScheduler() {
		return scheduler;
	}
}
