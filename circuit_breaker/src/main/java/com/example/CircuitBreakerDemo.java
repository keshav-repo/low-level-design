package com.example;

public class CircuitBreakerDemo {

    public static void main(String[] args) {
        CircuitBreaker circuitBreaker = new CircuitBreaker(3, 2, 5000); // 3 failures, 2 successes, 5 seconds timeout

        for (int i = 1; i <= 10; i++) {
            if (circuitBreaker.allowRequest()) {
                try {
                    // Simulate a call to an external service
                    if (simulateServiceCall(i)) {
                        circuitBreaker.recordSuccess();
                        System.out.println("Call " + i + " succeeded.");
                    } else {
                        throw new RuntimeException("Simulated failure");
                    }
                } catch (Exception e) {
                    circuitBreaker.recordFailure();
                    System.out.println("Call " + i + " failed.");
                }
            } else {
                System.out.println("Call " + i + " blocked by circuit breaker.");
            }
        }
    }

    private static boolean simulateServiceCall(int callNumber) {
        // Simulate a failure for specific calls with a more realistic approach
        // For example, fail every 4th call and every call that is a multiple of 5
        return callNumber % 4 != 0 && callNumber % 5 != 0;
    }
}
