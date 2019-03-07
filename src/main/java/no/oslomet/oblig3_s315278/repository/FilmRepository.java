package no.oslomet.oblig3_s315278.repository;

import no.oslomet.oblig3_s315278.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
