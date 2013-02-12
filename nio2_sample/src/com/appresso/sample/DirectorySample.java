package com.appresso.sample;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class DirectorySample {
	public static void main(String[] args) throws Exception {
		FileSystem fileSystem = FileSystems.getDefault();
        Path jarfile = fileSystem.getPath("commons-io-2.4.jar");

        	try (FileSystem jarFileSystem =
    		FileSystems.newFileSystem(jarfile, ClassLoader.getSystemClassLoader())) {
            showChildren(jarFileSystem.getPath("/"), "");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
	
	private static void showChildren(Path path, String bias) throws IOException {
        System.out.println(bias + path.getFileName());
 
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            DirectoryStream<Path> directory = Files.newDirectoryStream(path);
            for (Path tempPath: directory) {
                showChildren(tempPath, bias + "    ");
            }
        }
    }
}
