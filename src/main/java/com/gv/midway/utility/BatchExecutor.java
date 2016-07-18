package com.gv.midway.utility;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class BatchExecutor {

    private static final Logger LOGGER = Logger.getLogger(BatchExecutor.class.getName());

    private volatile static BatchExecutor batchExecutor;

    public ExecutorService executorService;

    private BatchExecutor() {
        LOGGER.info("singelton created");
        setExecutor();
    };

    public static BatchExecutor getBatchExecutor() {
        if (batchExecutor == null) {
            synchronized (BatchExecutor.class) {
                if (batchExecutor == null) {
                    batchExecutor = new BatchExecutor();

                }
            }
        }
        return batchExecutor;
    }

    private void setExecutor() {

        executorService = Executors.newFixedThreadPool(10);
    }

    public ExecutorService getExecutorService() {

        return executorService;
    }

    public void shutDown() {

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                LOGGER.info("Executor not able to finish task in specified time.");
                List<Runnable> droppedTasks = executorService.shutdownNow();
                LOGGER.info("Executor was abruptly shut down. "
                        + droppedTasks.size() + " tasks will not be executed.");
            }
        } catch (InterruptedException e) {
            LOGGER.error("ERROR"+e);
        }
    }

}
