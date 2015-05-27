package net.ins.arachnid.domain;

import java.io.IOException;

/**
 * Created by ins on 5/25/15.
 */
public class TrackParseException extends IOException {
    public TrackParseException() {
    }

    public TrackParseException(String message) {
        super(message);
    }

    public TrackParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrackParseException(Throwable cause) {
        super(cause);
    }
}
