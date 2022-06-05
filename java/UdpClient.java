import java.io.*;
import java.net.*;

/*
 * UDP related to API & Class definition
 *
 * */

public class UdpClient implements Runnable {
	private String         ipAddress;
	private int            udpPort;
	private String         remoteIpAddress;
	private int            remoteUdpPort;
	private DatagramSocket datagramObj;
	private Thread         threadObj;
	private String         threadName;

	public UdpClient() {
    	int idx     = 0;	  
    	udpPort     = 8080;
		ipAddress   = null;
	  	datagramObj = null;
	}/*UdpClient*/

	public UdpClient(int port, String ipAddress) {

    	this.udpPort   = port;
    	this.ipAddress = ipAddress;

		try {
		  	datagramObj = new DatagramSocket(port, InetAddress.getByName(ipAddress));
		}catch(UnknownHostException | SocketException sE) {
		  	System.out.println("Error " + sE);
		}

		threadName  = "ReceiveThread";
		/*this keyword is referring to UdpServer object*/
		threadObj   = new Thread(this, threadName);
		/*Newly created thread is suspended unless start is invoked*/
		threadObj.start();

	}/*UdpClient*/
 
	public void createSocket() {

	  if((this.udpPort != 0) && (this.ipAddress != null)) {
		  try {
		    datagramObj = new DatagramSocket(this.udpPort,
											 	InetAddress.getByName(this.ipAddress));
		  }catch(UnknownHostException | SocketException sE) {
		    System.out.println("Error " + sE);
		  }
		}
	}/*createSocket*/

	public void spawnThread(String threadName) {
		if(threadName == null) {
			this.threadName = "ReceiveThread";
		}else {
      		this.threadName = threadName;						
		}

    	threadObj = new Thread(this, threadName);
		/*This invocation will kick the run method*/
		threadObj.start();
	}/*spawnThread*/

	public int sendTo(String ATCommand) {
    	try {
						
			/*Converting from string into byte Array*/
			byte[] byteArray = ATCommand.getBytes();

      		DatagramPacket AT = new DatagramPacket(byteArray, 
				  							byteArray.length,
											InetAddress.getByName(remoteIpAddress),
											udpPort);

		  	datagramObj.send(AT);

		}catch(IOException e) {
		  System.err.println("Error:"+ e);
		}
		return 1;
	}/*sendTo*/	
	
	public void run() {
		try {
    		byte[] buf = new byte[2048];
    		DatagramPacket packetObj = new DatagramPacket(buf, buf.length);
    		String ATResponse;
			
			/*Enter into forever loop*/
			for(;;) {
				datagramObj.receive(packetObj);
				//System.out.println(new String(packetObj.getData(), 0, packetObj.getLength()));
				ATResponse = new String(packetObj.getData(), 0, packetObj.getLength());
				/*Displaying Received Text in DisplayWindow*/
				Widget.getDisplayWindow().append(ATResponse + "\n");
			}

			/*To be updated*/ 
		}catch(Exception e) {
			/*To be writen*/
       		System.out.println("Error has happened" + e);						
		}
	}/*run*/

	public void setIpAddress(String ip) {
    	ipAddress = ip;					
	}/*setIpAddress*/

	public void setPort(int port) {
		udpPort = port;
	}/*setPort*/

	public String getIpAddress() {
		return ipAddress;
	}/*getIpAddress*/

	public int getPort() {
		return udpPort;
	}/*getPort*/

	public void setRemoteIpAddress(String ip) {
    	remoteIpAddress = ip;					
	}/*setIpAddress*/

	public String getRemoteIpAddress() {
		return remoteIpAddress;
	}/*getIpAddress*/

}/*UdpClient Class*/


