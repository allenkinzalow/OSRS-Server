package com.kinztech.os.services;

import com.kinztech.os.services.impl.EntityUpdateService;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Used to represent the main service of the game.
 *
 * @author Taylor Moon <https://www.github.com/taaylormoon>
 */
public class ServiceExecutor implements Runnable {


    /**
     * If the central service is running or not.
     */
    public static final AtomicBoolean RUNNING = new AtomicBoolean(true);

    /**
     * The amount of time to wait after each consecutive game cycle.
     */
    public static final long TIME_RATE = 600;

    /**
     * A blocking queue of tasks that define the engine's logic. Handled in
     * the main thread; blocks the thread until this queue receives a 'procedural'
     * update task (in the form of a runnable).
     */
    private final BlockingQueue<Runnable> updateTasks = new LinkedBlockingQueue<>();

    /**
     * The game service logic executor. Presumably used for executing this main processor.
     */
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();


    /**
     * The background logic executor. Used for executing background logic
     * updates such as map region.
     */
    private final ScheduledExecutorService backgroundExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());


    /**
     * The executor service responsible for dispatching threads to this main
     * logic event.
     */
    private final ExecutorService updateExecutor = Executors.newWorkStealingPool();

    /**
     * Starts this executive processor.
     */
    public void startProcessor() {
		/*
		 * Start this main loop.
		 */
        service.scheduleAtFixedRate(this, 0, TIME_RATE, TimeUnit.MILLISECONDS);

        /**
         * Entity update service..
         */
        new EntityUpdateService(this).start();

		/*
		 * Start the login loop.
		 */
        //new RedirectorAsyncService(this).startAsync();
    }

    @Override
    public void run() {
        try {
            while (ServiceExecutor.RUNNING.get()) {
                try {
					/*
					 * Utilizes the update executor to take a task
					 * from the blocking queue, blocking the main thread only
					 * to resume when a new task is received.
					 */
                    updateExecutor.execute(updateTasks.take());
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        } finally {
			/*
			 * Mostly just for in case something needs to be spontaneously
			 * shutdown in the future, also to convey a more structure-oriented
			 * code base, and memory related practices.
			 */
            quit();
        }
    }

    /**
     * Orders a given logic task to be executed in the main thread after being
     * selected by the {@link java.util.concurrent.BlockingQueue#take()} method
     * in the main blocking queue.
     */
    public synchronized void submitLogicToProcessor(Runnable runnable) {
        synchronized (updateTasks) {
			/*
			 * The submitted task will be held at the head of the queue, and ran
			 * by the main thread.
			 */
            updateTasks.add(runnable);
        }
    }

    /**
     * Quits this executive processor.
     */
    public void quit() {
        RUNNING.set(false);
        backgroundExecutor.shutdown();
        service.shutdown();
    }

    /**
     * @return The background executor.
     */
    public ScheduledExecutorService getBackgroundExecutor() {
        return backgroundExecutor;
    }


    /**
     * @return the update executor
     */
    public ExecutorService getUpdateExecutor() {
        return updateExecutor;
    }

}
