package com.appresso.sample;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class PathSample {
	public static void main(String ... args) throws Exception {
		FileSystem fileSystem = FileSystems.getDefault();
		Path path = fileSystem.getPath("foo.txt");
		System.out.println(path.toString());
		System.out.println(path.toAbsolutePath());
	}
}
