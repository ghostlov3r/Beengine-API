package beengine.api.event;

/**
 * This interface is implemented by an Event subclass if and only if it can be cancelled.
 *
 * The cancellation of an event directly affects whether downstream event handlers
 * without `@handleCancelled` will be called with this event.
 * Implementations may provide a direct setter for cancellation (typically by using `CancellableTrait`)
 * or implement an alternative logic (such as a function on another data field) for `isCancelled()`.
 */
public interface Cancellable {

   // var isCancelled: Boolean

    /*fun setCancelled () {
        isCancelled = true
    }*/

   /* fun cancel() {
        isCancelled = true
    }

    fun uncancel() {
        isCancelled = false
    }*/

}