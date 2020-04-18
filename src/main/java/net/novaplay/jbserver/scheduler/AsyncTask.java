package net.novaplay.jbserver.scheduler;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.novaplay.jbserver.server.Server;
import net.novaplay.jbserver.utils.ThreadStore;
/**
 * @author Nukkit Project Team
 */
public abstract class AsyncTask implements Runnable {

    public static final Queue<AsyncTask> FINISHED_LIST = new ConcurrentLinkedQueue<>();

    private Object result;
    private int taskId;
    private boolean finished = false;

    public static void collectTask() {
        while (!FINISHED_LIST.isEmpty()) {
            FINISHED_LIST.poll().onCompletion(Server.getInstance());
        }
    }

    public void run() {
        this.result = null;
        this.onRun();
        this.finished = true;
        FINISHED_LIST.offer(this);
    }

    public boolean isFinished() {
        return this.finished;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean hasResult() {
        return this.result != null;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Object getFromThreadStore(String identifier) {
        return this.isFinished() ? null : ThreadStore.store.get(identifier);
    }

    public void saveToThreadStore(String identifier, Object value) {
        if (!this.isFinished()) {
            if (value == null) {
                ThreadStore.store.remove(identifier);
            } else {
                ThreadStore.store.put(identifier, value);
            }
        }
    }

    public abstract void onRun();

    public void onCompletion(Server server) {

    }

    public void cleanObject() {
        this.result = null;
        this.taskId = 0;
        this.finished = false;
    }

}
