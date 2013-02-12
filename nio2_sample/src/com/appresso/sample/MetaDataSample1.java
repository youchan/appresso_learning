package com.appresso.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

public class MetaDataSample1 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("metadata.txt");
		UserPrincipal owner = Files.getOwner(path);
		System.out.println("owner: " + owner.getName());
		FileTime lastModified = Files.getLastModifiedTime(path);
		System.out.println("lastModified: " + FileTimeUtil.format(lastModified));
		Files.write(path, new byte[0]);
		FileTime lastModified2 = Files.getLastModifiedTime(path);
		System.out.println("lastModified2: " + FileTimeUtil.format(lastModified2));
		Set<PosixFilePermission> permission = Files.getPosixFilePermissions(path);
		System.out.println("permission: " + permission.toString());
	}

}
