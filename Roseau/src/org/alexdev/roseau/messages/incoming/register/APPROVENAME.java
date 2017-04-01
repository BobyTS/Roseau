package org.alexdev.roseau.messages.incoming.register;

import org.alexdev.roseau.Roseau;
import org.alexdev.roseau.game.player.Player;
import org.alexdev.roseau.messages.incoming.MessageEvent;
import org.alexdev.roseau.messages.outgoing.register.NAME_APPROVED;
import org.alexdev.roseau.messages.outgoing.register.NAME_UNACCEPTABLE;
import org.alexdev.roseau.messages.outgoing.user.SYSTEMBROADCAST;
import org.alexdev.roseau.server.messages.ClientMessage;

public class APPROVENAME implements MessageEvent {

	@Override
	public void handle(Player player, ClientMessage reader) {

		if (reader.getArgumentAmount() > 0) {

			String name = reader.getArgument(0);

			if (name.length() > 0) {
				if (Roseau.getGame().getPlayerManager().approveName(name)) {
					player.send(new NAME_APPROVED());
				} else {
					player.send(new NAME_UNACCEPTABLE());
				}
			}

		}
	}

}
