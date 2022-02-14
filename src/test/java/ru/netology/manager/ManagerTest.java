package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CRUDManagerTest {
    @Nested
    public class Empty {
        private Repository repository = new Repository();
        private Manager manager = new Manager(repository);

        @Test
        void shouldFindOpenIssue() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.getOpenIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldFindClosedIssue() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.getClosedIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldFilterByAuthor() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.filterByAuthor("Vlad");
            assertEquals(expected, actual);

        }

        @Test
        void shouldFilterByLabel() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.filterByLabel("Label");
            assertEquals(expected, actual);

        }

        @Test
        void shouldFilterByAssignee() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.filterByAssignee("Assignee");
            assertEquals(expected, actual);

        }

        @Test
        void shouldSortedByNew() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.sortByNew();
            assertEquals(expected, actual);

        }

        @Test
        void shouldSortedByOld() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.sortByOld();
            assertEquals(expected, actual);

        }

        @Test
        void shouldClosedIssue() {
            List<Issue> expected = new ArrayList<>();
            manager.closedIssue(1);
            List<Issue> actual = manager.getClosedIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldOpenedIssue() {
            List<Issue> expected = new ArrayList<>();
            manager.openIssue(1);
            List<Issue> actual = manager.getOpenIssue();
            assertEquals(expected, actual);

        }
    }

    @Nested
    public class SingleItem {
        private Repository repository = new Repository();
        private Manager manager = new Manager(repository);
        Issue issue1 = new Issue(1, true, "Oleg", 5,
                new HashSet<>(Arrays.asList("Label 1", "Label 2", "Label 3")),
                new HashSet<>(Arrays.asList("Assignee 1", "Assignee 2", "Assignee 3")));
        Issue issue2 = new Issue(2, true, "Oleg", 5,
                new HashSet<>(Arrays.asList("Label 1", "Label 2", "Label 3")),
                new HashSet<>(Arrays.asList("Assignee 1", "Assignee 2", "Assignee 3")));

        @BeforeEach
        void setup() {
            manager.issueAdd(issue1);
        }


        @Test
        void shouldAddIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue2));
            manager.issueAdd(issue2);
            List<Issue> actual = repository.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpenIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.getOpenIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldFindClosedIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            manager.closedIssue(1);
            List<Issue> actual = manager.getClosedIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldFilterByAuthor() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.filterByAuthor("Oleg");
            assertEquals(expected, actual);

        }


        @Test
        void shouldFilterByLabel() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.filterByLabel("Label 3");
            assertEquals(expected, actual);

        }

        @Test
        void shouldFilterByAssignee() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.filterByAssignee("Assignee 2");
            assertEquals(expected, actual);

        }

        @Test
        void shouldSortedByNew() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.sortByNew();
            assertEquals(expected, actual);

        }

        @Test
        void shouldSortedByOld() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.sortByOld();
            assertEquals(expected, actual);

        }

        @Test
        void shouldClosedIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            manager.closedIssue(1);
            List<Issue> actual = manager.getClosedIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldOpenedIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.getOpenIssue();
            assertEquals(expected, actual);

        }
    }

    @Nested
    public class MultipleItems {
        private Repository repository = new Repository();
        private Manager manager = new Manager(repository);
        Issue issue1 = new Issue(1, true, "Oleg", 1,
                new HashSet<>(Arrays.asList("Label 1", "Label 2", "Label 3")),
                new HashSet<>(Arrays.asList("Assignee 1", "Assignee 2", "Assignee 3")));
        Issue issue2 = new Issue(2, false, "Vlad", 4,
                new HashSet<>(Arrays.asList("Label 225", "Label 223", "Label 224")),
                new HashSet<>(Arrays.asList("Assignee 225", "Assignee 223", "Assignee 224")));
        Issue issue3 = new Issue(3, true, "Oleg", 3,
                new HashSet<>(Arrays.asList("Label 111", "Label 2", "Label 376")),
                new HashSet<>(Arrays.asList("Assignee 111", "Assignee 2", "Assignee 376")));
        Issue issue4 = new Issue(4, false, "Elena", 7,
                new HashSet<>(Arrays.asList("Label 778", "Label 722", "Label 739")),
                new HashSet<>(Arrays.asList("Assignee 778", "Assignee 722", "Assignee 739")));
        Issue issue5 = new Issue(5, true, "Denis", 12,
                new HashSet<>(Arrays.asList("Label 2", "Label 23", "Label 3")),
                new HashSet<>(Arrays.asList("Assignee 2", "Assignee 23", "Assignee 3")));
        Issue issue6 = new Issue(6, true, "Oleg", 2,
                new HashSet<>(Arrays.asList("Label 2", "Label 23", "Label 3")),
                new HashSet<>(Arrays.asList("Assignee 2", "Assignee 23", "Assignee 3")));

        @BeforeEach
        void setup() {
            manager.issueAdd(issue1);
            manager.issueAdd(issue2);
            manager.issueAdd(issue3);
            manager.issueAdd(issue4);
            manager.issueAdd(issue5);
        }

        @Test
        void shouldAddIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue3, issue4, issue5, issue6));
            manager.issueAdd(issue6);
            List<Issue> actual = repository.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpenIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3, issue5));
            List<Issue> actual = manager.getOpenIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldFindClosedIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4));
            manager.closedIssue(1);
            List<Issue> actual = manager.getClosedIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldFilterByAuthor() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3, issue6));
            manager.issueAdd(issue6);
            List<Issue> actual = manager.filterByAuthor("Oleg");
            assertEquals(expected, actual);

        }


        @Test
        void shouldFilterByLabel() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3, issue5));
            List<Issue> actual = manager.filterByLabel("Label 2");
            assertEquals(expected, actual);

        }

        @Test
        void shouldFilterByAssignee() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3, issue5));
            List<Issue> actual = manager.filterByAssignee("Assignee 2");
            assertEquals(expected, actual);

        }

        @Test
        void shouldSortedByNew() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3, issue2, issue4, issue5));
            List<Issue> actual = manager.sortByNew();
            assertEquals(expected, actual);

        }

        @Test
        void shouldSortedByOld() {
            List<Issue> expected = new ArrayList<>(List.of(issue5, issue4, issue2, issue3, issue1));
            List<Issue> actual = manager.sortByOld();
            assertEquals(expected, actual);

        }

        @Test
        void shouldClosedIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4));
            manager.closedIssue(1);
            List<Issue> actual = manager.getClosedIssue();
            assertEquals(expected, actual);

        }

        @Test
        void shouldOpenedIssue() {
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3, issue5));
            List<Issue> actual = manager.getOpenIssue();
            assertEquals(expected, actual);

        }

    }

}
