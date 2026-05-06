package Controller;

import Service.VoteService;

public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    public void vote(int userId, int jokeId) {
        voteService.vote(userId, jokeId);
    }
}