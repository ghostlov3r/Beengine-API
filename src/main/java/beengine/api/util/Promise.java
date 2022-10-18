package beengine.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class Promise<T> {

	private class Result {
		Throwable t;
		T r;
	}
	
	private final List<Consumer<Promise<T>>> listeners = new ArrayList<>();
	
	private Result result = null;
	
	private boolean cancelled;
	
	public void onResolve (Consumer<Promise<T>> listener) {
		checkCancelled();
		if (result != null) {
			listener.accept(this);
		} else {
			listeners.add(listener);
		}
	}
	
	public void onResolve (Collection<Consumer<Promise<T>>> listeners) {
		checkCancelled();
		if (result != null) {
			listeners.forEach(l -> l.accept(this));
		} else {
			this.listeners.addAll(listeners);
		}
	}
	
	public void resolve (T result) {
		if (!cancelled) {
			if (this.result != null) {
				throw new IllegalStateException("Cannot resolve promise more than once");
			}
			this.result = new Result();
			this.result.r = result;
			listeners.forEach(l -> l.accept(this));
			listeners.clear();
		}
	}
	
	public void resolveExceptionally (Throwable t) {
		if (!cancelled) {
			if (this.result != null) {
				throw new IllegalStateException("Cannot resolve promise more than once");
			}
			this.result = new Result();
			this.result.t = t;
			listeners.forEach(l -> l.accept(this));
			listeners.clear();
		}
	}
	
	public Collection<Consumer<Promise<T>>> listeners() {
		return listeners;
	}
	
	public T result ()
	{
		checkCancelled();
		
		Result r = result;
		if (r == null) {
			throw new IllegalStateException("Promise has not yet been resolved");
		}
		if (r.t != null) {
			throw new RuntimeException(r.t);
		} else {
			return r.r;
		}
	}
	
	public boolean isResolved() {
		return result != null;
	}
	
	public boolean isCompletedNormally() {
		return result != null && result.t == null;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void cancel () {
		cancelled = true;
	}
	
	private void checkCancelled () {
		if (cancelled) {
			throw new IllegalStateException("Promise has been cancelled");
		}
	}
}
