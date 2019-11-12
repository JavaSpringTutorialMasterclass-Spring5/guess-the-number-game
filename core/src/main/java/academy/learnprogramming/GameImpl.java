package academy.learnprogramming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {

    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator; //dla tego pola getter nie będzie widoczny spoza klasy

    private final int guessCount; //wstzyknięty zostanie bean o nazwie guessCount, który zwróci odpowiedniego integera

    @Setter
    private int guess; //liczba podana przez gracza

    private int number; //numer do odgadnięcia
    private int smallest; //dolny zakres z którego trzeba zgadywać liczbę
    private int biggest; //górny zakres z którego trzeba zgadywać liczbę
    private int remainingGuesses; //ile jeszcze prób pozostało
    private boolean validNumberRange = true;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator,  int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    @Override
    @PostConstruct
    public void reset() { //resetowanie gry, ustawianie wartości początkowych na wszystkich polach
        smallest = numberGenerator.getMinNumber();
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();

        log.debug("The number to guess is {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("In Game preDestroy()");
    }

//    public GameImpl(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator; //tutaj zostanie wstrzyknięta zależność zdefiniowana w beans.xml w tagu <constructor-arg> (constructor based dependency injection)
//    }

    //setter dla numberGeneratora
//    public void setNumberGenerator(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator; //tutaj zostanie wstrzyknięta zależność zdefiniowana w beans.xml w tagu <constructor-arg> (setter based dependency injection)
//    }

    @Override
    public void check() {

        checkValidNumberRange();

        if (validNumberRange) {
            if (guess > number) {
                biggest = guess - 1;
            }
            if (guess < number) {
                smallest = guess + 1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    private void checkValidNumberRange() {
        validNumberRange = guess >= smallest && guess <= biggest;
    }
}
