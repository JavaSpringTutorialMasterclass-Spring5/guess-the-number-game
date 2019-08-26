package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {

    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    @Autowired
    private Game game;

    private int guessCount = 10;

    @PostConstruct
    public void init() {
        log.info("Obiekt game to: " + game);
    }

    @Override
    public String getMainMessage() {
        return "uruchomiono metodę getMainMessage";
    }

    @Override
    public String getResultMessage() {
        return "uruchomiono metodę getResultMessage";
    }
}
