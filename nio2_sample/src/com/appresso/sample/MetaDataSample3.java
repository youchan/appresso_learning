package com.appresso.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.Calendar;

public class MetaDataSample3 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("metadata.txt");
		UserPrincipal owner = (UserPrincipal) Files.getAttribute(path, "acl:owner");
		System.out.println("owner: " + owner.getName());
		FileTime lastModified = (FileTime) Files.getAttribute(path, "lastModifiedTime");
		System.out.println("lastModifiedTime: " + FileTimeUtil.format(lastModified));
		FileTime creationTime = (FileTime) Files.getAttribute(path, "creationTime");
		System.out.println("creationTime: " + FileTimeUtil.format(creationTime));
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		FileTime adayago = FileTime.fromMillis(cal.getTimeInMillis());
		Files.setAttribute(path, "creationTime", adayago);
		System.out.println("creationTime: " + FileTimeUtil.format((FileTime)Files.getAttribute(path, "creationTime")));
	}

}
