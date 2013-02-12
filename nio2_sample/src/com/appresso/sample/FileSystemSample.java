package com.appresso.sample;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FileSystemSample {
	public static void main(String ... args) throws Exception {
		try (FileSystem fileSystem = FileSystems.getDefault()) {
		} catch (IOException ex) {
			// note: Windows�̏ꍇWindowsFileSystem.close()���T�|�[�g����Ă��Ȃ����߁A
			// UnsupportedOperationException�ɂȂ�܂��B
			// close()�͕K�������Ăׂ�Ƃ͌���Ȃ��悤�ł��B
            ex.printStackTrace();
        }
    }
}
