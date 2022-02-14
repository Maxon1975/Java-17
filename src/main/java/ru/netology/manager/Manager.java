package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.*;
import java.util.Comparator;
import java.util.function.Predicate;

public class Manager {
    private Repository repository;

    public Manager(Repository repository) {
        this.repository = repository;
    }

    public void issueAdd(Issue item) {
        repository.add(item);
    }
    // Добавить один вопрос

    public void issueAddAll(List<Issue> items) {
        repository.addAll(items);
    }
    // Добавить несколько вопросов

    public List<Issue> getOpenIssue() {
        return repository.findOpen();
    }
    // Показать открытые вопросы

    public List<Issue> getClosedIssue() {
        return repository.findClosed();
    }
    // Показать закрытые вопросы

    public void closedIssue(int id) {
        repository.closeById(id);
    }

    public void openIssue(int id) {
        repository.openById(id);
    }

    public List<Issue> sortByNew() {
        List<Issue> result = new ArrayList<>();
        result.addAll(repository.getAll());
        Collections.sort(result);
        return result;
    }

    public List<Issue> sortByOld() {
        Comparator ByOld = Comparator.reverseOrder();
        List<Issue> issues = new ArrayList<>();
        issues.addAll(repository.getAll());
        issues.sort(ByOld);
        return issues;
    }

    public List<Issue> filterByAuthor(String author) {
        List<Issue> result = new ArrayList<>();
        Predicate<String> predicate = Predicate.isEqual(author);
        for (Issue item : repository.getAll())
            if (predicate.test(item.getAuthor())) {
                result.add(item);
            }
        ;
        return result;
    }

    public List<Issue> filterByLabel(String label) {
        List<Issue> result = new ArrayList<>();
        Predicate<Set<String>> predicate = x -> x.contains(label);
        for (Issue item : repository.getAll())
            if (predicate.test(item.getLabel())) {
                result.add(item);
            }
        return result;
    }

    public List<Issue> filterByAssignee(String assignee) {
        List<Issue> result = new ArrayList<>();
        Predicate<Set<String>> predicate = x -> x.contains(assignee);
        for (Issue item : repository.getAll())
            if (predicate.test(item.getAssignee())) {
                result.add(item);
            }
        return result;
    }
}
