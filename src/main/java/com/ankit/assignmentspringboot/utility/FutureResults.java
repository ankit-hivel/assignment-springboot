package com.ankit.assignmentspringboot.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureResults {
    private static final Logger log = LoggerFactory.getLogger(FutureResults.class);

    public static boolean getFutureResult(Future<Boolean> future, String name) {
        try {
            return future.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log.error("{} check timed out", name);
            future.cancel(true);
        } catch (Exception e) {
            log.error("{} check failed: {}", name, e.getMessage());
        }
        return false;
    }
}
