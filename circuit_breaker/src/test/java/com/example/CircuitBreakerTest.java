package com.example;

import static org.junit.jupiter.api.Assertions.*;

import com.example.CircuitBreaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class CircuitBreakerTest {

    private CircuitBreaker circuitBreaker;

    @BeforeEach
    void setUp() {
        circuitBreaker = new CircuitBreaker(3, 2, 2000); // 3 failures to open, 2 successes to close, 2-second retry
                                                         // timeout
    }

    @Test
    void testInitialStateIsClosed() {
        assertEquals(CircuitBreaker.State.CLOSED, circuitBreaker.getState());
    }

    @Test
    void testRequestAllowedWhenClosed() {
        assertTrue(circuitBreaker.allowRequest());
    }

    @Test
    void testTransitionToOpenStateOnFailures() {
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
        assertFalse(circuitBreaker.allowRequest());
    }

    @Test
    void testRetryTimeoutTransitionToHalfOpen() throws InterruptedException {
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());

        // Wait for retry timeout
        TimeUnit.MILLISECONDS.sleep(2100);

        assertTrue(circuitBreaker.allowRequest());
        assertEquals(CircuitBreaker.State.HALF_OPEN, circuitBreaker.getState());
    }

    @Test
    void testRequestAllowedWhenHalfOpen() throws InterruptedException {
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        // Wait for retry timeout
        TimeUnit.MILLISECONDS.sleep(2100);

        assertTrue(circuitBreaker.allowRequest());
        assertEquals(CircuitBreaker.State.HALF_OPEN, circuitBreaker.getState());
    }

    @Test
    void testTransitionToClosedStateFromHalfOpen() throws InterruptedException {
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        // Wait for retry timeout
        TimeUnit.MILLISECONDS.sleep(2100);

        // Simulate successful requests
        circuitBreaker.recordSuccess();
        circuitBreaker.recordSuccess();

        assertEquals(CircuitBreaker.State.CLOSED, circuitBreaker.getState());
    }

    @Test
    void testTransitionBackToOpenStateFromHalfOpen() throws InterruptedException {
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        // Wait for retry timeout
        TimeUnit.MILLISECONDS.sleep(2100);

        // Allow one request in HALF_OPEN state
        circuitBreaker.allowRequest();

        // Simulate failure
        circuitBreaker.recordFailure();

        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
    }

    @Test
    void testFailureCountResetsAfterStateChange() {
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());

        circuitBreaker.recordSuccess(); // Should reset counts when transitioning to HALF_OPEN
        circuitBreaker.recordSuccess();

        assertEquals(CircuitBreaker.State.CLOSED, circuitBreaker.getState());
    }

    @Test
    void testSuccessCountResetsAfterStateChange() throws InterruptedException {
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        // Wait for retry timeout
        TimeUnit.MILLISECONDS.sleep(2100);

        circuitBreaker.allowRequest(); // Transition to HALF_OPEN
        circuitBreaker.recordFailure(); // Transition back to OPEN

        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
        circuitBreaker.recordSuccess(); // Reset counters

        circuitBreaker.recordSuccess();
        assertEquals(CircuitBreaker.State.CLOSED, circuitBreaker.getState());
    }

    @Test
    void testStateRemainsOpenWithinRetryTimeout() throws InterruptedException {
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();
        circuitBreaker.recordFailure();

        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());

        // Wait less than the retry timeout
        TimeUnit.MILLISECONDS.sleep(1000);

        assertFalse(circuitBreaker.allowRequest());
        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
    }
}
