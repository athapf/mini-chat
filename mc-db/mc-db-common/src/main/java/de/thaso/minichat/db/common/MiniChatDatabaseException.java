package de.thaso.minichat.db.common;

/**
 * MiniChatApplicationException
 *
 * @author thaler
 * @since 12.09.16
 */
public class MiniChatDatabaseException extends RuntimeException {

    private MiniChatDbError miniChatDbError;

    public MiniChatDatabaseException(final MiniChatDbError miniChatDbError, final String message) {
        super(message);
        this.miniChatDbError = miniChatDbError;
    }

    public MiniChatDbError getMiniChatDbError() {
        return miniChatDbError;
    }
}
