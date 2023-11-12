package edu.hw6.task6;

public record BusyPort(int port, Protocol protocol, String service) {
    public enum Protocol {
        TCP,
        UDP
    }
}
