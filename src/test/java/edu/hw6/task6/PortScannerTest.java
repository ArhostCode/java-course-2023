package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PortScannerTest {

    @Test
    @DisplayName("Тестирование PortScanner#isPortBusy на TCP")
    public void isPortBusy_shouldReturnBusyTCP() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(6060)) {
            Optional<BusyPort> busyPort = PortScanner.isPortBusy(6060, BusyPort.Protocol.TCP);
            Assertions.assertThat(busyPort).isPresent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Тестирование PortScanner#isPortBusy на TCP")
    public void isPortBusy_shouldReturnBusyUDP() throws IOException {
        try (DatagramSocket serverSocket = new DatagramSocket(6060)) {
            Optional<BusyPort> busyPort = PortScanner.isPortBusy(6060, BusyPort.Protocol.UDP);
            Assertions.assertThat(busyPort).isPresent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
