package com.appresso.sample;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

public class LookupUserPricipalSample {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		UserPrincipalLookupService service = FileSystems.getDefault().getUserPrincipalLookupService();
		UserPrincipal user = service.lookupPrincipalByName("Administrator");
		System.out.println("user name: " + user.getName());
		
		Files.setOwner(Paths.get("metadata.txt"), user);
	}

}
