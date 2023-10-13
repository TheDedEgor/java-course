package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {

        Throwable error = null;
        for (int i = 0; i < maxAttempts; i++) {
            try (var conn = manager.getConnection()) {
                conn.execute(command);
                break;
            } catch (Throwable ex) {
                error = ex;
            }
        }
        if (error != null) {
            throw new ConnectionException("Ошибка при выполнении команды", error);
        }
    }
}
