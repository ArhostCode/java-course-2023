package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PortScanner {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String OUTPUT_PATTERN = "%-10s%-7d%s";
    private static final int MAX_PORT = 49152;

    private PortScanner() {
    }

    public static void printBusyPorts() {
        LOGGER.info("Протокол  Порт   Сервис");
        for (int i = 0; i < MAX_PORT; i++) {
            Optional<BusyPort> busyUDPPort = isPortBusy(i, BusyPort.Protocol.UDP);
            Optional<BusyPort> busyTCPPort = isPortBusy(i, BusyPort.Protocol.TCP);
            busyUDPPort.ifPresent(port -> LOGGER.info(
                OUTPUT_PATTERN.formatted(
                    port.protocol(),
                    port.port(),
                    port.service()
                )
            ));
            busyTCPPort.ifPresent(port -> LOGGER.info(
                OUTPUT_PATTERN.formatted(
                    port.protocol(),
                    port.port(),
                    port.service()
                )
            ));
        }
    }

    public static Optional<BusyPort> isPortBusy(int port, BusyPort.Protocol protocol) {
        return switch (protocol) {
            case TCP -> checkTCPPortBusy(port);
            case UDP -> checkUDPPortBusy(port);
        };
    }

    private static Optional<BusyPort> checkTCPPortBusy(int port) {
        try (ServerSocket ignored = new ServerSocket(port)) {
            return Optional.empty();
        } catch (Exception e) {
            return Optional.of(new BusyPort(port, BusyPort.Protocol.TCP, PortMap.getPortName(port)));
        }
    }

    private static Optional<BusyPort> checkUDPPortBusy(int port) {
        try (DatagramSocket ignored = new DatagramSocket(port)) {
            return Optional.empty();
        } catch (Exception e) {
            return Optional.of(new BusyPort(port, BusyPort.Protocol.UDP, PortMap.getPortName(port)));
        }
    }

}
