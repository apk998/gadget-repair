package multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool = null;
    private final List<Connection> connections = new ArrayList<>();
    private static final int MAX_CONNECTIONS = 5;

    private ConnectionPool() {
        IntStream.range(0, MAX_CONNECTIONS).forEach(i -> {
            Connection connection = new Connection();
            connection.setName("Connection " + i);
            connections.add(connection);
        });
    }

    public synchronized static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public synchronized Connection getConnection() {
        while (connections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Connection connection = connections.remove(0);
        LOGGER.info(Thread.currentThread().getName() + " acquired connection: " + connection.getName());
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        notify();
        LOGGER.info(Thread.currentThread().getName() + " released connection: " + connection.getName());
    }
}