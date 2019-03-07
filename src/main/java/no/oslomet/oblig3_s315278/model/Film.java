package no.oslomet.oblig3_s315278.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String cinema;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public Film (String title, String cinema) {
        this.title = title;
        this.cinema = cinema;
    }

}
