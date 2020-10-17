package tdd.cinema;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CinemaTest {
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(instanceOf(Ticket3D.class)));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions.get(0), is(instanceOf(Session.class)));
        assertThat(sessions, is(instanceOf(List.class)));
    }

    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);

        List<Session> actual = cinema.find(session1 -> true);
        assertThat(actual.get(0), is(instanceOf(Session.class)));
    }
}