package org.umit.android.javasockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.Enumeration;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class javasockets extends Activity {
    
	//yahoo's ip address
	public static String serverip = "67.195.160.76";
   	Process p;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //isReachable
        Button reachable = (Button)findViewById(R.id.ping_reachable);
        reachable.setOnClickListener(ping_reachable);
        
        //network interfaces
        Button getNetwork = (Button)findViewById(R.id.network_interfaces);
        getNetwork.setOnClickListener(network_interfaces);
        
        //Echo Port 7 Ping (Datagram channel)
        Button echoping = (Button)findViewById(R.id.echo_ping);
        echoping.setOnClickListener(echo_ping);
        
        //Ping port 13 (Socket Channel)
        Button sockping = (Button)findViewById(R.id.socket_ping);
        sockping.setOnClickListener(socket_ping);
                
        //Ping shell command
        Button pingshell = (Button)findViewById(R.id.command_ping);
        pingshell.setOnClickListener(command_ping);
               
        //C code ping
        Button pingnative = (Button)findViewById(R.id.native_ping);
        pingnative.setOnClickListener(native_ping);
        
    }
    
    private OnClickListener ping_reachable = new OnClickListener() {
        public void onClick(View v) {
        	checkReachable(serverip);
        }
    };
    
    private void checkReachable(String address)
    {
    	try {	
        	InetAddress addr = InetAddress.getByName(serverip);
        	boolean reachable = addr.isReachable(2000);
        	Log.e("JAVASOCKET TEST isReachable", "Result = " + reachable);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
    
    private OnClickListener socket_ping = new OnClickListener() {
        public void onClick(View v) {
        	ping_socket(serverip);
        }
    };
    
    private void ping_socket(String addr)
    {
    	InetSocketAddress address;
    	SocketChannel channel = null;
    	try {
			address = new InetSocketAddress(InetAddress.getByName(addr), 13);
			try {
				channel = SocketChannel.open();
				channel.configureBlocking(false);
				boolean connected = channel.connect(address);
				if(connected) {
					channel.close();
					Log.e("JAVASOCKET TEST socket_ping", "connected");	
				}
				else Log.e("JAVASOCKET TEST socket_ping", "not connected");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
    	catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }    
    
    private OnClickListener network_interfaces = new OnClickListener() {
        public void onClick(View v) {
        	try {
        		for(Enumeration<NetworkInterface> list = NetworkInterface.getNetworkInterfaces(); list.hasMoreElements();){
        			NetworkInterface i = list.nextElement();
        			Log.e("JAVASOCKET TEST network_interfaces", "display name " + i.getDisplayName());        			
        			for(Enumeration<InetAddress> addresses = i.getInetAddresses(); addresses.hasMoreElements();)
        			{
        				String address = addresses.nextElement().toString().substring(1);
        				Log.e("JAVASOCKET TEST InetAddress", address);
        				
        				ping_echo(address);
        				checkReachable(address);
        				ping_socket(address);
        				ping_shell(address);
        			}
        		}
        	}
        	catch (Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    };
    
    private OnClickListener echo_ping = new OnClickListener() {
        public void onClick(View v) {
        	ping_echo(serverip);
        }
    };
    
    private void ping_echo(String address)
    {
    	try {
    		ByteBuffer msg = ByteBuffer.wrap("Hello".getBytes());
    		ByteBuffer response = ByteBuffer.allocate("Hello".getBytes().length);
    		
    		InetSocketAddress sockaddr = new InetSocketAddress(serverip, 7);
    		DatagramChannel dgc = DatagramChannel.open();
    		dgc.configureBlocking(false);
    		dgc.connect(sockaddr);
    		dgc.send(msg, sockaddr);
    		Thread.sleep(1000);
    		dgc.receive(response);
    		
    		String received = new String(response.array());
			Log.e("JAVASOCKET TEST echo_ping", received);
    		}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    private OnClickListener command_ping = new OnClickListener() {
        public void onClick(View v) {
        	ping_shell(serverip);
        }
    };
    
    private void ping_shell(String address)
    {
    	ping_thread t = new ping_thread(address);
    	t.run();
    }
    
    private OnClickListener native_ping = new OnClickListener() {
        public void onClick(View v) {
        }
    };   
}//class
