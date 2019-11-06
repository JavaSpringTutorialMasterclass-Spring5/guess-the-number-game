package academy.learnprogramming.console;

import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import academy.learnprogramming.MessageGeneratorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess {

    //constants
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    //fields
    @Autowired
    private Game game;

    @Autowired
    private MessageGenerator messageGenerator;

    @EventListener(ContextRefreshedEvent.class)
    public void start() { //ta metoda zostanie uruchomiona kiedy odpalone zostanie zdarzenie ContextRefreshedEvent

        //event o nazwie ContextRefreshedEvent jest publikowany kiedy kontekst aplikacji jest inicjalizowany lub odświeżany
        //inicjalizowany = kiedy wszystkie beany są ładowane do kontekstu, kiedy uruchomiły się metody Post-processor,
        //kiedy singletony są tworzone, kiedy kontekst apliacji zaczyna być gotowy do użycia

        log.info("start() -> Container ready for use.");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");

                String playAgainString = scanner.nextLine().trim();

                if (!playAgainString.equalsIgnoreCase("y")) {
                    break;
                }

                game.reset();
            }
        }
    }
}