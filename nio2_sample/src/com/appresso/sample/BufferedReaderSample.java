package com.appresso.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedReaderSample {
	public static void main(String[] args) throws Exception {
		FileSystem fileSystem = FileSystems.getDefault();
        Path jarfile = fileSystem.getPath("commons-io-2.4.jar");
        
        try (FileSystem jarFileSystem =
        		FileSystems.newFileSystem(jarfile, ClassLoader.getSystemClassLoader())) {
        	Path indexlist = jarFileSystem.getPath("/META-INF/LICENSE.txt");
        	try (BufferedReader  reader = Files.newBufferedReader(indexlist, Charset.forName("UTF-8"))) {
        		String line = null;
        		while ((line = reader.readLine()) != null) {
        			System.out.println(line);
        		}
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
