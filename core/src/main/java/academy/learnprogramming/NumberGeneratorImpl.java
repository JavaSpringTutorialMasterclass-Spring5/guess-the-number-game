package academy.learnprogramming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {

    private final Random random = new Random();

    @Autowired
    private int maxNumber;  //wstzyknięty zostanie bean o nazwie maxNumber, który zwróci odpowiedniego integera

    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
