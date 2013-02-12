package com.appresso.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncronousServerSample2 {

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
		accept();
		
		for(int i = 0; i < 100; i++) {
			AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		    client.connect(socketAddress).get();
		    ByteBuffer dst = ByteBuffer.allocate(1024);
		    Future<Integer> future = client.read(dst);
		    int len = future.get();
		    System.out.println("accept: " + new String(dst.array(), 0, len));
		}
	}
	
	private static void accept() {
		server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
			@Override
			public void completed(AsynchronousSocketChannel ch, Void attachment) {
				try {
					ch.write(ByteBuffer.wrap("test...".getBytes()));
					ch.close();
					accept();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}

			@Override
			public void failed(Throwable exc, Void attachment) {
				exc.printStackTrace();
			}
		});
	}

}
