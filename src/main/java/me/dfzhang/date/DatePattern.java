package me.dfzhang.date;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DatePattern
 * 
 * @Version v1.0
 * @Date 2017年12月16日 下午4:06:18
 * @Author 854154025@qq.com
 * 
 * @Description 常用时间格式
 * 
 */
public class DatePattern {

	public static final String TIME_h = "h";
	public static final String TIME_a = "a";
	public static final String TIME_ah = "ah";
	public static final String TIME_HH = "HH";
	public static final String TIME_mm = "mm";
	public static final String TIME_ss = "ss";
	public static final String TIME_SSS = "SSS";
	public static final String TIME_hmmss = "hmmss";
	public static final String TIME_h_mm_ss = "h:mm:ss";
	public static final String TIME_ahmmss = "ahmmss";
	public static final String TIME_ah_mm_ss = "ah:mm:ss";
	public static final String TIME_HHmmss = "HHmmss";
	public static final String TIME_HH_mm_ss = "HH:mm:ss";
	public static final String DATE_yy = "yy";
	public static final String DATE_yyyy = "yyyy";
	public static final String DATE_MM = "MM";
	public static final String DATE_dd = "dd";
	public static final String DATE_yyMM = "yyMM";
	public static final String DATE_yyyyMM = "yyyyMM";
	public static final String DATE_yyyy_MM = "yyyy-MM";
	public static final String DATE_yyMMdd = "yyMMdd";
	public static final String DATE_yyyyMMdd = "yyyyMMdd";
	public static final String DATE_yy_MM_dd = "yy-MM-dd";
	public static final String DATE_yyyy_MM_dd = "yyyy-MM-dd";

	public static final String DATETIME_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String DATETIME_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String DATETIME_yy_MM_dd_h_mm_ss = "yyyy-MM-dd h:mm:ss";
	public static final String DATETIME_yy_MM_dd_h_mm_ss_Z = "yyyy-MM-dd h:mm:ssZ";
	public static final String DATETIME_yy_MM_dd_h_mm_ss_UTC = "yyyy-MM-dd h:mm:ss'Z'";
	public static final String DATETIME_yy_MM_dd_h_mm_ss_SSS = "yyyy-MM-dd h:mm:ss.SSS";
	public static final String DATETIME_yy_MM_dd_h_mm_ss_SSS_Z = "yyyy-MM-dd h:mm:ss.SSSZ";
	public static final String DATETIME_yy_MM_dd_h_mm_ss_SSS_UTC = "yyyy-MM-dd h:mm:ss.SSS'Z'";
	public static final String DATETIME_yy_MM_dd_ah_mm_ss = "yyyy-MM-dd ah:mm:ss";
	public static final String DATETIME_yy_MM_dd_ah_mm_ss_Z = "yyyy-MM-dd ah:mm:ssZ";
	public static final String DATETIME_yy_MM_dd_ah_mm_ss_SSS = "yyyy-MM-dd ah:mm:ss.SSS";
	public static final String DATETIME_yy_MM_dd_ah_mm_ss_SSS_Z = "yyyy-MM-dd ah:mm:ss.SSSZ";
	public static final String DATETIME_yy_MM_dd_ah_mm_ss_SSS_UTC = "yyyy-MM-dd ah:mm:ss.SSS'Z'";
	public static final String DATETIME_yy_MM_dd_HH_mm_ss = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATETIME_yy_MM_dd_HH_mm_ss_Z = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String DATETIME_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_yyyy_MM_dd_HH_mm_ss_Z = "yyyy-MM-dd HH:mm:ssZ";
	public static final String DATETIME_yyyy_MM_ddTHH_mm_ss = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATETIME_yyyy_MM_ddTHH_mm_ss_Z = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String DATETIME_yyyy_MM_ddTHH_mm_ss_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final String DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final String SLASH_DATE_yy_MM_dd = "yy/MM/dd";
	public static final String SLASH_DATE_yyyy_MM_dd = "yyyy/MM/dd";
	public static final String SLASH_DATETIME_yyyy_MM_dd_h_mm_ss = "yyyy/MM/dd h:mm:ss";
	public static final String SLASH_DATETIME_yyyy_MM_dd_h_mm_ss_Z = "yyyy/MM/dd h:mm:ssZ";
	public static final String SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss = "yyyy/MM/dd ah:mm:ss";
	public static final String SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss_Z = "yyyy/MM/dd ah:mm:ssZ";
	public static final String SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss_UTC = "yyyy/MM/dd ah:mm:ss'Z'";
	public static final String SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";
	public static final String SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss_Z = "yyyy/MM/dd HH:mm:ssZ";
	public static final String SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss_UTC = "yyyy/MM/dd HH:mm:ss'Z'";
	public static final String SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss = "yyyy/MM/dd'T'HH:mm:ss";
	public static final String SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS = "yyyy/MM/dd'T'HH:mm:ss.SSS";
	public static final String SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_Z = "yyyy/MM/dd'T'HH:mm:ss.SSSZ";
	public static final String SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_UTC = "yyyy/MM/dd'T'HH:mm:ss.SSS'Z'";

	public static final String CHINESE_TIME_h_mm_ss = "h时mm分ss秒";
	public static final String CHINESE_TIME_AH_MM_SS = "ah时mm分ss秒";
	public static final String CHINESE_TIME_HH_mm_ss = "HH时mm分ss秒";
	public static final String CHINESE_DATE_yy_MM_dd = "yy年MM月dd日";
	public static final String CHINESE_DATE_yyyy_MM_dd = "yyyy年MM月dd日";
	public static final String CHINESE_DATETIME_yyyy_MM_ddh_mm_ss = "yyyy年MM月dd日h时mm分ss秒";
	public static final String CHINESE_DATETIME_yyyy_MM_ddh_mm_ss_Z = "yyyy年MM月dd日h时mm分ss秒Z";
	public static final String CHINESE_DATETIME_yyyy_MM_ddah_mm_ss = "yyyy年MM月dd日ah时mm分ss秒";
	public static final String CHINESE_DATETIME_yyyy_MM_ddah_mm_ss_Z = "yyyy年MM月dd日ah时mm分ss秒Z";
	public static final String CHINESE_DATETIME_yyyy_MM_ddHH_mm_ss = "yyyy年MM月dd日HH时mm分ss秒";
	public static final String CHINESE_DATETIME_yyyy_MM_ddHH_mm_ss_Z = "yyyy年MM月dd日HH时mm分ss秒Z";
	public static final String CHINESE_DATETIME_yyyy_MM_dd_HH_mm_ss = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String CHINESE_DATETIME_yyyy_MM_dd_HH_mm_ss_Z = "yyyy年MM月dd日 HH时mm分ss秒";

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = DatePattern.class.getFields();
		System.out.println(fields.length);
		for (Field field : fields) {
			System.out.println("cached.put(" + field.getName() + ",DateTimeFormatter.ofPattern("
					+ field.getName() + "));");
		}
	}

	private static Map<String, DateTimeFormatter> cached = new ConcurrentHashMap<>(70);
	static {
		cached.put(TIME_h, DateTimeFormatter.ofPattern(TIME_h));
		cached.put(TIME_a, DateTimeFormatter.ofPattern(TIME_a));
		cached.put(TIME_ah, DateTimeFormatter.ofPattern(TIME_ah));
		cached.put(TIME_HH, DateTimeFormatter.ofPattern(TIME_HH));
		cached.put(TIME_mm, DateTimeFormatter.ofPattern(TIME_mm));
		cached.put(TIME_ss, DateTimeFormatter.ofPattern(TIME_ss));
		cached.put(TIME_SSS, DateTimeFormatter.ofPattern(TIME_SSS));
		cached.put(TIME_hmmss, DateTimeFormatter.ofPattern(TIME_hmmss));
		cached.put(TIME_h_mm_ss, DateTimeFormatter.ofPattern(TIME_h_mm_ss));
		cached.put(TIME_ahmmss, DateTimeFormatter.ofPattern(TIME_ahmmss));
		cached.put(TIME_ah_mm_ss, DateTimeFormatter.ofPattern(TIME_ah_mm_ss));
		cached.put(TIME_HHmmss, DateTimeFormatter.ofPattern(TIME_HHmmss));
		cached.put(TIME_HH_mm_ss, DateTimeFormatter.ofPattern(TIME_HH_mm_ss));
		cached.put(DATE_yy, DateTimeFormatter.ofPattern(DATE_yy));
		cached.put(DATE_yyyy, DateTimeFormatter.ofPattern(DATE_yyyy));
		cached.put(DATE_MM, DateTimeFormatter.ofPattern(DATE_MM));
		cached.put(DATE_dd, DateTimeFormatter.ofPattern(DATE_dd));
		cached.put(DATE_yyMM, DateTimeFormatter.ofPattern(DATE_yyMM));
		cached.put(DATE_yyyyMM, DateTimeFormatter.ofPattern(DATE_yyyyMM));
		cached.put(DATE_yyyy_MM, DateTimeFormatter.ofPattern(DATE_yyyy_MM));
		cached.put(DATE_yyMMdd, DateTimeFormatter.ofPattern(DATE_yyMMdd));
		cached.put(DATE_yyyyMMdd, DateTimeFormatter.ofPattern(DATE_yyyyMMdd));
		cached.put(DATE_yy_MM_dd, DateTimeFormatter.ofPattern(DATE_yy_MM_dd));
		cached.put(DATE_yyyy_MM_dd, DateTimeFormatter.ofPattern(DATE_yyyy_MM_dd));
		cached.put(DATETIME_yyyyMMddHHmmss, DateTimeFormatter.ofPattern(DATETIME_yyyyMMddHHmmss));
		cached.put(DATETIME_yyyyMMddHHmmssSSS,
				DateTimeFormatter.ofPattern(DATETIME_yyyyMMddHHmmssSSS));
		cached.put(DATETIME_yy_MM_dd_h_mm_ss,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_h_mm_ss));
		cached.put(DATETIME_yy_MM_dd_h_mm_ss_Z,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_h_mm_ss_Z));
		cached.put(DATETIME_yy_MM_dd_h_mm_ss_UTC,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_h_mm_ss_UTC));
		cached.put(DATETIME_yy_MM_dd_h_mm_ss_SSS,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_h_mm_ss_SSS));
		cached.put(DATETIME_yy_MM_dd_h_mm_ss_SSS_Z,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_h_mm_ss_SSS_Z));
		cached.put(DATETIME_yy_MM_dd_h_mm_ss_SSS_UTC,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_h_mm_ss_SSS_UTC));
		cached.put(DATETIME_yy_MM_dd_ah_mm_ss,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_ah_mm_ss));
		cached.put(DATETIME_yy_MM_dd_ah_mm_ss_Z,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_ah_mm_ss_Z));
		cached.put(DATETIME_yy_MM_dd_ah_mm_ss_SSS,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_ah_mm_ss_SSS));
		cached.put(DATETIME_yy_MM_dd_ah_mm_ss_SSS_Z,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_ah_mm_ss_SSS_Z));
		cached.put(DATETIME_yy_MM_dd_ah_mm_ss_SSS_UTC,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_ah_mm_ss_SSS_UTC));
		cached.put(DATETIME_yy_MM_dd_HH_mm_ss,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_HH_mm_ss));
		cached.put(DATETIME_yy_MM_dd_HH_mm_ss_Z,
				DateTimeFormatter.ofPattern(DATETIME_yy_MM_dd_HH_mm_ss_Z));
		cached.put(DATETIME_yyyy_MM_dd_HH_mm_ss,
				DateTimeFormatter.ofPattern(DATETIME_yyyy_MM_dd_HH_mm_ss));
		cached.put(DATETIME_yyyy_MM_dd_HH_mm_ss_Z,
				DateTimeFormatter.ofPattern(DATETIME_yyyy_MM_dd_HH_mm_ss_Z));
		cached.put(DATETIME_yyyy_MM_ddTHH_mm_ss,
				DateTimeFormatter.ofPattern(DATETIME_yyyy_MM_ddTHH_mm_ss));
		cached.put(DATETIME_yyyy_MM_ddTHH_mm_ss_Z,
				DateTimeFormatter.ofPattern(DATETIME_yyyy_MM_ddTHH_mm_ss_Z));
		cached.put(DATETIME_yyyy_MM_ddTHH_mm_ss_SSS,
				DateTimeFormatter.ofPattern(DATETIME_yyyy_MM_ddTHH_mm_ss_SSS));
		cached.put(DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_Z,
				DateTimeFormatter.ofPattern(DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_Z));
		cached.put(DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_UTC,
				DateTimeFormatter.ofPattern(DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_UTC));
		cached.put(SLASH_DATE_yy_MM_dd, DateTimeFormatter.ofPattern(SLASH_DATE_yy_MM_dd));
		cached.put(SLASH_DATE_yyyy_MM_dd, DateTimeFormatter.ofPattern(SLASH_DATE_yyyy_MM_dd));
		cached.put(SLASH_DATETIME_yyyy_MM_dd_h_mm_ss,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_dd_h_mm_ss));
		cached.put(SLASH_DATETIME_yyyy_MM_dd_h_mm_ss_Z,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_dd_h_mm_ss_Z));
		cached.put(SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss));
		cached.put(SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss_Z,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss_Z));
		cached.put(SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss_UTC,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_dd_ah_mm_ss_UTC));
		cached.put(SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss));
		cached.put(SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss_Z,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss_Z));
		cached.put(SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss_UTC,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_dd_HH_mm_ss_UTC));
		cached.put(SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss));
		cached.put(SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS));
		cached.put(SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_Z,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_Z));
		cached.put(SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_UTC,
				DateTimeFormatter.ofPattern(SLASH_DATETIME_yyyy_MM_ddTHH_mm_ss_SSS_UTC));
		cached.put(CHINESE_TIME_h_mm_ss, DateTimeFormatter.ofPattern(CHINESE_TIME_h_mm_ss));
		cached.put(CHINESE_TIME_AH_MM_SS, DateTimeFormatter.ofPattern(CHINESE_TIME_AH_MM_SS));
		cached.put(CHINESE_TIME_HH_mm_ss, DateTimeFormatter.ofPattern(CHINESE_TIME_HH_mm_ss));
		cached.put(CHINESE_DATE_yy_MM_dd, DateTimeFormatter.ofPattern(CHINESE_DATE_yy_MM_dd));
		cached.put(CHINESE_DATE_yyyy_MM_dd, DateTimeFormatter.ofPattern(CHINESE_DATE_yyyy_MM_dd));
		cached.put(CHINESE_DATETIME_yyyy_MM_ddh_mm_ss,
				DateTimeFormatter.ofPattern(CHINESE_DATETIME_yyyy_MM_ddh_mm_ss));
		cached.put(CHINESE_DATETIME_yyyy_MM_ddh_mm_ss_Z,
				DateTimeFormatter.ofPattern(CHINESE_DATETIME_yyyy_MM_ddh_mm_ss_Z));
		cached.put(CHINESE_DATETIME_yyyy_MM_ddah_mm_ss,
				DateTimeFormatter.ofPattern(CHINESE_DATETIME_yyyy_MM_ddah_mm_ss));
		cached.put(CHINESE_DATETIME_yyyy_MM_ddah_mm_ss_Z,
				DateTimeFormatter.ofPattern(CHINESE_DATETIME_yyyy_MM_ddah_mm_ss_Z));
		cached.put(CHINESE_DATETIME_yyyy_MM_ddHH_mm_ss,
				DateTimeFormatter.ofPattern(CHINESE_DATETIME_yyyy_MM_ddHH_mm_ss));
		cached.put(CHINESE_DATETIME_yyyy_MM_ddHH_mm_ss_Z,
				DateTimeFormatter.ofPattern(CHINESE_DATETIME_yyyy_MM_ddHH_mm_ss_Z));
		cached.put(CHINESE_DATETIME_yyyy_MM_dd_HH_mm_ss,
				DateTimeFormatter.ofPattern(CHINESE_DATETIME_yyyy_MM_dd_HH_mm_ss));
		cached.put(CHINESE_DATETIME_yyyy_MM_dd_HH_mm_ss_Z,
				DateTimeFormatter.ofPattern(CHINESE_DATETIME_yyyy_MM_dd_HH_mm_ss_Z));

	}

	public static DateTimeFormatter get(String pattern) {
		if (cached.containsKey(pattern)) {
			return cached.get(pattern);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		DateTimeFormatter old = cached.putIfAbsent(pattern, formatter);
		if (old != null) {
			cached.replace(pattern, old, formatter);
		}
		return formatter;
	}
}
