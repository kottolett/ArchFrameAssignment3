package no.oslomet.oblig3_s315278;

import no.oslomet.oblig3_s315278.model.Film;
import no.oslomet.oblig3_s315278.model.User;
import no.oslomet.oblig3_s315278.repository.FilmRepository;
import no.oslomet.oblig3_s315278.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Oblig3S315278Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Oblig3S315278Application.class, args);
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User("admin", "admin", "admin@oslomet.no", passwordEncoder.encode("admin"), "ADMIN"));
        filmRepository.save(new Film("Captain Marvel", "Colosseum"));
        filmRepository.save(new Film("Amundsen", "Ringen Kino"));
        filmRepository.save(new Film("Wreck-It Ralph Breaks the Internet", "Klingenberg"));
        filmRepository.save(new Film("Cold Pursuit", "Ringen Kino"));
        filmRepository.save(new Film("A Star is Born", "Odeon"));
        filmRepository.save(new Film("Alita: Battle Angel", "Colosseum"));
        filmRepository.save(new Film("Lego Movie 2", "Saga"));
    }
}
