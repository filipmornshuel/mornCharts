package ch.morn.historian.charts.mornCharts;

import ch.morn.historian.charts.mornCharts.repository.UserRepository;
import ch.morn.historian.charts.mornCharts.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("Max Goodwin", "maxgoodwin@lagalaxy.com", "asdf", User.Role.USER, LocalDateTime.now())));
            log.info("Preloading " + repository.save(new User("Ana Belle", "anabelle@lagalaxy.com", "asdf", User.Role.USER, LocalDateTime.now())));
        };
    }
}
