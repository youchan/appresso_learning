package com.appresso.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.Calendar;

public class MetaDataSample2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("metadata.txt");
		AclFileAttributeView acl = Files.getFileAttributeView(path, AclFileAttributeView.class);
		UserPrincipal owner = acl.getOwner();
		System.out.println("owner: " + owner.getName());
		for (AclEntry entry : acl.getAcl()) {
			System.out.println("acl: " + entry.toString());
		}
		BasicFileAttributeView basic = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		FileTime lastModified = basic.readAttributes().lastModifiedTime();
		System.out.println("lastModifiedTime: " + FileTimeUtil.format(lastModified));
		FileTime creationTime = basic.readAttributes().creationTime();
		System.out.println("creationTime: " + FileTimeUtil.format(creationTime));
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		FileTime now = FileTime.fromMillis(cal.getTimeInMillis());
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		FileTime adayago = FileTime.fromMillis(cal.getTimeInMillis());
		basic.setTimes(lastModified, now, adayago);
		System.out.println("creationTime: " + FileTimeUtil.format(basic.readAttributes().creationTime()));
		System.out.println("lastAccessTime: " + FileTimeUtil.format(basic.readAttributes().lastAccessTime()));
	}

}
