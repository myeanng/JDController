package com.test.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * The common method shared in all project
 * 
 * @author Administrator
 *
 */
@Component
public class CommonHelper {
	public static final String PARA_ERROR_CODE = "7777";

	// 营业开始时间
	private static final String startTime_HHMMSS = "09:00:00";
	// 营业结束时间
	private static final String endTime_HHMMSS = "16:00:00";

	private static final String Y_M_D = "yyyyMMdd";

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String getIRealIPAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 验证是否是手机号码
	 * 
	 * @param mobile
	 *            手机号码
	 * @return boolean
	 * 
	 */
	public boolean isMobile(String mobile) {
		Pattern p = null;
		Matcher m = null;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 传入制定key值获取value
	 * 
	 * @param resMap
	 *            map对象
	 * @param key
	 *            key值
	 * @return 返回value
	 */
	public static String getHsMapValue(Map<String, Object> resMap, String key) {
		if (resMap == null || resMap.get(key) == null) {
			return "";
		}
		return resMap.get(key).toString();
	}

	/**
	 * 获取当前时间字符串
	 * 
	 * @param type
	 * @return
	 */
	public String getDataTimeString(Boolean type) {
		SimpleDateFormat formatter = null;
		if (!type) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		return formatter.format(curDate);
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @return
	 */
	public String formatDate(Date date) {
		return new SimpleDateFormat(Y_M_D).format(date);
	}

	/**
	 * 取 yyyyMMdd 格式的当前日期
	 * 
	 * @return int
	 */
	public static int getIntTodayDate() {
		Date today = new Date();
		String ss = new SimpleDateFormat("yyyyMMdd").format(today);
		int rst = 0;
		try {
			rst = Integer.parseInt(ss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	/**
	 * 
	 * @param rq
	 *            20150101
	 * @param sj
	 *            12:01:01
	 * @return
	 */
	public static Date getTimeForRQ_SJ(String rq, String sj) {
		if (rq != null && rq.length() == 8 && sj != null && sj.length() == 8) {
			try {
				return new SimpleDateFormat("yyyyMMdd HH:mm:ss").parse(rq + " " + sj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 校验营业时间 true:校验通过 在营业时间内 false:校验失败 在营业时间外
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static boolean isWorkingTime() throws ParseException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

		// 检查开始时间
		String startTime = dateSdf.format(date) + " " + startTime_HHMMSS;
		Date startDate = sdf.parse(startTime);
		if (date.getTime() < startDate.getTime()) {
			// 当前时间<营业开始时间
			return false;
		}
		// 检查结束时间
		String endTime = dateSdf.format(date) + " " + endTime_HHMMSS;
		Date endDate = sdf.parse(endTime);
		if (date.getTime() > endDate.getTime()) {
			// 当前时间>营业结束时间
			return false;
		}

		return true;
	}

	/**
	 * 判断当前时间是否在这个区间,参数格式：startTime=9:00，endTime=16:00
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isWorkingTime(String startTime, String endTime) {
		int s_hour = Integer.parseInt(startTime.split(":")[0]);
		int s_minute = Integer.parseInt(startTime.split(":")[1]);
		int e_hour = Integer.parseInt(endTime.split(":")[0]);
		int e_minute = Integer.parseInt(endTime.split(":")[1]);

		Calendar ca_now = Calendar.getInstance();
		int hour = ca_now.get(Calendar.HOUR_OF_DAY);
		int minute = ca_now.get(Calendar.MINUTE);
		if (s_hour < e_hour) {
			if ((hour > s_hour && hour < e_hour) || (hour == s_hour && minute >= s_minute)
					|| (hour == e_hour && minute < e_minute)) {
				return true;
			}
		} else if (s_hour == e_hour) {
			if (minute >= s_minute && minute < e_minute) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取两个日期之间间隔的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getIntervalDay(Date startDate, Date endDate) {
		int day = 0;
		if (null != startDate && null != endDate) {
			long end = endDate.getTime();
			long start = startDate.getTime();
			long betweenDate = (end - start) / (24 * 60 * 60 * 1000);
			day = Long.valueOf(betweenDate).intValue();
		}

		return day;
	}

	public static Date parse(String dateString, String dateFormat) {
		if (StringUtils.isEmpty(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String format(Date current, String type) {
		return new SimpleDateFormat(type).format(current);
	}

	/**
	 * 文件上传方法：通过Base64文件流上传
	 * 
	 * @param file
	 *            文件流
	 * @param fileName
	 *            文件名称
	 * @param nameTitle
	 *            新生成文件名起始字符串
	 * @param myFilePath
	 *            子文件夹路径
	 * @return 文件的Map（key=path 文件路径 ；key=savename 保存的文件名）
	 * @throws IOException
	 */
	public HashMap<String, String> uploadFile(String file, String fileName, String nameTitle, String myFilePath)
			throws IOException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>文件开始上传");
		String imgPath = "";// configReader.getImgPath()
		HashMap<String, String> fileMap = new HashMap<String, String>();
		System.out.println("创建问价夹");
		File myfile = new File(imgPath + myFilePath);
		myfile.mkdirs();
		System.out.println("创建完毕：" + imgPath + myFilePath);
		FileOutputStream fos = null;
		BASE64Decoder decoder = new BASE64Decoder();
		String path = "";
		System.out.println("Base64解码,调整异常数据");
		// Base64解码
		byte[] bytes = decoder.decodeBuffer(file);
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		Integer rdm = new Random().nextInt(10000);
		String savename = nameTitle + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + rdm.toString()
				+ fileName.substring(fileName.indexOf('.'));
		path = imgPath + myFilePath + savename;
		fos = new FileOutputStream(path);
		System.out.println("将字节数组bytes中的数据，写入文件输出流fos中,地址：" + path);
		// 将字节数组bytes中的数据，写入文件输出流fos中
		fos.write(bytes);
		fos.flush();
		fos.close();
		fileMap.put("path", path);
		fileMap.put("savename", savename);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>文件上传结束");
		return fileMap;
	}

	/**
	 * 图片下载方法
	 * 
	 * @param path
	 *            文件地址
	 * @return 文件流
	 * @throws IOException
	 */
	public String downFile(String path) throws IOException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>开始下载服务");
		FileInputStream in = null;
		byte bytes[] = null;
		String file = null;
		in = new FileInputStream(path);
		bytes = new byte[in.available()];
		System.out.println("从输入流in中,将 bytes.length 个字节的数据读入字节数组bytes中");
		// 从输入流in中,将 bytes.length 个字节的数据读入字节数组bytes中
		in.read(bytes);
		System.out.println("开始BASE64Encoder转换");
		BASE64Encoder encoder = new BASE64Encoder();
		file = encoder.encode(bytes);
		in.close();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>下载结束，读取成功，返回图片流");
		return file;
	}

	/**
	 * 比较固定日期是否在当前时间减去固定月份及天数之前
	 * 
	 * @param dateStr
	 *            固定日期yyyyMMdd
	 * @param month
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public boolean timeToCompare(String dateStr, int month, int day) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf.parse(dateStr);
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.MONTH, -month);
		cd.add(Calendar.DATE, -day);
		Date date1 = cd.getTime();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		System.out.println("当前时间减去" + month + "个月，" + day + "个天，的时间为：" + sdf1.format(date1));
		System.out.println("固定时间为：" + sdf1.format(date));
		System.out.println("比较结果为：" + date1.after(date));
		return date1.after(date);
	}

	/**
	 * 检查文件类型，是指定的文件类型返回true
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean checkFileType(String fileName, String[] fileTypes) {
		System.out.println("----------------------------开始检查文件类型----------------------------");
		String fileType = (fileName.substring(fileName.indexOf('.') + 1)).trim().toUpperCase();
		for (String type : fileTypes) {
			if (type.equals(fileType)) {
				System.out.println("该文件类型在指定类型范围内，属合法上传文件，检查通过");
				return true;
			}
		}
		System.out.println("该文件类型未在指定类型范围内，属不合法上传文件，检查不通过，返回错误消息");
		return false;
	}
}
