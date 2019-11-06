package academy.learnprogramming.console;

import academy.learnprogramming.MessageGeneratorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsoleNumberGuess {

    //constants
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    @EventListener(ContextRefreshedEvent.class)
    public void start() { //ta metoda zostanie uruchomiona kiedy odpalone zostanie zdarzenie ContextRefreshedEvent

        //event o nazwie ContextRefreshedEvent jest publikowany kiedy kontekst aplikacji jest inicjalizowany lub odświeżany
        //inicjalizowany = kiedy wszystkie beany są ładowane do kontekstu, kiedy uruchomiły się metody Post-processor,
        //kiedy singletony są tworzone, kiedy kontekst apliacji zaczyna być gotowy do użycia

        log.info("start() -> Container ready for use.");
    }
}
