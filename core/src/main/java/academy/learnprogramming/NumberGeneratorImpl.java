package academy.learnprogramming;

import academy.learnprogramming.annotations.MaxNumber;
import academy.learnprogramming.annotations.MinNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGeneratorImpl implements NumberGenerator {

    private final Random random = new Random();

    private final int maxNumber;  //wstzyknięty zostanie bean o nazwie maxNumber, który zwróci odpowiedniego integera
    private final int minNumber;  //wstzyknięty zostanie bean o nazwie minNumber, który zwróci odpowiedniego integera

    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

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
