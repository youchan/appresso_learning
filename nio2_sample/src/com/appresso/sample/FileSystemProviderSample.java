package com.appresso.sample;

import java.nio.file.spi.FileSystemProvider;
import java.util.List;

public class FileSystemProviderSample {
	public static void main(String ... args) throws Exception {
		List<FileSystemProvider> providers = FileSystemProvider.installedProviders();

		for (FileSystemProvider provider: providers) {
		    System.out.println(provider.getScheme() + ": " + provider.getClass());
		}
	}
}
