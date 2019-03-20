package org.toktakprogramming.foodlover;

public class Review {
    String Name;
    String Reviews;

    public Review(String name, String reviews) {
        Name = name;
        Reviews = reviews;
    }

    public String getName() {
        return Name;
    }

    public String getReviews() {
        return Reviews;
    }
}
