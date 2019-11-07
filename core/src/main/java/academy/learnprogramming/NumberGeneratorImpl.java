package academy.learnprogramming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {

    private final Random random = new Random();

    @Autowired
    @MaxNumber
    private int maxNumber;  //wstzyknięty zostanie bean o nazwie maxNumber, który zwróci odpowiedniego integera

    @Autowired
    @MinNumber
    private int minNumber;  //wstzyknięty zostanie bean o nazwie minNumber, który zwróci odpowiedniego integera

    @Override
    public int next() {
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }
}
