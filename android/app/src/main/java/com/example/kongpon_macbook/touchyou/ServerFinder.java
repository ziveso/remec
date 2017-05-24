package com.example.kongpon_macbook.touchyou;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by kongpon-macbook on 5/13/2017 AD.
 */

public class ServerFinder {
    /* port to send broadcast packets */
    private static final int PORT = 5910;
    /* maximum time to wait for reply */
    private static final int TIMEOUT = 4000; // 4 seconds
    private static final int MAX_PACKET_SIZE = 2048;

    public static Host[] getAvailableHost() {
        List<Host> availableHost = new ArrayList<>();
        // Find the server using UDP broadcast
        try {
            //Open a random port to send the package
            DatagramSocket c = new DatagramSocket();
            c.setBroadcast(true);

            byte[] sendData = "REQUEST_HANDSHAKE".getBytes();

            //Try the 255.255.255.255 first
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), PORT);
                c.send(sendPacket);
                System.out.println(">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
            } catch (Exception e) {
            }

            // Broadcast the message over all the network interfaces
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue; // Don't want to broadcast to the loopback interface
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }

                    // Send the broadcast package!
                    try {
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, PORT);
                        c.send(sendPacket);
                    } catch (Exception e) {
                    }
                    System.out.println(">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
                }
            }

            System.out.println(">>> Done looping over all network interfaces. Now waiting for a reply!");
            c.setSoTimeout(TIMEOUT);
            //Wait for a response
            byte[] recvBuf = new byte[MAX_PACKET_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
            c.receive(receivePacket);

            //We have a response
            System.out.println(">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());
            //Check if the message is correct
            String message = new String(receivePacket.getData()).trim();
            System.out.println(message);
            if (message.contains("RESPONSE_FROM")) {
                String name = message.split("=")[1];
                System.out.println("Client : found server at " + receivePacket.getAddress() + " " + name);
                String address = receivePacket.getAddress().getHostName();
                availableHost.add(new Host(name, address));
                //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
//            Controller_Base.setServerIp(receivePacket.getAddress());
            }

            //Close the port!
            c.close();
        } catch (IOException ex) {
//          Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableHost.toArray(new Host[availableHost.size()]);
    }
}
