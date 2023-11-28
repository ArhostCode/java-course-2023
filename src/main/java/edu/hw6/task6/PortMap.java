package edu.hw6.task6;

import java.util.Map;

public final class PortMap {

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private static final Map<Integer, String> PORTS_MAP = Map.ofEntries(
        Map.entry(21, "FTP (File Transfer Protocol)"),
        Map.entry(22, "SSH (Secure Shell)"),
        Map.entry(25, "SMTP (Simple Mail Transfer Protocol)"),
        Map.entry(53, "DNS (Domain Name System)"),
        Map.entry(80, "HTTP (HyperText Transfer Protocol)"),
        Map.entry(443, "HTTPS (HyperText Transfer Protocol Secure)"),
        Map.entry(3306, "MySQL Database"),
        Map.entry(5432, "PostgreSQL Database"),
        Map.entry(3389, "Remote Desktop Protocol (RDP)"),
        Map.entry(27017, "MongoDB Database"),
        Map.entry(1521, "Oracle Database"),
        Map.entry(49152, "Windows RPC (Remote Procedure Call)"),
        Map.entry(5353, "mDNS (Multicast Domain Name System)"),
        Map.entry(5672, "AMQP (Advanced Message Queuing Protocol)"),
        Map.entry(5355, "LLMNR (Link-Local Multicast Name Resolution)"),
        Map.entry(49153, "Windows RPC (Remote Procedure Call)"),
        Map.entry(23, "Telnet protocol to ensure effective communication along with the remote server."),
        Map.entry(110, "Post Office Protocol version 3 for email retrieval."),
        Map.entry(143, "Internet Message Access Protocol for email retrieval."),
        Map.entry(67, "Dynamic Host Configuration Protocol for IP address allocation."),
        Map.entry(68, "Dynamic Host Configuration Protocol for IP address allocation."),
        Map.entry(123, "Network Time Protocol for time synchronization."),
        Map.entry(161, "SNMP to verify the functionality of the network and management of network."),
        Map.entry(162, "SNMP to verify the functionality of the network and management of network."),
        Map.entry(445, "Server Message Block protocol for file sharing and printer sharing."),
        Map.entry(548, "Apple Filing Protocol for file sharing between Macs."),
        Map.entry(137, "NetBIOS protocol for network communication between Windows devices."),
        Map.entry(138, "NetBIOS protocol for network communication between Windows devices."),
        Map.entry(139, "NetBIOS protocol for network communication between Windows devices."),
        Map.entry(1080, "SOCKS proxy server."),
        Map.entry(1433, "Microsoft SQL Server database server."),
        Map.entry(17500, "Dropbox")
    );

    private PortMap() {
    }

    public static String getPortName(int port) {
        return PORTS_MAP.getOrDefault(port, "");
    }
}
