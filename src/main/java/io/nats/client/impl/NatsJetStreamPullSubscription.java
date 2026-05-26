// Copyright 2021-2022 The NATS Authors
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at:
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package io.nats.client.impl;

import io.nats.client.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import static io.nats.client.support.NatsConstants.NANOS_PER_MILLI;

public class NatsJetStreamPullSubscription extends NatsJetStreamSubscription {

    private final AtomicLong pullSubjectIdHolder;

    NatsJetStreamPullSubscription(String sid, String subject, NatsConnection connection, NatsDispatcher dispatcher, NatsJetStream js, String stream, String consumer, MessageManager manager) {
        super(sid, subject, null, connection, dispatcher, js, stream, consumer, manager);
        pullSubjectIdHolder = new AtomicLong();
    }

    @Override
    boolean isPullMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pull(int batchSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pull(PullRequestOptions pullRequestOptions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected String _pull(PullRequestOptions pullRequestOptions, boolean raiseStatusWarnings, PullManagerObserver pullManagerObserver) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullNoWait(int batchSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullNoWait(int batchSize, Duration expiresIn) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullNoWait(int batchSize, long expiresInMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullExpiresIn(int batchSize, Duration expiresIn) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pullExpiresIn(int batchSize, long expiresInMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Message> fetch(int batchSize, long maxWaitMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Message> fetch(int batchSize, Duration maxWait) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private List<Message> _fetch(int batchSize, long maxWaitMillis) {
        List<Message> messages = drainAlreadyBuffered(batchSize);
        int batchLeft = batchSize - messages.size();
        if (batchLeft == 0) {
            return messages;
        }
        try {
            long start = NatsSystemClock.nanoTime();
            Duration expires = Duration.ofMillis(maxWaitMillis > MIN_EXPIRE_MILLIS ? maxWaitMillis - EXPIRE_ADJUSTMENT : maxWaitMillis);
            String pullSubject = _pull(PullRequestOptions.builder(batchLeft).expiresIn(expires).build(), false, null);
            // timeout > 0 process as many messages we can in that time period
            // If we get a message that either manager handles, we try again, but
            // with a shorter timeout based on what we already used up
            long maxWaitNanos = maxWaitMillis * 1_000_000;
            long timeLeftNanos = maxWaitNanos;
            while (batchLeft > 0 && timeLeftNanos > 0) {
                Message msg = nextMessageInternal(Duration.ofNanos(timeLeftNanos));
                if (msg == null) {
                    // normal timeout
                    return messages;
                }
                switch(manager.manage(msg)) {
                    case MESSAGE:
                        messages.add(msg);
                        batchLeft--;
                        break;
                    case STATUS_TERMINUS:
                        // if there is a match, the status applies otherwise it's ignored
                        if (pullSubject.equals(msg.getSubject())) {
                            return messages;
                        }
                        break;
                    case STATUS_ERROR:
                        // if there is a match, the status applies otherwise it's ignored
                        if (pullSubject.equals(msg.getSubject())) {
                            throw new JetStreamStatusException(msg.getStatus(), this);
                        }
                        break;
                }
                // anything else, try again while we have time
                timeLeftNanos = maxWaitNanos - (NatsSystemClock.nanoTime() - start);
            }
        } catch (InterruptedException e) {
            // nextMessageInternal failed. By not throwing
            // this gives them the messages already added to the list
            Thread.currentThread().interrupt();
        }
        return messages;
    }

    private List<Message> drainAlreadyBuffered(int batchSize) {
        List<Message> messages = new ArrayList<>(batchSize);
        try {
            while (true) {
                Message msg = nextMessageInternal(null);
                if (msg == null) {
                    // no more message currently queued
                    return messages;
                }
                if (manager.manage(msg) == MessageManager.ManageResult.MESSAGE) {
                    messages.add(msg);
                    if (messages.size() == batchSize) {
                        return messages;
                    }
                }
                // since this is buffered, no non-message applies, try again
            }
        } catch (InterruptedException ignore) {
            // nextMessageInternal failed. By not throwing
            // this gives them the messages already added to the list
            Thread.currentThread().interrupt();
        }
        return messages;
    }

    private void durationGtZeroRequired(Duration duration, String label) {
        if (duration == null || duration.toMillis() <= 0) {
            throw new IllegalArgumentException(label + " wait duration must be supplied and greater than 0.");
        }
    }

    private void durationGtZeroRequired(long millis, String label) {
        if (millis <= 0) {
            throw new IllegalArgumentException(label + " wait duration must be supplied and greater than 0.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Message> iterate(int batchSize, Duration maxWait) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Message> iterate(final int batchSize, long maxWaitMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Iterator<Message> _iterate(final int batchSize, long maxWaitNanos) {
        final List<Message> buffered = drainAlreadyBuffered(batchSize);
        // if there was a full batch buffered, no need to pull, just iterate over the list you already have
        int batchLeft = batchSize - buffered.size();
        if (batchLeft == 0) {
            return new Iterator<Message>() {

                @Override
                public boolean hasNext() {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }

                @Override
                public Message next() {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }
            };
        }
        // if there were some messages buffered, reduce the raw pull batch size
        String pullSubject = _pull(PullRequestOptions.builder(batchLeft).expiresIn(Duration.ofNanos(maxWaitNanos)).build(), false, null);
        // the iterator is also more complicated
        return new Iterator<Message>() {

            int received = 0;

            boolean done = false;

            Message msg = null;

            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public Message next() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        };
    }

    static class JetStreamReaderImpl implements JetStreamReader {

        private final NatsJetStreamPullSubscription sub;

        private final int batchSize;

        private final int repullAt;

        private int currentBatchRed;

        private boolean keepGoing = true;

        public JetStreamReaderImpl(final NatsJetStreamPullSubscription sub, final int batchSize, final int repullAt) {
            this.sub = sub;
            this.batchSize = batchSize;
            this.repullAt = Math.max(1, Math.min(batchSize, repullAt));
            currentBatchRed = 0;
            sub.pull(batchSize);
        }

        @Override
        public Message nextMessage(Duration timeout) throws InterruptedException, IllegalStateException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Message nextMessage(long timeoutMillis) throws InterruptedException, IllegalStateException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Message track(Message msg) {
            if (msg != null) {
                if (++currentBatchRed == repullAt) {
                    if (keepGoing) {
                        sub.pull(batchSize);
                    }
                }
                if (currentBatchRed == batchSize) {
                    currentBatchRed = 0;
                }
            }
            return msg;
        }

        @Override
        public void stop() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JetStreamReader reader(final int batchSize, final int repullAt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
