package no.oslomet.oblig3_s315278.controller;

import no.oslomet.oblig3_s315278.model.Film;
import no.oslomet.oblig3_s315278.model.Ticket;
import no.oslomet.oblig3_s315278.model.User;
import no.oslomet.oblig3_s315278.repository.FilmRepository;
import no.oslomet.oblig3_s315278.repository.TicketRepository;
import no.oslomet.oblig3_s315278.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping({"/", "/login"})
    public String index() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByEmail(auth.getName()).get();
        model.addAttribute("user", user);
        model.addAttribute("films", filmRepository.findAll());
        model.addAttribute("tickets", ticketRepository.findAll());
        return "index";
    }

    @PostMapping("/processRegistration")
    public String register(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/buyTicket/{id}")
    public String buyTicket(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ticketRepository.save(new Ticket(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), filmRepository.findById(id).get(), userRepository.findUserByEmail(auth.getName()).get()));
        return "redirect:/tickets";
    }

    @GetMapping("/deleteTicket/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets")
    public String showTickets(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByEmail(auth.getName()).get();
        model.addAttribute("user", user);
        model.addAttribute("tickets", ticketRepository.findTicketsByUser(user));
        return "tickets";
    }

    @GetMapping("/addFilm")
    public String addFilm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByEmail(auth.getName()).get();
        model.addAttribute("user", user);
        model.addAttribute("film", new Film());
        return "addFilm";
    }

    @PostMapping("/saveFilm")
    public String saveFilm(@ModelAttribute("film") Film film) {
        filmRepository.save(film);
        return "redirect:/home";
    }

    @GetMapping("/deleteFilm/{id}")
    public String deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
        return "redirect:/home";
    }

}
