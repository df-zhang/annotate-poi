package me.dfzhang.date;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName DateUtils
 * 
 * @Version v1.0
 * @Date 2017年12月16日
 * @Author 854154025@qq.com
 * 
 * @Description Java8时间工具类使用java.time包下的时间工具。以返回{@link java.time.ZonedDateTime}为主，其它格式可通过
 * {@link ZonedDateTime}自由转换。
 * 
 */
public final class DateUtils {
	/**
	 * 系统默认时区。DEFAULT_ZONE : {@link ZoneId#systemDefault()}
	 */
	public static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

	public static Instant toInstant(Date uDate) {
		return uDate.toInstant();
	}

	public static Instant toInstant(LocalDate localDate) {
		return toInstant(toZoned(localDate));
	}

	public static Instant toInstant(LocalDateTime localDateTime) {
		return toInstant(toZoned(localDateTime));
	}

	public static Instant toInstant(LocalTime localTime) {
		return toInstant(toZoned(localTime));
	}

	public static Instant toInstant(OffsetDateTime offsetDateTime) {
		return toInstant(toZoned(offsetDateTime));
	}

	public static Instant toInstant(ZonedDateTime zonedDateTime) {
		return zonedDateTime.toInstant();
	}

	/**
	 * 
	 * 只匹配格式为"yyyy-MM-dd'T'HH:mm:ss'Z'"或"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"的字符串。<br>
	 * 例：2011-12-03T10:15:30Z
	 * 
	 * @param dateStr 指定格式的字符串
	 * @return
	 */
	public static Instant toInstant(String dateStr) {
		return Instant.parse(dateStr);
	}

	public static void main(String[] args) {
		System.out.println(toString(new Date(), "yyyy-MM-dd'T'HH:mm:ss'Z'"));
		System.out.println(toInstant("2011-12-03T10:15:30.333+0800"));
	}

	/**
	 * 
	 * {@link java.util.Date} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.util.Date}和默认系统时区{@link ZoneId#systemDefault()}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。
	 * 
	 * @param uDate {@link Date}
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间。
	 */
	public static ZonedDateTime toZoned(Date uDate) {
		return toZoned(uDate, DEFAULT_ZONE);
	}

	/**
	 * 
	 * {@link java.util.Date} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.util.Date}和时区{@link java.time.ZoneId}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。
	 * 
	 * @param uDate 时间 {@link Date}
	 * @param zone 时区 {@link ZoneId}
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间。
	 */
	public static ZonedDateTime toZoned(Date uDate, ZoneId zone) {
		return uDate.toInstant().atZone(zone);
	}

	/**
	 * 
	 * {@link java.time.LocalDate} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.time.LocalDate}和默认系统时区{@link ZoneId#systemDefault()}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。{@link LocalDate}只有日期，因此在转换时会自动为{@link ZonedDateTime}
	 * 对象加上时间"00:00:00.000"
	 * 
	 * @param localDate {@link LocalDate}
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间
	 */
	public static ZonedDateTime toZoned(LocalDate localDate) {
		return toZoned(localDate, DEFAULT_ZONE);
	}

	/**
	 * 
	 * {@link java.time.LocalDate} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.time.LocalDate}和时区{@link java.time.ZoneId}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。{@link LocalDate}只有日期，因此在转换时会自动为{@link ZonedDateTime}
	 * 对象加上时间"00:00:00.000"
	 * 
	 * @param localDate 本地日期 {@link LocalDate}
	 * @param zone 时区 {@link ZoneId}
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间
	 */
	public static ZonedDateTime toZoned(LocalDate localDate, ZoneId zone) {
		return localDate.atStartOfDay(zone);
	}

	/**
	 * 
	 * {@link java.time.LocalTime} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.time.LocalTime}和默认系统时区{@link ZoneId#systemDefault()}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。{@link LocalTime}只有时间，因此在转换时会自动为{@link ZonedDateTime}
	 * 对象加上当天日期，如"2017-12-17"
	 * 
	 * @param localTime 本地时间{@link LocalTime}
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间
	 */
	public static ZonedDateTime toZoned(LocalTime localTime) {
		return toZoned(localTime, DEFAULT_ZONE);
	}

	/**
	 * 
	 * {@link java.time.LocalTime} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.time.LocalTime}和时区{@link java.time.ZoneId}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。{@link LocalTime}只有时间，因此在转换时会自动为{@link ZonedDateTime}
	 * 对象加上当天日期，如"2017-12-17"
	 * 
	 * @param localTime 本地时间{@link LocalTime}
	 * @param zone 时区 {@link ZoneId}
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间
	 */
	public static ZonedDateTime toZoned(LocalTime localTime, ZoneId zone) {
		return toZoned(toDateTime(localTime), zone);
	}

	/**
	 * 
	 * {@link java.time.LocalDateTime} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.time.LocalDateTime}和默认系统时区{@link ZoneId#systemDefault()}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。
	 * 
	 * @param localDateTime 本地日期时间{@link LocalDateTime}
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间
	 */
	public static ZonedDateTime toZoned(LocalDateTime localDateTime) {
		return toZoned(localDateTime, DEFAULT_ZONE);
	}

	/**
	 * 
	 * {@link java.time.LocalDateTime} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.time.LocalDateTime}和时区{@link java.time.ZoneId}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。
	 * 
	 * @param localDateTime
	 * @param zone
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间
	 */
	public static ZonedDateTime toZoned(LocalDateTime localDateTime, ZoneId zone) {
		return localDateTime.atZone(zone);
	}

	/**
	 * 
	 * {@link java.time.OffsetDateTime} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.time.OffsetDateTime}和默认系统时区{@link ZoneId#systemDefault()}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。
	 * 
	 * @param offsetDateTime 本地日期时间{@link LocalDateTime}
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间
	 */
	public static ZonedDateTime toZoned(OffsetDateTime offsetDateTime) {
		return offsetDateTime.toZonedDateTime();
	}

	/**
	 * 
	 * {@link java.time.OffsetDateTime} to {@link java.time.ZonedDateTime} <br>
	 * 根据{@link java.time.OffsetDateTime}和时区{@link java.time.ZoneId}， 返回一个包含时区的完整的日期时间对象
	 * {@link java.time.ZonedDateTime}。
	 * 
	 * @param localDateTime
	 * @param zone
	 * @return {@link ZonedDateTime}，包含时区的完整日期时间
	 */
	public static ZonedDateTime toZoned(OffsetDateTime offsetDateTime, ZoneId zone) {
		return offsetDateTime.atZoneSimilarLocal(zone);
	}

	/**
	 * 
	 * 根据{@link java.util.Date}和默认的时区，返回一个不包含日期的时间{@link java.time.LocalTime}。 例："00:00:00.000"
	 * @param uDate 时间 {@link java.util.Date}
	 * @return {@link java.time.LocalTime}
	 */
	public static LocalTime toTime(Date uDate) {
		return toTime(uDate, DEFAULT_ZONE);
	}

	/**
	 * 
	 * 根据{@link java.util.Date}和时区{@link java.time.ZoneId}，返回一个不包含日期的时间 {@link java.time.LocalTime}。
	 * 例："00:00:00.000"
	 * @param uDate {@link Date}
	 * @param zone {@link ZoneId}
	 * @return {@link LocalTime}
	 */
	public static LocalTime toTime(Date uDate, ZoneId zone) {
		return toZoned(uDate, zone).toLocalTime();
	}

	/**
	 * 
	 * 从{@link java.time.LocalDateTime}中获取时间对象
	 * 
	 * @param localDateTime 本地日期时间{@link LocalDateTime}
	 * @return {@link LocalTime}
	 */
	public static LocalTime toTime(LocalDateTime localDateTime) {
		return localDateTime.toLocalTime();
	}

	/**
	 * TODO 根据默认的格式HH:mm:ss转换为时间类型{@link LocalTime}<br>
	 * 例："10:15:30"
	 * @param timeStr "HH:mm:ss"格式的字符串
	 * @return {@link LocalTime}
	 */
	public static LocalTime toTime(String timeStr) {
		return toTime(timeStr, DatePattern.TIME_HH_mm_ss);
	}

	/**
	 * 
	 * 根据指定格式将字符串转换为时间类型{@link LocalTime}
	 * 
	 * @param timeStr 时间字符串
	 * @param pattern 时间格式，可参见{@link DatePattern}
	 * @return {@link LocalTime}
	 */
	public static LocalTime toTime(String timeStr, String pattern) {
		return toTime(timeStr, DatePattern.get(pattern));
	}

	/**
	 * 
	 * 根据指定格式{@link DatePattern}将字符串转换为时间类型{@link LocalTime}
	 * 
	 * @param timeStr 时间字符串
	 * @param pattern 时间格式，可参见{@link DatePattern}
	 * @return {@link LocalTime}
	 */
	public static LocalTime toTime(String timeStr, DateTimeFormatter pattern) {
		return LocalTime.parse(timeStr, pattern);
	}

	/**
	 * 根据{@link java.util.Date}和默认的时区，返回一个不包含具体时间的日期 {@link java.time.LocalDate}。
	 * @param uDate {@link java.util.Date}
	 * @return {@link LocalDate}
	 */
	public static LocalDate toDate(Date uDate) {
		return toDate(uDate, DEFAULT_ZONE);
	}

	/**
	 * 根据{@link java.util.Date}和时区{@link java.time.ZoneId}，返回一个不包含具体时间的日期对象
	 * {@link java.time.LocalDate}。
	 * @param uDate {@link Date}
	 * @param zone {@link ZoneId}
	 * @return {@link LocalDate}
	 */
	public static LocalDate toDate(Date uDate, ZoneId zone) {
		return toZoned(uDate, zone).toLocalDate();
	}

	public static LocalDate toDate(String dateStr) {
		return LocalDate.parse(dateStr);
	}

	public static LocalDate toDate(String dateStr, String pattern) {
		return LocalDate.parse(dateStr, DatePattern.get(pattern));
	}

	/**
	 * 根据{@link java.util.Date}和默认的时区{@link ZoneId#systemDefault()}，返回一个包含了日期及时间，但没有偏移或者时区信息的日期时间对象
	 * {@link java.time.LocalDateTime}。
	 * @param uDate {@link Date}
	 * @return {@link LocalDateTime}
	 */
	public static LocalDateTime toDateTime(Date uDate) {
		return toDateTime(uDate, DEFAULT_ZONE);
	}

	/**
	 * 根据{@link java.util.Date}和时区{@link java.time.ZoneId} ，返回一个包含了日期及时间，但没有偏移或者时区信息的日期时间对象
	 * {@link java.time.LocalDateTime}。
	 * @param uDate 时间 {@link Date}
	 * @param zone 时区 {@link ZoneId}
	 * @return {@link LocalDateTime}
	 */
	public static LocalDateTime toDateTime(Date uDate, ZoneId zone) {
		return toZoned(uDate, zone).toLocalDateTime();
	}

	/**
	 * 
	 * 将{@link java.time.LocalTime}转换为{@link java.time.LocalDateTime}。{@link LocalTime}
	 * 内没有具体日期，因此在转换时会使用当天日期进行补齐。如"2017-12-14" + "21:33:51"
	 * 
	 * @param localTime 本地时间 {@link LocalTime}
	 * @return
	 */
	public static LocalDateTime toDateTime(LocalTime localTime) {
		return localTime.atDate(LocalDate.now());
	}

	public static LocalDateTime toDateTime(LocalDate localDate) {
		return localDate.atStartOfDay();
	}

	public static LocalDateTime toDateTime(String dateStr) {
		return LocalDateTime.parse(dateStr);
	}

	public static LocalDateTime toDateTime(String dateStr, String pattern) {
		return LocalDateTime.parse(dateStr, DatePattern.get(pattern));
	}

	public static LocalDateTime toDateTime(String dateStr, DateTimeFormatter pattern) {
		return LocalDateTime.parse(dateStr, pattern);
	}

	/**
	 * 
	 * @param uDate
	 * @return
	 * 
	 * @Description 根据{@link java.util.Date}和默认的时区，返回一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的对象
	 * {@link java.time.OffsetDateTime}。
	 */
	public static OffsetDateTime toOffsetDateTime(Date uDate) {
		return toOffsetDateTime(uDate, DEFAULT_ZONE);
	}

	/**
	 * 
	 * @param date
	 * @param zone 时区
	 * @return {@link java.time.OffsetDateTime} 带时区偏移量的日期与时间
	 * 
	 * @Description 根据{@link java.util.Date}和时区{@link java.time.ZoneId}
	 * ，返回一个包含时区偏移量的完整日期时间对象，偏移量以UTC/格林威治时间为基准 {@link java.time.OffsetDateTime}。<br>
	 * 示例: 北京时间"2017-12-16T21:40:09.938+08:00"和标准UTC时间"2017-12-16T13:41:46.084Z"
	 */
	public static OffsetDateTime toOffsetDateTime(Date uDate, ZoneId zone) {
		return toZoned(uDate, zone).toOffsetDateTime();
	}

	/**
	 * 
	 * 根据{@link java.util.Date}和时区偏移量{@link java.time.ZoneOffset} ，返回一个包含时区偏移量的完整日期时间对象，偏移量以参数
	 * {@link ZoneOffset}为基准。
	 * 
	 * @param uDate {@link java.util.Date} 时间对象
	 * @param zoneOffset {@link java.time.ZoneOffset} 时区偏移量
	 * @return {@link java.time.OffsetDateTime} 带时区偏移量的日期与时间
	 */
	public static OffsetDateTime toOffsetDateTime(Date uDate, ZoneOffset zoneOffset) {
		return uDate.toInstant().atOffset(zoneOffset);
	}

	public static OffsetTime toOffsetTime(Date date, ZoneId zone) {
		return toOffsetDateTime(date, zone).toOffsetTime();
	}

	/**
	 * 
	 * @param zonedDateTime
	 * @return
	 * 
	 * @Description TODO
	 */
	public static Date toUDate(ZonedDateTime zonedDateTime) {
		return Date.from(zonedDateTime.toInstant());
	}

	public static Date toUDate(LocalTime localTime) {
		return toUDate(toDateTime(localTime));
	}

	/**
	 * 
	 * 将本地日期对象{@link java.time.LocalDate}转换为{@link java.util.Date}， 由于{@link LocalDate}
	 * 不包含时分秒等时间数据，因此在转换{@link Date}时，会自动填充时间为00:00:00，即每一天开始的那一瞬间（微秒）<br>
	 * 例：Sat Dec 16 00:00:00 CST 2017
	 * 
	 * @param localDate 本地时间对象 {@link java.time.LocalDate}
	 * @return 时间对象{@link java.util.Date}
	 */
	public static Date toUDate(LocalDate localDate) {
		return toUDate(localDate.atStartOfDay());
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param offsetDateTime
	 * @return
	 */
	public static Date toUDate(OffsetDateTime offsetDateTime) {
		return Date.from(offsetDateTime.toInstant());
	}

	public static Date toUDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(DEFAULT_ZONE).toInstant());
	}

	public static Date toUDate(String dateStr) {
		return toUDate(dateStr, DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss);
	}

	public static Date toUDate(String dateStr, String pattern) {
		return toUDate(LocalDateTime.parse(dateStr, DatePattern.get(pattern)));
	}

	public static Date toUDate(String dateStr, DateTimeFormatter pattern) {
		return toUDate(LocalDateTime.parse(dateStr, pattern));
	}

	/**
	 * @Methods format
	 * 
	 * @param date
	 * @return
	 * 
	 * @Description 默认时间格式化，{@link DateUtils#yyyy_MM_dd_HH_mm_ss} 例: "2017-12-15 15:20:30"
	 */
	public static String toString(Date date) {
		return toString(date, DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss);
	}

	/**
	 * @Methods format
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * 
	 * @Description 时间格式化方法
	 */
	public static String toString(Date date, String pattern) {
		return toString(date, DatePattern.get(pattern));
	}

	public static String toString(Date date, DateTimeFormatter pattern) {
		return toZoned(date).format(pattern);
	}

	public static String toString(LocalTime localTime) {
		return toString(localTime, DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss);
	}

	public static String toString(LocalTime localTime, String pattern) {
		return toString(localTime, DatePattern.get(pattern));
	}

	public static String toString(LocalTime localTime, DateTimeFormatter pattern) {
		return localTime.format(pattern);
	}

	public static String toString(ChronoLocalDate chrono) {
		return toString(chrono, DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss);
	}

	public static String toString(ChronoLocalDate chrono, String pattern) {
		return chrono.format(DatePattern.get(pattern));
	}

	public static String toString(ChronoLocalDate chrono, DateTimeFormatter pattern) {
		return chrono.format(pattern);
	}

	public static String toString(LocalDateTime localDateTime) {
		return toString(localDateTime, DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss);
	}

	public static String toString(LocalDateTime localDateTime, String pattern) {
		return toString(localDateTime, DatePattern.get(pattern));
	}

	public static String toString(ChronoZonedDateTime<? extends ChronoLocalDate> chrono) {
		return toString(chrono, DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss);
	}

	public static String toString(ChronoZonedDateTime<? extends ChronoLocalDate> chrono,
			String pattern) {
		return chrono.format(DatePattern.get(pattern));
	}

	public static String toString(ChronoZonedDateTime<? extends ChronoLocalDate> chrono,
			DateTimeFormatter pattern) {
		return chrono.format(pattern);
	}

	public static String toStringS(ChronoLocalDateTime<? extends ChronoLocalDate> chrono) {
		return chrono.format(DatePattern.get(DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss));
	}

	public static String toString(ChronoLocalDateTime<? extends ChronoLocalDate> chrono,
			DateTimeFormatter pattern) {
		return chrono.format(pattern);
	}

	// /**
	// *
	// * 默认时间格式化，将时间{@link java.util.Date}转换为{@link DatePattern#yyyy_MM_dd_HH_mm_ss}格式的字符串<br>
	// * 例: "2017-12-15 15:20:30"
	// *
	// * @param date 时间{@link Date}
	// * @return {@link String}
	// */
	// public static String format(Date date) {
	// return toString(date, DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss);
	// }
	//
	// public static String format(LocalDateTime localDateTime) {
	// return toString(localDateTime, DatePattern.DATETIME_yyyy_MM_dd_HH_mm_ss);
	// }
	//
	// public static String format(LocalDate localDate) {
	// return toString(localDate, DatePattern.DATE_yyyy_MM_dd);
	// }
	//
	// public static String format(LocalTime localTime) {
	// return toString(localTime, DatePattern.TIME_HH_mm_ss);
	// }

	/**
	 * 
	 * 获取当前时间的毫秒值，等同于{@link System#currentTimeMillis()}
	 * 
	 * @return 当前UTC时间的毫秒值
	 */
	public static long millis() {
		return Instant.now().toEpochMilli();
	}

	/**
	 * 
	 * 获取当前时间的年份，如2017
	 * 
	 * @return 当前时间的年份
	 */
	public static int year() {
		return year(now());
	}

	/**
	 * 
	 * 获取指定时间的年份，如2017
	 * 
	 * @param uDate
	 * @return
	 */
	public static int year(Date uDate) {
		return year(toDate(uDate));
	}

	/**
	 * 
	 * 获取指定时间的年份，如2017
	 * 
	 * @param temporalAccessor 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return
	 */
	public static int year(TemporalAccessor temporalAccessor) {
		return temporalAccessor.get(ChronoField.YEAR);
	}

	public static boolean isLeapYear() {
		return isLeapYear(now());
	}

	public static boolean isLeapYear(Date uDate) {
		return isLeapYear(toZoned(uDate));
	}

	public static boolean isLeapYear(ZonedDateTime zonedDateTime) {
		return zonedDateTime.toLocalDate().isLeapYear();
	}

	/**
	 * 
	 * 获取当前时间的月份数值，如12
	 * 
	 * @return
	 */
	public static int month() {
		return month(now());
	}

	/**
	 * 
	 * 获取指定时间的月份数值，如12
	 * 
	 * @param uDate
	 * @return
	 */
	public static int month(Date uDate) {
		return month(toDate(uDate));
	}

	/**
	 * 
	 * 获取指定时间的月份数值，如12
	 * 
	 * @param temporal 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return
	 */
	public static int month(TemporalAccessor temporal) {
		return Month.from(temporal).getValue();
	}

	/**
	 * 
	 * 获取指定时间的月份，如十二月
	 * 
	 * @return
	 */
	public static String monthString() {
		return monthString(now());
	}

	/**
	 * 
	 * 获取指定时间的月份，如十二月
	 * 
	 * @param uDate
	 * @return
	 */
	public static String monthString(Date uDate) {
		return monthString(toDate(uDate));
	}

	/**
	 * 
	 * 获取指定时间的月份，如十二月
	 * 
	 * @param temporal 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return
	 */
	public static String monthString(TemporalAccessor temporal) {
		return ChineseMonth.from(temporal).getChinese();
	}

	/**
	 * 
	 * 获取当前时间是的当前月份内的星期数，如3
	 * 
	 * @return 第几个星期
	 */
	public static int weekOfMonth() {
		return weekOfMonth(now());
	}

	/**
	 * 
	 * 获取指定时间是的时间月份内的星期数，如3
	 * 
	 * @param uDate
	 * @return 第几个星期
	 */
	public static int weekOfMonth(Date uDate) {
		return weekOfMonth(toDate(uDate));
	}

	/**
	 * 
	 * 获取指定时间是的时间月份内的星期数，如3
	 * 
	 * @param temporalAccessor 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return 第几个星期
	 */
	public static int weekOfMonth(TemporalAccessor temporalAccessor) {
		return temporalAccessor.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
	}

	public static int weekOfYear() {
		return weekOfYear(now());
	}

	public static int weekOfYear(Date uDate) {
		return weekOfYear(toDate(uDate));
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param temporalAccessor 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return
	 */
	public static int weekOfYear(TemporalAccessor temporalAccessor) {
		return temporalAccessor.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
	}

	public static int dayOfWeek() {
		return dayOfWeek(now());
	}

	public static int dayOfWeek(Date uDate) {
		return dayOfWeek(toDate(uDate));
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param temporal 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return
	 */
	public static int dayOfWeek(TemporalAccessor temporal) {
		return DayOfWeek.from(temporal).getValue();
	}

	public static ZonedDateTime dayOfWeek(DayOfWeek dayOfWeek) {
		return now().with(ChronoField.DAY_OF_WEEK, DayOfWeek.MONDAY.getValue());
	}

	public static ZonedDateTime dayOfWeek(ZonedDateTime zonedDateTime, DayOfWeek dayOfWeek) {
		return zonedDateTime.with(ChronoField.DAY_OF_WEEK, dayOfWeek.getValue());
	}

	public static String dayOfWeekString() {
		return dayOfWeekString(now());
	}

	public static String dayOfWeekString(Date uDate) {
		return dayOfWeekString(toDate(uDate));
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param temporal 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return
	 */
	public static String dayOfWeekString(TemporalAccessor temporal) {
		return ChineseDayOfWeek.from(temporal).getChinese();
	}

	public static int dayOfMonth() {
		return dayOfMonth(now());
	}

	public static int dayOfMonth(Date uDate) {
		return dayOfMonth(toDate(uDate));
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param temporalAccessor 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return
	 */
	public static int dayOfMonth(TemporalAccessor temporalAccessor) {
		return temporalAccessor.get(ChronoField.DAY_OF_MONTH);
	}

	public static int dayOfYear() {
		return dayOfYear(now());
	}

	public static int dayOfYear(Date uDate) {
		return dayOfYear(toDate(uDate));
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param temporalAccessor 如{@link java.time.LocalDate}、{@link java.time.LocalDateTime}、
	 * {@link java.time.ZoneDateTime}、{@link java.time.OffsetDate}、{@link java.time.OffsetDateTime}
	 * @return
	 */
	public static int dayOfYear(TemporalAccessor temporalAccessor) {
		return temporalAccessor.get(ChronoField.DAY_OF_YEAR);
	}

	public static boolean isAM(Date uDate) {
		return isAM(toZoned(uDate));
	}

	public static boolean isAM(TemporalAccessor temporal) {
		return ampm(temporal) == 0;
	}

	public static boolean isPM(Date uDate) {
		return isPM(toZoned(uDate));
	}

	public static boolean isPM(TemporalAccessor temporal) {
		return ampm(temporal) == 1;
	}

	public static int ampm(TemporalAccessor temporal) {
		return temporal.get(ChronoField.AMPM_OF_DAY);
	}

	public static ZonedDateTime yesterday() {
		return now().minusDays(1L);
	}

	public static String yesterString() {
		return toString(yesterday());
	}

	public static ZonedDateTime now() {
		return ZonedDateTime.now();
	}

	public static String nowString() {
		return toString(now());
	}

	public static String nowTimeString() {
		return toString(now().toLocalTime());
	}

	public static String nowDateString() {
		return toString(now().toLocalDate());
	}

	public static ZonedDateTime tomorrow() {
		return now().plusDays(1L);
	}

	public static String tomorrowString() {
		return toString(tomorrow());
	}

	public static ZonedDateTime startOfDay() {
		return startOfDay(now());
	}

	public static Date startOfDay(Date uDate) {
		return toUDate(toDate(uDate));
	}

	public static ZonedDateTime startOfDay(ZonedDateTime zonedDateTime) {
		return zonedDateTime.with(ChronoField.MILLI_OF_DAY, 0L);
	}

	public static ZonedDateTime endOfDay() {
		return endOfDay(now());
	}

	/**
	 * 
	 * 方法计算每天的最后一刻<br>
	 * 例："2017-12-16 23:59:59.999"，其中的"23:59:59.999"就是最后一刻
	 * 
	 * @param uDate {@link java.util.Date}
	 * @return {@link java.util.Date}
	 */
	public static Date endOfDay(Date uDate) {
		return toUDate(endOfDay(toZoned(uDate)));
	}

	public static ZonedDateTime endOfDay(ZonedDateTime zonedDateTime) {
		return zonedDateTime.with(ChronoField.MILLI_OF_DAY,
				ChronoField.MILLI_OF_DAY.range().getMaximum());
	}

	public static ZonedDateTime firstDayOfWeek() {
		return firstDayOfWeek(now());
	}

	public static ZonedDateTime firstDayOfWeek(ZonedDateTime zonedDateTime) {
		return dayOfWeek(zonedDateTime, DayOfWeek.MONDAY);
	}

	public static ZonedDateTime lastDayOfWeek() {
		return lastDayOfWeek(now());
	}

	public static ZonedDateTime lastDayOfWeek(ZonedDateTime zonedDateTime) {
		return dayOfWeek(zonedDateTime, DayOfWeek.SUNDAY);
	}

	public static ZonedDateTime firstDayOfPrevWeek() {
		return firstDayOfPrevWeek(now());
	}

	public static ZonedDateTime firstDayOfPrevWeek(ZonedDateTime zonedDateTime) {
		return dayOfWeek(zonedDateTime.minusDays(7L), DayOfWeek.MONDAY);
	}

	public static ZonedDateTime lastDayOfPrevWeek() {
		return lastDayOfPrevWeek(now());
	}

	public static ZonedDateTime lastDayOfPrevWeek(ZonedDateTime zonedDateTime) {
		return dayOfWeek(zonedDateTime.minusDays(7L), DayOfWeek.SUNDAY);
	}

	public static ZonedDateTime firstDayOfNextWeek() {
		return firstDayOfNextWeek(now());
	}

	public static ZonedDateTime firstDayOfNextWeek(ZonedDateTime zonedDateTime) {
		return dayOfWeek(zonedDateTime.plusDays(7L), DayOfWeek.MONDAY);
	}

	public static ZonedDateTime lastDayOfNextWeek() {
		return lastDayOfNextWeek(now());
	}

	public static ZonedDateTime lastDayOfNextWeek(ZonedDateTime zonedDateTime) {
		return dayOfWeek(zonedDateTime.plusDays(7L), DayOfWeek.SUNDAY);
	}

	public static ZonedDateTime firstDayOfMonth() {
		return now().withDayOfMonth(1);
	}

	public static ZonedDateTime lastDayOfMonth() {
		return now().with(TemporalAdjusters.lastDayOfMonth());
	}

	public static ZonedDateTime firstDayOfMonth(int month) {
		return now().withMonth(month).withDayOfMonth(1);
	}

	public static ZonedDateTime lastDayOfMonth(int month) {
		return now().withMonth(month).with(TemporalAdjusters.lastDayOfMonth());
	}

	public static long millisBetween(Date begin, Date end) {
		return millisBetween(begin.toInstant(), end.toInstant());
	}

	public static long millisBetween(Temporal begin, Temporal end) {
		return ChronoUnit.MILLIS.between(begin, end);
	}

	public static long secondsBetween(Date begin, Date end) {
		return secondsBetween(begin.toInstant(), end.toInstant());
	}

	public static long secondsBetween(Temporal begin, Temporal end) {
		return ChronoUnit.SECONDS.between(begin, end);
	}

	public static long minutesBetween(Date begin, Date end) {
		return minutesBetween(begin.toInstant(), end.toInstant());
	}

	public static long minutesBetween(Temporal begin, Temporal end) {
		return ChronoUnit.MINUTES.between(begin, end);
	}

	public static long hoursBetween(Date begin, Date end) {
		return hoursBetween(begin.toInstant(), end.toInstant());
	}

	public static long hoursBetween(Temporal begin, Temporal end) {
		return ChronoUnit.HOURS.between(begin, end);
	}

	/**
	 * 
	 * 计算两个时间之间相差的天数，例：1日到11日之间相差10天。<br>
	 * 方法使用时间来计算间隔天数，若两个参数中，begin的时间大于end的时间，如2017-12-01 23:59:59和2017-12-11
	 * 01:01:01之间，就只会返回9天，而不是10天，调用时需要注意具体时间。<br>
	 * 如果前者日期大于后者，将会返回负数。
	 * 
	 * @param temporal1 {@link java.util.Date}
	 * @param temporal2 {@link java.util.Date}
	 * @return 天数间隔 {@link java.lang.Long}
	 */
	public static long daysBetween(Date begin, Date end) {
		return daysBetween(begin.toInstant(), end.toInstant());
	}

	/**
	 * 
	 * 计算两个时间之间相差的天数，例：1日到11日之间相差10天。<br>
	 * 方法使用时间来计算间隔天数，若两个参数中，temporal1的时间大于temporal2的时间，如2017-12-01 23:59:59和2017-12-11
	 * 01:01:01之间，就只会返回9天，而不是10天<br>
	 * 如果使用了{@link java.time.LocalDateTime}进行比较，需要注意具体时间。<br>
	 * 如果前者日期大于后者，将会返回负数。
	 * 
	 * @param begin {@link java.time.LocalDateTime}或{@link java.time.LocalDate}
	 * @param end {@link java.time.LocalDateTime}或{@link java.time.LocalDate}
	 * @return 天数间隔 {@link java.lang.Long}
	 */
	public static long daysBetween(Temporal begin, Temporal end) {
		return ChronoUnit.DAYS.between(begin, end);
	}

	public static long weeksBetween(Date begin, Date end) {
		return weeksBetween(begin.toInstant(), end.toInstant());
	}

	public static long weeksBetween(Temporal begin, Temporal end) {
		return ChronoUnit.WEEKS.between(begin, end);
	}

	public static long monthsBetween(Date begin, Date end) {
		return monthsBetween(begin.toInstant(), end.toInstant());
	}

	public static long monthsBetween(Temporal begin, Temporal end) {
		return ChronoUnit.MONTHS.between(begin, end);
	}

	public static long yearsBetween(Date begin, Date end) {
		return yearsBetween(begin.toInstant(), end.toInstant());
	}

	public static long yearsBetween(Temporal begin, Temporal end) {
		return ChronoUnit.YEARS.between(begin, end);
	}

	// public static boolean isBefore(Date a, Date b) {
	// return a.before(b);
	// }
	//
	// public static boolean isBefore(LocalTime a, LocalTime b) {
	// return a.isBefore(b);
	// }
	//
	// public static boolean isBefore(LocalDate a, LocalDate b) {
	// return a.isBefore(b);
	// }
	//
	// public static boolean isBefore(LocalDateTime a, LocalDateTime b) {
	// return a.isBefore(b);
	// }

	public static boolean equals(Date a, Date b) {
		return Objects.equals(a, b);
	}

	public static boolean equals(Temporal a, Temporal b) {
		return Objects.equals(a, b);
	}

	public static boolean equalsDayOfWeek(Date a, Date b) {
		return equalsDayOfWeek(toDate(a), toDate(b));
	}

	public static boolean equalsDayOfWeek(Temporal a, Temporal b) {
		return a.get(ChronoField.DAY_OF_WEEK) == b.get(ChronoField.DAY_OF_WEEK);
	}

	public static boolean equalsDayOfMonth(Date a, Date b) {
		return equalsDayOfMonth(toDate(a), toDate(b));
	}

	public static boolean equalsDayOfMonth(Temporal a, Temporal b) {
		return a.get(ChronoField.DAY_OF_MONTH) == b.get(ChronoField.DAY_OF_MONTH);
	}

	public static boolean equalsDayOfYear(Date a, Date b) {
		return equalsDayOfYear(toDate(a), toDate(b));
	}

	public static boolean equalsDayOfYear(Temporal a, Temporal b) {
		return a.get(ChronoField.DAY_OF_YEAR) == b.get(ChronoField.DAY_OF_YEAR);
	}

	public static boolean equalsWeekOfMonth(Date a, Date b) {
		return equalsWeekOfMonth(toDate(a), toDate(b));
	}

	public static boolean equalsWeekOfMonth(Temporal a, Temporal b) {
		return a.get(ChronoField.ALIGNED_WEEK_OF_MONTH) == b.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
	}

	public static boolean equalsWeekOfYear(Date a, Date b) {
		return equalsWeekOfYear(toDate(a), toDate(b));
	}

	public static boolean equalsWeekOfYear(Temporal a, Temporal b) {
		return a.get(ChronoField.ALIGNED_WEEK_OF_YEAR) == b.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
	}

	public static boolean equalsMonth(Date a, Date b) {
		return equalsMonth(toDate(a), toDate(b));
	}

	public static boolean equalsMonth(Temporal a, Temporal b) {
		return a.get(ChronoField.MONTH_OF_YEAR) == b.get(ChronoField.MONTH_OF_YEAR);
	}

	public static boolean equalsYear(Date a, Date b) {
		return equalsYear(toDate(a), toDate(b));
	}

	public static boolean equalsYear(Temporal a, Temporal b) {
		return a.get(ChronoField.YEAR) == b.get(ChronoField.YEAR);
	}

	public static enum ChineseDayOfWeek {
		MONDAY("星期一"), TUESDAY("星期二"), WEDNESDAY("星期三"), THURSDAY("星期四"), FRIDAY("星期五"), SATURDAY(
				"星期六"), SUNDAY("星期日");

		private String chineseDayOfWeekName;

		ChineseDayOfWeek(String chineseDayOfWeekName) {
			this.chineseDayOfWeekName = chineseDayOfWeekName;
		}

		private static final ChineseDayOfWeek[] ENUMS = ChineseDayOfWeek.values();

		public String getChinese() {
			return chineseDayOfWeekName;
		}

		public static ChineseDayOfWeek of(int dayOfWeek) {
			if (dayOfWeek < 1 || dayOfWeek > 7) {
				throw new DateTimeException("Invalid value for DayOfWeek: " + dayOfWeek);
			}
			return ENUMS[dayOfWeek - 1];
		}

		public static ChineseDayOfWeek of(DayOfWeek dayOfWeek) {
			return of(dayOfWeek.getValue());
		}

		public static ChineseDayOfWeek from(TemporalAccessor temporal) {
			if (temporal instanceof DayOfWeek) {
				return of(((DayOfWeek) temporal).getValue());
			}
			try {
				return of(temporal.get(ChronoField.DAY_OF_WEEK));
			} catch (DateTimeException ex) {
				throw new DateTimeException("Unable to obtain DayOfWeek from TemporalAccessor: "
						+ temporal + " of type " + temporal.getClass().getName(), ex);
			}
		}

		public int getValue() {
			return ordinal() + 1;
		}
	}

	public static enum ChineseMonth {
		JANUARY("一月"), FEBRUARY("二月"), MARCH("三月"), APRIL("四月"), MAY("五月"), JUNE("六月"), JULY(
				"七月"), AUGUST(
						"八月"), SEPTEMBER("九月"), OCTOBER("十月"), NOVEMBER("十一月"), DECEMBER("十二月");
		private String chineseMonthOfYearName;

		ChineseMonth(String chineseMonthOfYearName) {
			this.chineseMonthOfYearName = chineseMonthOfYearName;
		}

		public String getChinese() {
			return chineseMonthOfYearName;
		}

		private static final ChineseMonth[] ENUMS = ChineseMonth.values();

		public static ChineseMonth of(int month) {
			if (month < 1 || month > 12) {
				throw new DateTimeException("Invalid value for MonthOfYear: " + month);
			}
			return ENUMS[month - 1];
		}

		public static ChineseMonth of(Month month) {
			return of(month.getValue());
		}

		public static ChineseMonth from(TemporalAccessor temporal) {
			if (temporal instanceof Month) {
				return of(((Month) temporal).getValue());
			}
			try {
				if (IsoChronology.INSTANCE.equals(Chronology.from(temporal)) == false) {
					temporal = LocalDate.from(temporal);
				}
				return of(temporal.get(ChronoField.MONTH_OF_YEAR));
			} catch (DateTimeException ex) {
				throw new DateTimeException("Unable to obtain Month from TemporalAccessor: "
						+ temporal + " of type " + temporal.getClass().getName(), ex);
			}
		}

		public int getValue() {
			return ordinal() + 1;
		}
	}
}
