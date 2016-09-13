package de.thaso.minichat.db.store.utils;

import de.thaso.minichat.db.common.MiniChatDatabaseException;
import de.thaso.minichat.db.common.MiniChatDbError;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * MiniChatDatabaseExceptionCodeMatcher
 *
 * @author thaler
 * @since 13.09.16
 */
public class MiniChatDatabaseExceptionCodeMatcher extends TypeSafeMatcher<MiniChatDatabaseException> {
    private MiniChatDbError errorCode;

    public MiniChatDatabaseExceptionCodeMatcher(MiniChatDbError errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    protected boolean matchesSafely(final MiniChatDatabaseException exception) {
        return exception.getMiniChatDbError() == errorCode;
    }

    public void describeTo(final Description description) {
        description.appendText("expects error code ")
                .appendValue(errorCode);
    }

    @Override
    protected void describeMismatchSafely(final MiniChatDatabaseException exception,
                                          final Description mismatchDescription) {
        mismatchDescription.appendText("was ")
                .appendValue(exception.getMiniChatDbError());
    }
}
