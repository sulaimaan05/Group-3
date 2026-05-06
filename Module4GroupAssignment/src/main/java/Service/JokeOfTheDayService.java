package Service;

import Repository.JokeRepo;
import Repository.VoteRepo;
import Model.Joke;

public class JokeOfTheDayService {

    private JokeRepo jokeRepository;
    private VoteRepo voteRepository;

    public JokeOfTheDayService(JokeRepo jokeRepository, VoteRepo voteRepository) {
        this.jokeRepository = jokeRepository;
        this.voteRepository = voteRepository;
    }

    public Joke getJokeOfTheDay() {
        return jokeRepository.findTopRatedLast24Hours();
    }

    public void calculateJokeOfTheDay() {
        // logic to calculate and store in DB
    }
}