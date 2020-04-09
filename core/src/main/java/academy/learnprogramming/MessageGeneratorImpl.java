package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constans ==
    public static final String MAIN_MESSAGE = "game.main.message"; //klucz napisu jakiego będziemy poszukiwać w plikach messages.properties i message_es.properties
    public static final String WON_MESSAGE = "game.won.message";
    public static final String LOST_MESSAGE = "game.lost.message";
    public static final String INVALID_NUMBER_RANGE_MESSAGE = "game.invalid.number.range.message";
    public static final String FIRST_GUESS_MESSAGE = "game.first.guess.message";
    public static final String LOWER_MESSAGE = "game.lower.message";
    public static final String HIGHER_MESSAGE = "game.higher.message";
    public static final String REMANING_GUESESS_MESSAGE = "game.remaning.guesses.message";

    // == fields ==
    private final Game game;
    private final MessageSource messageSource;

    // == constructor ==
    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == init ==
    @PostConstruct
    public void init() {
        log.info("Obiekt game to: {}", game);
    }


    // == public methods ==

    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
    }

    @Override
    public String getResultMessage() {

        if (game.isGameWon()) {
            return getMessage(WON_MESSAGE, game.getNumber());
        } else if (game.isGameLost()) {
            return getMessage(LOST_MESSAGE, game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID_NUMBER_RANGE_MESSAGE);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_GUESS_MESSAGE);
        } else {
            //String direction = "Lower";
            String direction = getMessage(LOWER_MESSAGE);

            if (game.getGuess() < game.getNumber()) {
                //direction = "Higher";
                direction = getMessage(HIGHER_MESSAGE);
            }
            //return direction + "! You have " + game.getRemainingGuesses() + " guesses left";
            return getMessage(REMANING_GUESESS_MESSAGE, direction, game.getRemainingGuesses());
        }

    }

    // == private methods ==
    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
