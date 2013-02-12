package com.appresso.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncronousServerSample {

	private static AsynchronousServerSocketChannel server;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		InetSocketAddress socketAddress = new InetSocketAddress("localhost", 30000);
		server = AsynchronousServerSocketChannel.open().bind(socketAddress);
		System.out.println("server address: " + socketAddress.toString());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Future<AsynchronousSocketChannel> acceptFuture = server.accept();
					try {
						AsynchronousSocketChannel ch = acceptFuture.get();
						Future<Integer> future = ch.write(ByteBuffer.wrap("test...".getBytes()));
						future.get();
						ch.close();
					} catch (InterruptedException | ExecutionException | IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}).start();
		
		for(int i = 0; i < 100; i++) {
			AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		    client.connect(socketAddress).get();
		    ByteBuffer dst = ByteBuffer.allocate(1024);
		    Future<Integer> future = client.read(dst);
		    int len = future.get();
		    System.out.println("accept: " + new String(dst.array(), 0, len));
		}
	}

}
