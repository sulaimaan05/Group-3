package Service;

import Repository.JokeRepo;
import Model.Joke;
import java.util.List;

public class JokeService {

    private JokeRepo jokeRepository;

    public JokeService(JokeRepo jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    public void submitJoke(int creatorId, String jokeText) {
        jokeRepository.save(creatorId, jokeText, "PENDING");
    }

    public List<Joke> getApprovedJokes() {
        return jokeRepository.findByStatus("APPROVED");
    }

    public List<Joke> getJokesByUser(int userId) {
        return jokeRepository.findByUser(userId);
    }
}