package de.thaso.minichat.db.it;

import de.thaso.minichat.db.it.base.DbTestBase;
import de.thaso.minichat.db.it.utils.SecondCauseMatcher;
import de.thaso.minichat.db.store.ChatMessageDAO;
import de.thaso.minichat.db.store.ChatMessageEntity;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * SimpleIT
 *
 * @author thaler
 * @since 13.09.16
 */
public class SimpleDatabaseIT extends DbTestBase {

    @InjectMocks
    private ChatMessageDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

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
        assertThat(resultSet.getInt(1),is(15));
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
        exception.expectCause(new SecondCauseMatcher(SQLException.class, "PRIMARY_KEY", "Unique-Constraint"));
        // when
        entityManager.persist(chatMessageEntity);
    }

    @Test
    public void testFindLastChatMessages() throws SQLException {
        // when
        final List<ChatMessageEntity> result = underTest.findLastChatMessages();
        // then
        assertThat(result.size(), is(10));
        Long previousTimestamp = null;
        for (ChatMessageEntity chatMessageEntity : result) {
            if(previousTimestamp != null) {
                assertThat(chatMessageEntity.getTimestamp().getTime(),is(lessThan(previousTimestamp)));
            }
            previousTimestamp = chatMessageEntity.getTimestamp().getTime();
        }
    }

    @Test
    public void testFindChatMessagesSince() throws SQLException, ParseException {
        // given
        final Date timestamp = DateUtils.parseDate("2015-09-09 14:49:37.837", "yyyy-MM-dd hh:mm:ss.SSS");
        // when
        final List<ChatMessageEntity> result = underTest.findChatMessagesSince(timestamp);
        // then
        assertThat(result.size(), is(5));
        Long previousTimestamp = null;
        for (ChatMessageEntity chatMessageEntity : result) {
            if(previousTimestamp != null) {
                assertThat(chatMessageEntity.getTimestamp().getTime(),is(lessThan(previousTimestamp)));
            }
            previousTimestamp = chatMessageEntity.getTimestamp().getTime();
        }
    }
}
