package com.example.tdd_project.program;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    private Map<String, Answer> answers = new HashMap<>();

    private Answer getMatchingProfileAnswer(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }
    public boolean matches(Criterion criterion) {
        return criterion.getWeight() == Weight.DontCare ||
                criterion.getAnswer().match(getMatchingProfileAnswer(criterion));
    }

    public boolean matches(Criteria criteria) {
        boolean matches = false;
        for (Criterion criterion: criteria) {
            if (matches(criterion)) {
                return true;
            } else if (criterion.getWeight() == Weight.MustMatch) {
                return false;
            }
        }
        return matches;
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public ProfileMatch match(Criteria criteria) {
        return new ProfileMatch();
    }
}
