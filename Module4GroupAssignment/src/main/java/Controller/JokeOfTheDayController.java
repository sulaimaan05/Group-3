package Controller;

import Service.JokeOfTheDayService;
import Model.Joke;

public class JokeOfTheDayController {

    private JokeOfTheDayService jodService;

    public JokeOfTheDayController(JokeOfTheDayService jodService) {
        this.jodService = jodService;
    }

    public Joke getJokeOfTheDay() {
        return jodService.getJokeOfTheDay();
    }

    public void calculateJokeOfTheDay() {
        jodService.calculateJokeOfTheDay();
    }
}