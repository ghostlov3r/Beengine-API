package beengine.api.scheduler;

public interface Scheduler {

	TaskControl submit(Runnable task);

	TaskControl delay(int delay, Runnable task);

	TaskControl repeat(int period, Runnable task);

	TaskControl delayedRepeat(int delay, int period, Runnable task);

	boolean isQueued(Runnable task);

	void cancelAllTasks();
}
