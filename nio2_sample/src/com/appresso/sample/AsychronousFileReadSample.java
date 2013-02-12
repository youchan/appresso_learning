package com.appresso.sample;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsychronousFileReadSample {
	public static void main(String ... args) throws Exception {
		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int pos = 0;
		while (true) {
		    buffer.clear();
		    Future<Integer> future = fileChannel.read(buffer, pos);
		    int len = future.get();
		    if (len < 0) {
		        break;
		    }
		    pos += len;
		    System.out.write(buffer.array(), 0, len);
		}
	}
}
