package tech.lapsa.esbd.connection;

import java.io.Serializable;
import java.time.Instant;

@SuppressWarnings("serial")
public class ConnectionStatus implements Serializable {

    private final String status;

    public String getStatus() {
	return status;
    }

    private final String connectionId;

    public String getConnectionId() {
	return connectionId;
    }

    private final String lastCheckError;

    public String getLastCheckError() {
	return lastCheckError;
    }

    private final Instant lastCheckInstant;

    public Instant getLastCheckInstant() {
	return lastCheckInstant;
    }

    public ConnectionStatus(String status, String connectionId, String lastCheckError, Instant lastCheckInstant) {
	this.status = status;
	this.connectionId = connectionId;
	this.lastCheckError = lastCheckError;
	this.lastCheckInstant = lastCheckInstant;
    }
}
