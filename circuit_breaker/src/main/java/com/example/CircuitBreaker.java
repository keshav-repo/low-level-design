package com.example;

/**
 * Circuit breaker implementation to prevent cascading failures in distributed
 * systems.
 * The circuit breaker has three states: CLOSED, OPEN, and HALF_OPEN.
 * CLOSED: The circuit is closed and requests are allowed to pass through.
 * OPEN: The circuit is open and requests are blocked.
 * HALF_OPEN: The circuit is half-open and allows a limited number of requests
 * to test if the issue is resolved.
 */
public class CircuitBreaker {

    public enum State {
        CLOSED, OPEN, HALF_OPEN
    }

    private final int failureThreshold; // Max failures before "open"
    private final int successThreshold; // Successes needed to "close"
    private volatile State state; // Current state
    private int failureCount; // Failure counter
    private int successCount; // Success counter
    private volatile long lastFailureTime; // Last failure timestamp
    private final long retryTimeout; // Retry timeout duration

    public CircuitBreaker(int failureThreshold, int successThreshold, long retryTimeout) {
        this.failureThreshold = failureThreshold;
        this.successThreshold = successThreshold;
        this.retryTimeout = retryTimeout;
        this.state = State.CLOSED;
    }

    // Synchronized to handle multi-threaded access
    public boolean allowRequest() {
        if (state == State.OPEN) {
            synchronized (this) {
                // Check if the retry timeout has elapsed
                if (System.currentTimeMillis() - lastFailureTime > retryTimeout) {
                    state = State.HALF_OPEN; // Transition to HALF_OPEN state
                    failureCount = 0; // Reset failure count
                    successCount = 0; // Reset success count
                    return true; // Allow the request
                }
                return false; // Block the request
            }
        }
        return true; // Allow requests in CLOSED and HALF_OPEN states
    }

    public void recordSuccess() {
        synchronized (this) {
            successCount++;
            if (successCount >= successThreshold) {
                state = State.CLOSED; // Transition to CLOSED state
                failureCount = 0; // Reset failure count
            }
        }
    }

    public void recordFailure() {
        synchronized (this) {
            failureCount++;
            if (state == State.HALF_OPEN || failureCount >= failureThreshold) {
                state = State.OPEN; // Transition to OPEN state
                lastFailureTime = System.currentTimeMillis();
            }
        }
    }

    public State getState() {
        State currentState;
        synchronized (this) {
            currentState = state;
        }
        return currentState;
    }
}
