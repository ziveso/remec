package touchyou;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import touchyou.util.Controller;

/**
 * UDP Broadcaster for broadcasting this computer IP Address to android devices.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class UDPBroadcast {
    private DatagramSocket socket;

    /**
     * Set broadcast listening status. True to start listening to the broadcast, false to stop
     * listening to broadcast.
     * 
     * @param choice
     *            is the broadcast listening status
     */
    public void setListening(boolean choice) {
	if (socket == null || (choice && socket.isClosed())) {
	    startListen();
	} else if (!choice) {
	    stopListen();
	}
    }

    private void stopListen() {
	socket.close();
    }

    private void startListen() {
	new Thread(() -> {
	    try {
		// Keep a socket open to listen to all the UDP trafic that
		// is
		// destined for this port
		socket = new DatagramSocket(App.PORT, InetAddress.getByName("0.0.0.0"));
		socket.setBroadcast(true);

		while (true) {

		    // Receive a packet
		    byte[] recvBuf = new byte[2048];
		    DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
		    socket.receive(packet);

		    // Packet received
//		    System.out.println(getClass().getName() + ">>>Discovery packet received from: "
//			    + packet.getAddress().getHostAddress());
//		    System.out.println(
//			    getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));

		    // See if the packet holds the right command (message)
		    String message = new String(packet.getData()).trim();
		    if (message.equals("REQUEST_HANDSHAKE")) {
			byte[] sendData = ("RESPONSE_FROM=" + System.getProperty("user.name")).getBytes();

			// Send a response
			DatagramPacket sendPacket =
				new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
			socket.send(sendPacket);

//			System.out.println(getClass().getName() + ">>>Sent packet to: "
//				+ sendPacket.getAddress().getHostAddress());
			Controller.getInstance().updateIP(sendPacket.getAddress().getHostAddress());
		    }
		}
	    } catch (IOException ex) {
	    }
	}).start();
    }
}
