package com.example.tdd_project.program;



import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest {
    private Profile profile;
    private BooleanQuestion questionIsThereRelocation;
    private Answer answerIsThereIsRelocation;
    private Answer answerThereIsNotRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerDoesNotReimburseTuition;
    private Answer answerReimbursesTuition;
    private Answer answerThereRelocation;
    private Criteria criteria;

    @Before
    public void createCriteria() {
        criteria = new Criteria();
    }

    @Before
    public void createProfile() {
        profile = new Profile();
    }

    @Before
    public void createQuestionAndAnswer() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerIsThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);
    }

    @Test
    public void matchesNothingWhenProfileEmpty() {
        Criterion criterion = new Criterion(answerIsThereIsRelocation, Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answerIsThereIsRelocation);
        Criterion criterion = new Criterion(answerIsThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

    @Test
    public void doesNotMatchWhenNoMatchingAnswer() {
        profile.add(answerThereIsNotRelocation);
        Criterion criterion = new Criterion(answerThereIsNotRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matchesWhenContainsMultipleAnswers() {
        profile.add(answerThereIsNotRelocation);
        profile.add(answerDoesNotReimburseTuition);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

    @Test
    public void doesNotMatchWhenNoneOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        assertFalse(profile.matches(criteria));
    }

    @Test
    public void matchesWhenAnyOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        assertTrue(profile.matches(criteria));
    }

    @Test
    public void doesNotMatchWhenAnyMustMeetCriteriaNotMet() {
        profile.add(answerThereRelocation);
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.MustMatch));

        assertFalse(profile.matches(criteria));
    }
}
