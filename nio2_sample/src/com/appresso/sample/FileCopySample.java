package com.appresso.sample;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class FileCopySample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileSystem fileSystem = FileSystems.getDefault();
        Path jarfile = fileSystem.getPath("commons-io-2.4.jar");
        
        // commons-io-2.4.jarに対応するFileSystemオブジェクトを取得する
        try (FileSystem jarFileSystem = FileSystems.newFileSystem(jarfile, ClassLoader.getSystemClassLoader())) {
        	Path src = jarFileSystem.getPath("/");
        	Path dest = fileSystem.getPath("commons-io-2.4");
        	Files.createDirectories(dest);
        	copyDirectory(src, dest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

	}

    private static void copyDirectory(Path src, final Path dest) throws IOException {
		FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Path target = dest.resolve(file.getRoot().relativize(file).toString());
				Files.copy(file, target, StandardCopyOption.COPY_ATTRIBUTES);
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				if (dir.getNameCount() == 0) { return FileVisitResult.CONTINUE; }
				Path target = dest.resolve(dir.getRoot().relativize(dir).toString());
				Files.copy(dir, target, StandardCopyOption.COPY_ATTRIBUTES);
				return FileVisitResult.CONTINUE;
			}
		};
		
		Files.walkFileTree(src, visitor);
	}
}
