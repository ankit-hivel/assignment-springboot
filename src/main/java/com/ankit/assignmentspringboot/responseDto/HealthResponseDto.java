package com.ankit.assignmentspringboot.responseDto;

public class HealthResponseDto {
    private Boolean db;
    private Boolean redis;

    public Boolean getDb() {
        return db;
    }

    public void setDb(Boolean db) {
        this.db = db;
    }

    public Boolean getRedis() {
        return redis;
    }

    public void setRedis(Boolean redis) {
        this.redis = redis;
    }
}
