package com.appresso.sample;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitorSample {

	public static void main(String[] args) {
		FileSystem fileSystem = FileSystems.getDefault();
        Path jarfile = fileSystem.getPath("commons-io-2.4.jar");
        
        // dscommon.jar�ɑΉ�����FileSystem�I�u�W�F�N�g���擾����
        try (FileSystem jarFileSystem =
    		FileSystems.newFileSystem(jarfile, ClassLoader.getSystemClassLoader())) {
        
	        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
	            @Override
	            public FileVisitResult visitFile(Path file,
	                                             BasicFileAttributes attrs)
	                                                 throws IOException {
	                // �t�@�C���ɖK�₵�����ɃR�[�������
	                System.out.println("Visit File: " + file);
	                
	                return FileVisitResult.CONTINUE;
	            }
	 
	            @Override
	            public FileVisitResult preVisitDirectory(Path dir, 
	                                                     BasicFileAttributes attrs)
	                                                 throws IOException {
	                // �f�B���N�g���ɓ��鎞�ɃR�[�������
	                System.out.println("Pre Visit Dir: " + dir);
	                
	                return FileVisitResult.CONTINUE;
	            }
	 
	            @Override
	            public FileVisitResult postVisitDirectory(Path dir, 
	                                                      IOException e)
	                                                 throws IOException {
	                // �f�B���N�g������o�鎞�ɃR�[�������
	                System.out.println("Post Visit Dir: " + dir);
	                
	                return FileVisitResult.CONTINUE;
	            }
	        };

	        Files.walkFileTree(jarFileSystem.getPath("/"), visitor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
