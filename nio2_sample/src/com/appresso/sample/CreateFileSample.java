package com.appresso.sample;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFileSample {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FileSystem fileSystem = FileSystems.getDefault();
        Path createdFile = fileSystem.getPath("createdFile.txt");
        Files.createFile(createdFile);
        
        Path createdDir = fileSystem.getPath("newDirectry");
        Files.createDirectory(createdDir);
        
        Path deepDir = fileSystem.getPath("newParent/newChild");
        Files.createDirectories(deepDir);
        
        Path link = fileSystem.getPath("newLink");
        Files.createLink(link, createdFile);
	}

}
