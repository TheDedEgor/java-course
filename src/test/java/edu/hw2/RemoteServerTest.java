package edu.hw2;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RemoteServerTest {

    @Test
    @DisplayName("Проверка на ошибку")
    void validTest1() {
        try {
            var executor = new PopularCommandExecutor(new FaultyConnectionManager(), 10);
            executor.updatePackages();
        }
        catch (Throwable ex) {
            assertThat(ex).isInstanceOf(ConnectionException.class);
        }
    }

    @Test
    @DisplayName("Проверка на возможную ошибку")
    void validTest2() {
        try {
            var executor = new PopularCommandExecutor(new DefaultConnectionManager(), 5);
            executor.updatePackages();
        }
        catch (Throwable ex) {
            assertThat(ex).isInstanceOf(ConnectionException.class);
        }
    }
}
