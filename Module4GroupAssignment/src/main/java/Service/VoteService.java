package Service;

import Model.Vote;
import Repository.VoteRepo;
import Repository.JokeRepo;

import java.util.List;
import java.util.Optional;


public class VoteService {

    private final VoteRepo voteRepo;
    private final JokeRepo jokeRepo;

    public VoteService(VoteRepo voteRepo, JokeRepo jokeRepo) {
        this.voteRepo = voteRepo;
        this.jokeRepo = jokeRepo;
    }

    public Optional<Vote> castVote(int userId, int jokeId, int voteValue) {
        // Validate vote value
        if (voteValue != 1 && voteValue != -1) return Optional.empty();

        // Ensure the joke exists and is approved
        var jokeOpt = jokeRepo.getJokeById(jokeId);
        if (jokeOpt.isEmpty()) return Optional.empty();
        if (!"approved".equals(jokeOpt.get().getStatus())) return Optional.empty();

        // Prevent duplicate votes
        if (voteRepo.hasUserVoted(userId, jokeId)) return Optional.empty();

        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setJokeId(jokeId);
        vote.setVoteValue(voteValue);

        return voteRepo.createVote(vote);
    }

    public boolean retractVote(int userId, int jokeId) {
        if (!voteRepo.hasUserVoted(userId, jokeId)) return false;
        return voteRepo.deleteVoteByUserAndJoke(userId, jokeId);
    }

    public int getVoteCountForJoke(int jokeId) {
        return voteRepo.getVoteCountByJokeId(jokeId);
    }

    public List<Vote> getVotesForJoke(int jokeId) {
        return voteRepo.getVotesByJokeId(jokeId);
    }

    public List<Vote> getVotesByUser(int userId) {
        return voteRepo.getVotesByUserId(userId);
    }

    public boolean hasUserVoted(int userId, int jokeId) {
        return voteRepo.hasUserVoted(userId, jokeId);
    }

    public boolean deleteVoteById(int voteId) {
        return voteRepo.deleteVoteById(voteId);
    }
}
