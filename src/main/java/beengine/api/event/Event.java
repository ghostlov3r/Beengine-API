package beengine.api.event;

import beengine.api.Server;

/**
 * Event related classes
 */
// todo methods cancel etc only for cancellable
public abstract class Event {

    protected String eventName = null;

    private boolean cancelled = false;

    public String getEventName () {
        return eventName != null ? eventName : getClass().getSimpleName();
    }

    public void cancel () {
        ensureCancellable();
        cancelled = true;
    }

    public void uncancel () {
        ensureCancellable();
        cancelled = false;
    }

    private void ensureCancellable () {
        if (!(this instanceof Cancellable)) {
            throw new RuntimeException("Event " + eventName + " is not cancellable!");
        }
    }

    /**
     * Whether this instance of the event is currently cancelled.
     *
     * If it is cancelled, only downstream handlers
     * with annotation parameter `ignoreCancelled = false` will be called with this event.
     */
    public boolean isCancelled () {
        return cancelled;
    }

    public boolean isNotCancelled () {
        return !isCancelled();
    }
    
    public Event call () {
        Server.eventManager().callEvent(this);
        return this;
    }
    
    public abstract void handle (EventListener handler, int depth);
    
    public abstract RegisteredListener[] handlers ();
}