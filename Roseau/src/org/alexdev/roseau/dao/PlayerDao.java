package org.alexdev.roseau.dao;

import java.util.List;
import java.util.Map;

import org.alexdev.roseau.game.player.Player;
import org.alexdev.roseau.game.player.PlayerDetails;

public interface PlayerDao {

	public void createPlayer(String username, String password, String email, String mission, String figure, int credits, String sex, String birthday);
	public PlayerDetails getDetails(int userID);
	public boolean login(Player player, String userna, String password);
	public int getID(String username);
	public boolean isNameTaken(String name);
	void updatePlayer(PlayerDetails details);
	void updateLastLogin(PlayerDetails details);
	public Map<Integer, List<String>> getPermissions();
	public PlayerDetails getDetails(String username);
	

}
