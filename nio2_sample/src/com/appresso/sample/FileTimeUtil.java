package com.appresso.sample;

import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class FileTimeUtil {
	public static String format(FileTime time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.toMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
		return format.format(cal.getTime());
	}
}
