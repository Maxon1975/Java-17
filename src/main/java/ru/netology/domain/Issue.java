package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Issue implements Comparable<Issue> {
    private int id;
    private boolean isOpen;
    private String author;
    private int dayOpenAgo;
    private Set<String> label;
    private Set<String> assignee;


    @Override
    public int compareTo(Issue o) {
        return dayOpenAgo - o.dayOpenAgo;
    }
}

