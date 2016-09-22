package de.thaso.mc.be.chat.service;

import java.util.Date;

/**
 * ChatMessage
 *
 * @author thaler
 * @since 21.09.16
 */
public class ChatMessage {

    private Long id;
    private Date timestamp;
    private String nick;
    private String room;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(final String nick) {
        this.nick = nick;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(final String room) {
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
