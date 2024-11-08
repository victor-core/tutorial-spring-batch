package com.ccsw.tutorialbatch.model;

public class Game {

    private Long id;
    private String title;
    private int ageRecommended;
    private int stock;

    public Game() {
    }

    public Game(Long id, String title, int ageRecommended, int stock) {
        this.id = id;
        this.title = title;
        this.ageRecommended = ageRecommended;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAgeRecommended() {
        return ageRecommended;
    }

    public void setAgeRecommended(int ageRecommended) {
        this.ageRecommended = ageRecommended;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", title=" + title + ", ageRecommended=" + ageRecommended + ", stock=" + stock + "]";
    }
}
