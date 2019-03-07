package no.oslomet.oblig3_s315278.repository;

import no.oslomet.oblig3_s315278.model.Ticket;
import no.oslomet.oblig3_s315278.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketsByUser(User user);
}
