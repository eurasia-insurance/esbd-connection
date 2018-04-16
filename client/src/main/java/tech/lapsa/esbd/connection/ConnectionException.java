package tech.lapsa.esbd.connection;

public class ConnectionException extends Exception {
    private static final long serialVersionUID = 1L;

    public ConnectionException() {
    }

    public ConnectionException(final String message, final Throwable cause) {
	super(message, cause);
    }

    public ConnectionException(final String message) {
	super(message);
    }

    public ConnectionException(final Throwable cause) {
	super(cause);
    }
}
