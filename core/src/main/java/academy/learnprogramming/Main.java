package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess The Number Game");

        //create context container
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //odpytujemy kontekst o odpowiednią instancję beana po nazwie (id z pliku beans) i typie
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        //call method
        int number = numberGenerator.next();

        //log generated number
        log.info("generated number = {}", number);

        //get the MessageGeneratorImpl bean from context
        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);
        log.info("getMainMessage = {}", messageGenerator.getMainMessage());
        log.info("getResultMessage = {}", messageGenerator.getResultMessage());

        //close context
        context.close();
    }
}
