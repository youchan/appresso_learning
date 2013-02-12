package com.appresso.sample;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FileSystemSample {
	public static void main(String ... args) throws Exception {
		try (FileSystem fileSystem = FileSystems.getDefault()) {
		} catch (IOException ex) {
			// note: Windowsの場合WindowsFileSystem.close()がサポートされていないため、
			// UnsupportedOperationExceptionになります。
			// close()は必ずしも呼べるとは限らないようです。
            ex.printStackTrace();
        }
    }
}
