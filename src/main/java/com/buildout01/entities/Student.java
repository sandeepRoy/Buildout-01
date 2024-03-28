package com.buildout01.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Document
public class Student {
    @Id
    private String id;
    private String username;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer score;

    // @Max(3) [try adding it if more than 3 badges are assigned]
    private List<String> badgeList;

    public Student() {
        badgeList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getBadgeList() {
        return badgeList;
    }

    public void setBadgeList(List<String> badgeList) {
        this.badgeList = badgeList;
    }
}
