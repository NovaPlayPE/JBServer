package net.novatech.jbserver.scheduler;

import java.util.concurrent.Future;
import java.util.concurrent.RunnableScheduledFuture;

import net.novatech.jbserver.scheduler.ServerSchedulerNew;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.utils.Utils;

public class TaskNew {
    public final int id;
    public final Runnable runnable;
    public boolean finished;
    public Future<?> future;

    TaskNew(Runnable runnable) {
        this.runnable = runnable;
        this.id = ServerSchedulerNew.ID_COUNTER.getAndIncrement();
    }

    void run() {
        try {
            this.runnable.run();
        } catch (Exception ex) {
            Server.getInstance().getLogger().error("Exception in task #" + this.id + Utils.getExceptionMessage(ex));
        }
        if (!isRepeatable())
            this.finished = true;
    }

    /**
     * Get this task id.
     *
     * @return this task id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Check whether this task is finished.
     *
     * @return if this task is finished.
     */
    public boolean isFinished() {
        return this.finished;
    }

    /**
     * Check whether this task corresponds to repeatable executions.
     *
     * @return if this task corresponds to repeatable executions.
     */
    public boolean isRepeatable() {
        return this.future instanceof RunnableScheduledFuture && ((RunnableScheduledFuture) this.future).isPeriodic();
    }

    /**
     * Cancels the task.
     *
     * @return false, whether the task is already finished or is in progress; true otherwise.
     */
    public boolean cancel() {
        return this.future.cancel(false);
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TaskNew && ((TaskNew) obj).id == this.id;
    }

}