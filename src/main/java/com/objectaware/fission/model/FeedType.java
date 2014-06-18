package com.objectaware.fission.model;

public enum FeedType {
    SIMPLE(1, "Simples"),
    GROUP(2, "Grupo");

    private int id;
    private String name;

    private FeedType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FeedType getType(Integer id) {
        if (id == null) {
            return null;
        }

        for (FeedType feedType: FeedType.values()) {
            if (id.equals(feedType.getId())) {
                return feedType;
            }
        }

        throw new IllegalArgumentException("No matching type for id " + id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
