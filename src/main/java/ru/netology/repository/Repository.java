package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Repository {
    private List<Issue> items = new ArrayList<>();

    public List<Issue> getAll() {
        return items;
    }

    public boolean add(Issue item) {
        return items.add(item);
    }

    public boolean addAll(Collection<? extends Issue> items) {
        return this.items.addAll(items);
    }

    public List<Issue> findOpen() {
        List<Issue> openIssues = new ArrayList<>();
        for (Issue item : items)
            if (item.isOpen()) {
                openIssues.add(item);
            }
        return openIssues;
    }

    public List<Issue> findClosed() {
        List<Issue> closedIssues = new ArrayList<>();
        for (Issue item : items)
            if (!item.isOpen()) {
                closedIssues.add(item);
            }
        return closedIssues;
    }

    public void closeById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && item.isOpen()) {
                item.setOpen(false);
            }
        }
    }

    public void openById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && !item.isOpen()) {
                item.setOpen(true);
            }
        }
    }

    public boolean removeAll(Collection<? extends Issue> items) {
        return this.items.removeAll(items);
    }
}
