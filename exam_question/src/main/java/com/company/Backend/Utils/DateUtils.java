package com.company.Backend.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static final String VIETNAMESE_DATE_PATTERN = "MM-dd-yyyy";

	public static String formatDate(LocalDate date) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(VIETNAMESE_DATE_PATTERN);
		return date.format(dateFormat);
	}

}
