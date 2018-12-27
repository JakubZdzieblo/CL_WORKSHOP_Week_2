package pl.coderslab.entity;

import java.util.Date;

public class Solution {

    private int id;
    private Date created;
    private Date updated;
    private String description;
    private Exercise exercise;
    private User user;

    public Solution() {
    }

    @Override
    public String toString() {
        return "ID: " + id + " Ex.: " + exercise + ", by " + user.getUsername() + ", '" + description + "'";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
