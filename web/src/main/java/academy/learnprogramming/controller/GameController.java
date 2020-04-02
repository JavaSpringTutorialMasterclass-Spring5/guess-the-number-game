package academy.learnprogramming.controller;

import academy.learnprogramming.service.GameService;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.GameMappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GameController {

    // == fields ==
    private final GameService service;

    // == contrctructor ==
    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    // == request methods ==
    @GetMapping(GameMappings.PLAY)
    public String play(Model model) {

        model.addAttribute(AttributeNames.MAIN_MESSAGE, service.getMainMessage());
        model.addAttribute(AttributeNames.RESULT_MESSAGE, service.getResultMessage());

        log.info("model = {}", model);

        if (service.isGameOver()) {
            //jeśli gra skończona - obojętnie czy sukses czy porażka - to przechodzimy do strony z podsumowaniem gry
            return ViewNames.GAME_OVER;
        }

        return ViewNames.PLAY; //jeśli gra nie jest skończona, to wracamy do tej strony, w której właśnie jesteśmy
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess) { //parametr po stronie thymeleafa też musi nazywać się guess

        log.info("guess = {}", guess);

        //sprawdzamy zgadnięcie usera
        service.checkGuess(guess);

        //wracamy na stronę "play" gdzie albo wyświetlimy formularz, albo stronę na zakończenie gry
        return GameMappings.REDIRECT_PLAY;
    }

    @GetMapping(GameMappings.RESTART)
    public String restart() { //parametr po stronie thymeleafa też musi nazywać się guess

        log.info("restarting the game");

        service.reset();

        //wracamy na stronę "play" gdzie - w tym przypadku - wyświetli się formularz z nową grą
        return GameMappings.REDIRECT_PLAY;
    }
}
