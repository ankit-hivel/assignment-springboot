package com.ankit.assignmentspringboot.responseDto;

public class MetricsResponseDto {
    private String cacheMiss;
    private String cacheHit;

    public String getCacheMiss() {
        return cacheMiss;
    }

    public void setCacheMiss(String cacheMiss) {
        this.cacheMiss = cacheMiss;
    }

    public String getCacheHit() {
        return cacheHit;
    }

    public void setCacheHit(String cacheHit) {
        this.cacheHit = cacheHit;
    }
}
