package no.oslomet.oblig3_s315278.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    @ManyToOne
    @JoinColumn
    private Film film;
    @ManyToOne
    @JoinColumn
    private User user;

    public Ticket(String date, Film film, User user) {
        this.date = date;
        this.film = film;
        this.user = user;
    }

}
