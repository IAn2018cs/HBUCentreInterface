package model;

public class Update {
	private int appVersion;
	private String appDescribe;
	private String appUrl;
	private int showScansSensor;

	public int getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(int appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppDescribe() {
		return appDescribe;
	}

	public void setAppDescribe(String appDescribe) {
		this.appDescribe = appDescribe;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public int getShowScansSensor() {
		return showScansSensor;
	}

	public void setShowScansSensor(int showScansSensor) {
		this.showScansSensor = showScansSensor;
	}

}
