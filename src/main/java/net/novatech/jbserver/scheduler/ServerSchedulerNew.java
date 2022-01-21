package net.novatech.jbserver.scheduler;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import co.aikar.timings.Timings;

import net.novatech.jbserver.server.Server;
import net.novatech.library.accessibility.Internal;

public class ServerSchedulerNew {

    public final static long ONE_TICK_IN_MILLIS = 50L;

    public static AtomicInteger ID_COUNTER = new AtomicInteger(0);

    private final ScheduledExecutorService serverThread = createScheduledExecutor("Server Thread", 1);
    private final ScheduledExecutorService asyncExecutor = createScheduledExecutor("Async Task Pool", 5);

    public ServerSchedulerNew(Server server) {
        scheduleRepeatable(() -> {
            Timings.fullServerTickTimer.stopTiming();
            Timings.fullServerTickTimer.startTiming();
        }, 0L, 40L, TimeUnit.MILLISECONDS);
    }

    /**
     * Invoke runnable synchronously with main world thread.
     *
     * @param runnable the runnable to be executed.
     * @return task, corresponding to described execution.
     */
    public TaskNew run(Runnable runnable) {
    	TaskNew task = new TaskNew(runnable);
        task.future = this.serverThread.submit(task::run);
        return task;
    }

    /**
     * Invoke runnable synchronously with main world thread, but delay it for given amount of time.
     *
     * @param runnable the runnable to be executed.
     * @param delay    amount of time for execution delay.
     * @param unit     unit of time.
     * @return task, corresponding to described execution.
     */
    public TaskNew schedule(Runnable runnable, long delay, TimeUnit unit) {
    	TaskNew task = new TaskNew(runnable);
        task.future = this.serverThread.schedule(task::run, delay, unit);
        return task;
    }

    /**
     * Invoke runnable synchronously with main world thread.
     * Delay it for given amount of time and run it repeatedly with given repeat delay.
     *
     * @param runnable    the runnable to be executed.
     * @param delay       amount of time for execution delay.
     * @param repeatDelay amount of time for repeatedly execution delay.
     * @param unit        unit of time.
     * @return task, corresponding to described execution.
     */
    public TaskNew scheduleRepeatable(Runnable runnable, long delay, long repeatDelay, TimeUnit unit) {
    	TaskNew task = new TaskNew(runnable);
        task.future = this.serverThread.scheduleAtFixedRate(task::run, delay, repeatDelay, unit);
        return task;
    }

    /**
     * Invoke runnable asynchronously.
     *
     * @param runnable the runnable to be executed.
     * @return task, corresponding to described execution.
     */
    public TaskNew runAsync(Runnable runnable) {
    	TaskNew task = new TaskNew(runnable);
        task.future = this.asyncExecutor.submit(task::run);
        return task;
    }

    public ScheduledExecutorService getAsyncExecutor() {
        return this.asyncExecutor;
    }

    /**
     * Invoke runnable asynchronously with given delay.
     *
     * @param runnable the runnable to be executed.
     * @param delay    amount of time for execution delay.
     * @param unit     unit of time.
     * @return task, corresponding to described execution.
     * @see Scheduler#schedule(Runnable, long, TimeUnit)
     */
    public TaskNew scheduleAsync(Runnable runnable, long delay, TimeUnit unit) {
    	TaskNew task = new TaskNew(runnable);
        task.future = this.asyncExecutor.schedule(task::run, delay, unit);
        return task;
    }

    /**
     * Invoke runnable asynchronously with given delay and repeat-delay.
     *
     * @param runnable    the runnable to be executed.
     * @param delay       amount of time for execution delay.
     * @param repeatDelay amount of time for repeatedly execution delay.
     * @param unit        unit of time.
     * @return task, corresponding to described execution.
     * @see Scheduler#scheduleRepeatable(Runnable, long, long, TimeUnit)
     */
    public TaskNew scheduleRepeatableAsync(Runnable runnable, long delay, long repeatDelay, TimeUnit unit) {
    	TaskNew task = new TaskNew(runnable);
        task.future = this.asyncExecutor.scheduleAtFixedRate(task::run, delay, repeatDelay, unit);
        return task;
    }

    @Internal
    public static ScheduledExecutorService createScheduledExecutor(String name, int threads) {
        if (threads == 1) {
            return Executors.newScheduledThreadPool(1, r -> new Thread(r, name));
        } else {
            return Executors.newScheduledThreadPool(threads, new ThreadFactory() {
                private final AtomicInteger threadNumber = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, name + " #" + threadNumber.getAndIncrement());
                }
            });
        }
    }

}