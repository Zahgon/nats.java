package io.nats.client.support;

import io.nats.client.Message;
import io.nats.client.NatsSystemClock;
import io.nats.client.Options;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.Duration;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import static io.nats.client.support.NatsConstants.NANOS_PER_MILLI;

/**
 * This is an internal class and is only public for access.
 */
public class NatsRequestCompletableFuture extends CompletableFuture<Message> {

    // allows a small buffer to account for communication and code execution time, probably more than needed but...
    private static final long HYDRATION_TIME = 10 * NANOS_PER_MILLI;

    public enum CancelAction {

        CANCEL, REPORT, COMPLETE
    }

    private static final String CLOSING_MESSAGE = "Future cancelled, connection closing.";

    private static final String CANCEL_MESSAGE = "Future cancelled, response not registered in time, check connection status.";

    // currently 5 seconds
    private static final long DEFAULT_TIMEOUT_NANOS = Options.DEFAULT_REQUEST_CLEANUP_INTERVAL.toNanos();

    private final CancelAction cancelAction;

    private final long timeOutAfterNanoTime;

    private boolean wasCancelledClosing;

    private boolean wasCancelledTimedOut;

    private final boolean useTimeoutException;

    public NatsRequestCompletableFuture(@NonNull CancelAction cancelAction, @Nullable Duration timeout, boolean useTimeoutException) {
        this.cancelAction = cancelAction;
        timeOutAfterNanoTime = NatsSystemClock.nanoTime() + HYDRATION_TIME + (timeout == null ? DEFAULT_TIMEOUT_NANOS : timeout.toNanos());
        this.useTimeoutException = useTimeoutException;
    }

    public void cancelClosing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void cancelTimedOut() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @NonNull
    public CancelAction getCancelAction() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean useTimeoutException() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasExceededTimeout() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean wasCancelledClosing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean wasCancelledTimedOut() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
