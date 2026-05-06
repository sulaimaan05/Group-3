package Controller;

import Service.ModerationService;
import Model.Joke;
import java.util.List;

public class ModerationController {

    private ModerationService moderationService;

    public ModerationController(ModerationService moderationService) {
        this.moderationService = moderationService;
    }

    public List<Joke> getPendingJokes() {
        return moderationService.getPendingJokes();
    }

    public void approveJoke(int jokeId) {
        moderationService.approveJoke(jokeId);
    }

    public void rejectJoke(int jokeId) {
        moderationService.rejectJoke(jokeId);
    }
}