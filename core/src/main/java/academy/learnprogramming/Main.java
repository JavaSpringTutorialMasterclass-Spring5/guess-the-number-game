package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private final static Logger log = LoggerFactory.getLogger(Main.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess The Number Game");

        //create context container
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        //odpytujemy kontekst o odpowiednią instancję beana po nazwie (id z pliku beans) i typie
        NumberGenerator numberGenerator = context.getBean("numberGenerator", NumberGenerator.class);

        //call method
        int number = numberGenerator.next();

        //log generated number
        log.info("generated number = {}", number);

        //close context
        context.close();
    }
}
