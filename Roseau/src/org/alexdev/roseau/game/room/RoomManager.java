package org.alexdev.roseau.game.room;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.alexdev.roseau.Roseau;
import org.alexdev.roseau.game.room.settings.RoomType;

import com.google.common.collect.Lists;

public class RoomManager {

	public List<Room> loadedRooms;

	public RoomManager() {
		this.loadedRooms = Lists.newArrayList();
	}
	
	public void load() {
		Roseau.getDataAccess().getRoom().getPublicRooms(true);
	}

	public void add(Room room) {

		boolean add = true;
		
		for (Room loadedRoom : this.loadedRooms) {

			if (room.getData().getId() == loadedRoom.getData().getId()) {
				add = false;
			}
		}

		if (add) {
			this.loadedRooms.add(room);
		}
	}
	
	public List<Room> getPublicRooms() {
		try {
			List<Room> rooms =  this.loadedRooms.stream().filter(room -> room.getData().getRoomType() == RoomType.PUBLIC && room.getData().isHidden() == false).collect(Collectors.toList());
			
			Collections.sort(rooms,new Comparator<Room>() {
			    @Override
			    public int compare(Room a, Room b) {
			        return b.getUsers().size() - a.getUsers().size();
			    }
			});
			
			return rooms;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Room> getPlayerRooms(int userId) {
		try {
			return this.loadedRooms.stream().filter(room -> room.getData().getOwnerId() == userId && room.getData().isHidden() == false).collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	public Room getRoomById(int roomId) {

		try {
			return Roseau.getGame().getRoomManager().getLoadedRooms().stream().filter(r -> r.getData().getId() == roomId).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Room getRoomByPort(int port) {

		try {
			return Roseau.getGame().getRoomManager().getLoadedRooms().stream().filter(r -> r.getData().getServerPort() == port).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Room getRoomByName(String name) {

		try {
			return Roseau.getGame().getRoomManager().getLoadedRooms().stream().filter(r -> r.getData().getName().equals(name)).findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Room> getLoadedRooms() {
		return loadedRooms;
	}

}