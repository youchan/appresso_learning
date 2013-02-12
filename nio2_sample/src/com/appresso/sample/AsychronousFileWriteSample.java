package com.appresso.sample;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsychronousFileWriteSample {
	public static void main(String ... args) throws Exception {
		AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Paths.get("output.txt"), StandardOpenOption.WRITE);
		
		ByteBuffer buffer = ByteBuffer.wrap("ƒeƒXƒg".getBytes());
		Future<Integer> readFuture = fileChannel.write(buffer, 0);
		readFuture.get();
	}
}
