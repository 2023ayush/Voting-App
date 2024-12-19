package com.voting.service;

import com.voting.model.OptionVote;
import com.voting.model.Poll;
import com.voting.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {

   @Autowired
   PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);

    }

    public void vote(Long pollId, int optionIndex) {
        //Get Poll from Db
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new IllegalStateException("Poll not found"));
        //Get ALL Options
        List<OptionVote> options = poll.getOptions();
        //Check if selected option index is valid
        if (optionIndex < 0 || optionIndex >= options.size()) {
            throw new IllegalArgumentException("Invalid option index");
        }
        //Get selected option
        OptionVote selectedOption = options.get(optionIndex);
        //Increment vote count
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        //Update Poll options
        poll.setOptions(options);
        //Save Poll to Db
        pollRepository.save(poll);
    }
}
