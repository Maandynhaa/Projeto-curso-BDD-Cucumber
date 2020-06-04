package br.ce.wcaquino.runners.utils;

import java.util.Date;
import java.util.Calendar;

public class DateUtils {

	public static Date obterDataDiferencaDias(int dias) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, dias);
		return cal.getTime();
	}
}
