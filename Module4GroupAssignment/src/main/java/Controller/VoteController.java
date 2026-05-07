package Controller;

import Controller.ControllerResponse;
import Model.Vote;
import Service.VoteService;

import java.util.List;


public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    public ControllerResponse<Vote> upvote(int userId, int jokeId) {
        return castVote(userId, jokeId, 1);
    }

    public ControllerResponse<Vote> downvote(int userId, int jokeId) {
        return castVote(userId, jokeId, -1);
    }

    private ControllerResponse<Vote> castVote(int userId, int jokeId, int value) {
        if (voteService.hasUserVoted(userId, jokeId))
            return ControllerResponse.failure("You have already voted on this joke. Retract your vote first.");

        return voteService.castVote(userId, jokeId, value)
                .map(v -> ControllerResponse.success(
                        (value == 1 ? "Upvote" : "Downvote") + " cast successfully.", v))
                .orElse(ControllerResponse.failure("Failed to cast vote. The joke may not exist or is not yet approved."));
    }

    public ControllerResponse<Void> retractVote(int userId, int jokeId) {
        boolean retracted = voteService.retractVote(userId, jokeId);
        return retracted
                ? ControllerResponse.success("Your vote has been retracted.")
                : ControllerResponse.failure("No vote found to retract for this joke.");
    }

    public ControllerResponse<Integer> getVoteCount(int jokeId) {
        int count = voteService.getVoteCountForJoke(jokeId);
        return ControllerResponse.success("Vote count for joke #" + jokeId + ": " + count, count);
    }

    public ControllerResponse<List<Vote>> getVotesForJoke(int jokeId, String requesterRole) {
        if (!"moderator".equals(requesterRole))
            return ControllerResponse.failure("Access denied. Moderator role required to view individual votes.");

        List<Vote> votes = voteService.getVotesForJoke(jokeId);
        return ControllerResponse.success("Retrieved " + votes.size() + " vote(s) for joke #" + jokeId + ".", votes);
    }

    public ControllerResponse<List<Vote>> getVotesByUser(int requesterId, String requesterRole, int targetUserId) {
        boolean isModerator = "moderator".equals(requesterRole);
        boolean isSelf      = requesterId == targetUserId;

        if (!isModerator && !isSelf)
            return ControllerResponse.failure("Access denied. You may only view your own votes.");

        List<Vote> votes = voteService.getVotesByUser(targetUserId);
        return ControllerResponse.success("Retrieved " + votes.size() + " vote(s).", votes);
    }

    public ControllerResponse<Void> deleteVote(String requesterRole, int voteId) {
        if (!"moderator".equals(requesterRole))
            return ControllerResponse.failure("Access denied. Moderator role required.");

        boolean deleted = voteService.deleteVoteById(voteId);
        return deleted
                ? ControllerResponse.success("Vote #" + voteId + " deleted.")
                : ControllerResponse.failure("Vote not found.");
    }
}
