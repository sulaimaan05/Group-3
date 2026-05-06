package Controller;

import Service.JokeService;
import Model.Joke;
import java.util.List;

public class JokeController {

    private JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    public void submitJoke(int creatorId, String jokeText) {
        jokeService.submitJoke(creatorId, jokeText);
    }

    public List<Joke> getApprovedJokes() {
        return jokeService.getApprovedJokes();
    }

    public List<Joke> getUserJokes(int userId) {
        return jokeService.getJokesByUser(userId);
    }
}