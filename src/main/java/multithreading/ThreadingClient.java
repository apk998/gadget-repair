package multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

public class ThreadingClient {
    private static final Logger LOGGER= LogManager.getLogger(ConnectionPool.class);
    public static void main(String[] args) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            Connection connection = connectionPool.getConnection();
            if (connection != null) {
                countAndPrintNumbers(connection, "Task 1");
                LOGGER.info("Task 1: Using connection - " + connection.getName());
                connectionPool.releaseConnection(connection);
            } else {
                LOGGER.info("Task 1: Unable to acquire a connection");
            }
        });

        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            Connection connection = connectionPool.getConnection();
            if (connection != null) {
                countAndPrintNumbers(connection, "Task 2");
                LOGGER.info("Task 2: Using connection - " + connection.getName());
                connectionPool.releaseConnection(connection);
            } else {
                LOGGER.info("Task 2: Unable to acquire a connection");
            }
        });

        try {
            CompletableFuture.allOf(task1, task2).get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static void countAndPrintNumbers(Connection connection, String taskName) {
        LOGGER.info(taskName + ": Using connection - " + connection.getName());

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    LOGGER.info(taskName + ": Count: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
    }
}