package academy.learnprogramming.service;

import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    // == fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    // == constructor ==
    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // == init ==
    @PostConstruct
    public void init() {
        log.info("Utworzono obiekt GameServiceImpl");
        log.info("Oto mainMessage: {}", getMainMessage());
        log.info("Oto numer do odgadnięcia: {}", game.getNumber());
    }

    // == public methods ==
    @Override
    public boolean isGameOver() {

        //czy gra jest zakończona (obojętnie jak)
        return game.isGameLost() || game.isGameWon();
    }

    @Override
    public String getMainMessage() {

        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {

        return messageGenerator.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {

        //wysłanie wartości podanej od usera do instancji klasy Game
        game.setGuess(guess);

        //sprawdzenie wyniku tego zgadnięcia od usera
        game.check();
    }

    @Override
    public void reset() {

        //zresetowanir gry
        game.reset();
    }
}
