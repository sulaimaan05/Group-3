package Service;

import Repository.VoteRepo;

public class VoteService {

    private VoteRepo voteRepository;

    public VoteService(VoteRepo voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void vote(int userId, int jokeId) {
        voteRepository.save(userId, jokeId);
    }
}