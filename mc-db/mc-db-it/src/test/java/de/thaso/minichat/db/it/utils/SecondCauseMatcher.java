package de.thaso.minichat.db.it.utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * CauseMatcher
 *
 * @author thaler
 * @since 14.09.16
 */
public class SecondCauseMatcher extends TypeSafeMatcher<Throwable> {

    private final Class<? extends Throwable> type;
    private final String expectedMessage;

    public SecondCauseMatcher(Class<? extends Throwable> type, String expectedMessage) {
        this.type = type;
        this.expectedMessage = expectedMessage;
    }

    @Override
    protected boolean matchesSafely(Throwable item) {
        Throwable throwable = item.getCause();
        return throwable.getClass().isAssignableFrom(type)
                && throwable.getMessage().contains(expectedMessage);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("expects type ")
                .appendValue(type)
                .appendText(" and a message ")
                .appendValue(expectedMessage);
    }
}
