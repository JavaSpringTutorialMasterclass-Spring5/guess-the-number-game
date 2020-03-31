package academy.learnprogramming;

import academy.learnprogramming.config.GameConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        log.info("Guess The Number Game");

        SpringApplication.run(Main.class, args);

        // to co poniżej ZBĘDNE przy SpringBoocie!!!

        //create context container
        //ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        //utworzenie kontekstu skutkuje wyzwoleniem eventu ContextRefreshedEvent w klasie ConsoleNumberGuess
        //co skutkuje uruchomieniem się gry (uruchomieniem metody start z tej klasy)
        //po zakończeniu gry (zakończeniu metody start) przechodzimy do kodu poniżej, czyli zamknięcia kontekstu

        //close context
        //context.close();

        // to co powyżej ZBĘDNE przy SpringBoocie!!!
    }
}
