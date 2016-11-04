package de.thaso.mc.fe.chat.model;

import javax.enterprise.inject.Any;

/**
 * ChatRoomModel
 *
 * @author thaler
 * @since 27.10.16
 */
@Any
public class ChatRoomModel {
    private String room;
    private String nick;
    private String message;

    public String getRoom() {
        return room;
    }

    public void setRoom(final String room) {
        this.room = room;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(final String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
