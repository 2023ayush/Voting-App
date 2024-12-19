package com.voting.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Embeddable
//@Entity
public class OptionVote {

    private String voteOption;
    private Long voteCount = 0L;

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public String getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(String option) {
        this.voteOption = option;
    }
}
