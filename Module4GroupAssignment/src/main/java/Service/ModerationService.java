package Service;

import Repository.JokeRepo;
import Model.Joke;
import java.util.List;

public class ModerationService {

    private JokeRepo jokeRepository;

    public ModerationService(JokeRepo jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    public List<Joke> getPendingJokes() {
        return jokeRepository.findByStatus("PENDING");
    }

    public void approveJoke(int jokeId) {
        jokeRepository.updateStatus(jokeId, "APPROVED");
    }

    public void rejectJoke(int jokeId) {
        jokeRepository.updateStatus(jokeId, "REJECTED");
    }
}