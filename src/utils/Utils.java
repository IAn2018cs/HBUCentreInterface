package utils;

public class Utils {
	
	// 将long类型的时间转换成通俗的时间字符串
	public static String TimeFormat(long time) {
		String result = "";
		// 计算天数
		long days = time / (1000 * 60 * 60 * 24);
		// 计算小时数
		long hours = (time - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		// 计算分钟数
		long m = (time - days * (1000 * 60 * 60 * 24) - hours
				* (1000 * 60 * 60))
				/ (1000 * 60);
		// 计算秒数
		long s = (time - days * (1000 * 60 * 60 * 24) - hours
				* (1000 * 60 * 60) - m * (1000 * 60)) / 1000;
		if (days != 0) {
			result += (days + "天");
		}
		if (hours != 0) {
			result += (hours + "小时");
		}
		if (m != 0) {
			result += (m + "分钟");
		}
		if (s != 0) {
			result += (s + "秒");
		}
		return result;
	}
}
