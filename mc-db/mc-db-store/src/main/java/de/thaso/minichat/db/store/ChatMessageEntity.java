package de.thaso.minichat.db.store;

import de.thaso.minichat.db.store.base.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * ChatMessageEntity
 *
 * @author thaler
 * @since 13.09.16
 */
@Entity
@Table(name = "T_CHAT_MESSAGE")
public class ChatMessageEntity extends EntityBase {

    private static final long serialVersionUID = -4319045348350461073L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messageSequence")
    @SequenceGenerator(name = "messageSequence", sequenceName = "SEQ_ID_CHAT_MESSAGE", allocationSize = 10)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
    @NotNull
    private Date timestamp;

    @Column(name = "NICK")
    @Size(min=1, max= 10)
    @NotNull
    private String nick;

    @Column(name = "ROOM")
    @Size(min=1, max= 25)
    @NotNull
    private String room;

    @Column(name = "MESSAGE")
    @Size(min=1, max= 4000)
    @NotNull
    private String message;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
