package com.appresso.sample;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ZipFileSystemProviderSample {
	public static void main(String[] args) throws Exception {
		FileSystem fileSystem = FileSystems.getDefault();
        Path jarfile = fileSystem.getPath("commons-io-2.4.jar");
        
        // dscommon.jarに対応するFileSystemオブジェクトを取得する
        try (FileSystem jarFileSystem =
        		FileSystems.newFileSystem(jarfile, ClassLoader.getSystemClassLoader())) {
            System.out.println("JAR FileSystem: " + jarFileSystem + " Class: " + jarFileSystem.getClass());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
