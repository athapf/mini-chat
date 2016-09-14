package de.thaso.minichat.db.it;

import de.thaso.minichat.db.it.base.DbTestBase;
import de.thaso.minichat.db.it.utils.SecondCauseMatcher;
import de.thaso.minichat.db.store.ChatMessageEntity;
import org.h2.jdbc.JdbcSQLException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

/**
 * SimpleIT
 *
 * @author thaler
 * @since 13.09.16
 */
public class SimpleDatabaseIT extends DbTestBase {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testStoreNote() throws SQLException {
        // given
        final ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setTimestamp(new Date());
        chatMessageEntity.setNick("test nick");
        chatMessageEntity.setRoom("test room");
        chatMessageEntity.setMessage("hallo, world!");
        // when
        entityManager.persist(chatMessageEntity);
        // then
        assertThat(chatMessageEntity.getId(), is(greaterThan(1000000L)));

        entityManager.flush();
        final ResultSet resultSet = getConnection().prepareStatement("select count(*) from T_CHAT_MESSAGE").executeQuery();
        resultSet.next();
        assertThat(resultSet.getInt(1),is(2));
    }

    @Test
    public void testPrimaryKeyViolation() throws SQLException {
        // given
        Query nativeQuery = entityManager.createNativeQuery("INSERT INTO T_CHAT_MESSAGE (ID, TIMESTAMP, NICK, ROOM, MESSAGE) VALUES(74, '2014-02-15', 'foo', 'bar', 'bla ...')");
        nativeQuery.executeUpdate();

        final ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setId(74L);
        chatMessageEntity.setTimestamp(new Date());
        chatMessageEntity.setNick("developer");
        chatMessageEntity.setRoom("home");
        chatMessageEntity.setMessage("this should know all developer ...");

        exception.expect(RollbackException.class);
        exception.expectCause(new SecondCauseMatcher(JdbcSQLException.class, "PRIMARY_KEY"));
        // when
        entityManager.persist(chatMessageEntity);
    }
}