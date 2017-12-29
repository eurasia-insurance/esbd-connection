package tech.lapsa.esbd.connection.beans;

import java.time.Instant;

public final class InstantMarker {

    private final long timeout;

    private Instant marker;

    public InstantMarker(final long timeout) {
	this.timeout = timeout;
    }

    public synchronized boolean isExpired() {
	return marker == null || Instant.now().minusMillis(timeout).isAfter(marker);
    }

    public synchronized void mark() {
	marker = Instant.now();
    }

    public synchronized void expire() {
	marker = null;
    }
}