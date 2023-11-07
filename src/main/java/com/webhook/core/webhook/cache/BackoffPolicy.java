package com.webhook.core.webhook.cache;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Builder(access = AccessLevel.PRIVATE)
public class BackoffPolicy implements Serializable {
    private final int tryCount;
    private final int backoffTimeSec;

    public static BackoffPolicy of(int tryCount) {
        return BackoffPolicy.builder()
                .tryCount(tryCount)
                .backoffTimeSec(calculate(tryCount))
                .build();
    }

    public int tryCount() {
        return tryCount;
    }

    public int backoffTimeMinutes() {
        return backoffTimeSec;
    }

    private static int calculate(int tryCount) {
        return switch (tryCount) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 5;
            case 4 -> 10;
            case 5 -> 15;
            default -> -1;
        };
    }
}
