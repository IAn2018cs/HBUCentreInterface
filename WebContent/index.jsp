<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中心应用测试接口</title>
</head>
<body>

	<form action="./login.do" method="get">
		一、登录接口：<br>
		账号:<input type="text" name="Account" /> <br> 
		密码:<input type="password" name="Password" /> <br> 
		<input type="submit" value="登录" /> <br> <br>
	</form>
	
	<form action="./getInfo.do" method="get">
		二、获取学生信息接口：<br>
		账号:<input type="text" name="Account" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getActivity.do" method="get">
		三、查询附近活动接口：<br>
		云子:<input type="text" name="sensoroId" /> <br> 
		学号:<input type="text" name="studentNum" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./sign.do" method="get">
		四（1）、签到接口：<br>
		学号:<input type="text" name="account" /> <br> 
		活动id:<input type="text" name="activityid" /> <br> 
		签到时间:<input type="text" name="intime" /> <br> 
		签离时间:<input type="text" name="outtime" /> <br> 
		签到地点:<input type="text" name="InLocation" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	<form action="./signout.do" method="get">
		四（2）、签离接口：<br>
		id:<input type="text" name="nid" /> <br> 
		签离时间:<input type="text" name="outtime" /> <br> 
		签离地点:<input type="text" name="OutLocation" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./addActivity.do" method="get">
		五、添加活动接口：<br>
		名称:<input type="text" name="activityname" /> <br> 
		描述:<input type="text" name="activitydec" /> <br> 
		云子id:<input type="text" name="sensorid" /> <br> 
		时间:<input type="text" name="time" /> <br> 
		结束时间:<input type="text" name="endtime" /> <br>
		位置:<input type="text" name="location" /> <br> 
		规则:<input type="text" name="rule" /> <br> 
		账号:<input type="text" name="account" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getHistoryActivity.do" method="get">
		六、查询历史签到接口：<br>
		学号:<input type="text" name="userId" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./changePassword.do" method="get">
		七、修改密码接口：<br>
		学号:<input type="text" name="Account" /> <br> 
		新密码:<input type="password" name="Password" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./feedback.do" method="get">
		八、意见反馈：<br>
		账号:<input type="text" name="account" /> <br> 
		意见:<input type="text" name="msg" /> <br> 
		手机品牌:<input type="text" name="PhoneBrand" /> <br> 
		手机型号:<input type="text" name="PhoneBrandType" /> <br> 
		android版本:<input type="text" name="AndroidVersion" /> <br> 
		是否匿名:<input type="text" name="Anonymous" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getfeedback.do" method="get">
		九、获取意见反馈：<br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getSaying.do" method="get">
		十、获取名言信息：<br>
		<input type="submit" value="提交" /> <br> <br>
	</form>

	<form action="./changeActivity.do" method="get">
		十一、修改活动接口：<br>
		活动id:<input type="text" name="Nid" /> <br> 
		名称:<input type="text" name="ActivityName" /> <br> 
		描述:<input type="text" name="ActivityDescription" /> <br> 
		时间:<input type="text" name="Time" /> <br> 
		结束时间:<input type="text" name="EndTime" /> <br>
		位置:<input type="text" name="Location" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getActivityByAccount.do" method="get">
		十二、根据组别号和活动类型查询活动：<br>
		账号组别:<input type="text" name="account" /> <br> 
		活动类型:<input type="text" name="rule" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./deleteActivityFalse.do" method="get">
		十三、删除活动：<br>
		活动id:<input type="text" name="Nid" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getSignByActivityId.do" method="get">
		十四、查看活动的签到详情：<br>
		活动id:<input type="text" name="activityId" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getDutyTime.do" method="get">
		十五、获取值班时间：<br>
		学号:<input type="text" name="studentNum" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./addBackDuty.do" method="get">
		十六、添加补班接口：<br>
		名称:<input type="text" name="activityname" /> <br> 
		时间:<input type="text" name="time" /> <br> 
		结束时间:<input type="text" name="endtime" /> <br>
		账号:<input type="text" name="account" /> <br> 
		星期:<input type="text" name="week" /> <br> 
		学号:<input type="text" name="studentNum" /> <br> 
		补班节次:<input type="text" name="BackActiveID" /> <br> 
		请假时间:<input type="text" name="LeaveTime" /> <br> 
		请假节次:<input type="text" name="LeaveActiveID" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./signUp.do" method="get">
		十七、注册接口：<br>
		学号:<input type="text" name="studentNum" /> <br> 
		密码:<input type="text" name="password" /> <br> 
		姓名:<input type="text" name="name" /> <br>
		年级:<input type="text" name="grade" /> <br> 
		班级:<input type="text" name="class" /> <br> 
		组别:<input type="text" name="groupCode" /> <br> 
		电话:<input type="text" name="Phone" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getWeekList.do" method="get">
		十八、获取周列表：<br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getRankList.do" method="get">
		十九、获取组内排名：<br>
		组别:<input type="text" name="GroupCode" /> <br> 
		周:<input type="text" name="WeekID" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./addRanks.do" method="get">
		二十、添加组内排名：<br>
		组别:<input type="text" name="GroupCode" /> <br> 
		周:<input type="text" name="Week" /> <br> 
		排名详情:<input type="text" name="Ranks" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getArticleList.do" method="get">
		二十一、获取中心物品：<br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getLoanList.do" method="get">
		二十二、获取个人租借记录：<br>
		账号:<input type="text" name="Account" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./laonArticle.do" method="get">
		二十三、租借：<br>
		账号:<input type="text" name="Account" /> <br> 
		物品ID:<input type="text" name="ArticleId" /> <br> 
		借出时间:<input type="text" name="Time" /> <br> 
		归还时间:<input type="text" name="BackTime" /> <br> 
		处理人账号:<input type="text" name="Handle" /> <br> 
		物品剩余数量:<input type="text" name="Quantity" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./backArticle.do" method="get">
		二十四、归还：<br>
		租借记录ID:<input type="text" name="LoanId" /> <br> 
		物品ID:<input type="text" name="ArticleId" /> <br> 
		实际归还时间:<input type="text" name="ActualBackTime" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./changeInfo.do" method="get">
		二十五、修改个人信息：<br>
		账号:<input type="text" name="Account" /> <br> 
		姓名:<input type="text" name="Name" /> <br> 
		年级:<input type="text" name="GradeCode" /> <br>
		班级:<input type="text" name="ClassDescription" /> <br> 
		组别:<input type="text" name="Group" /> <br> 
		电话:<input type="text" name="Phone" /> <br> 
		头像:<input type="text" name="ImageUrl" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getUserListByGroup.do" method="get">
		二十六、获取组内人员：<br>
		组别:<input type="text" name="Group" /> <br> 
		年级:<input type="text" name="Grade" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getRepairUser.do" method="get">
		二十六、获取维修人员电话：<br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./addRepair.do" method="get">
		二十七、添加报修记录：<br>
		账号:<input type="text" name="Account" /> <br> 
		报修物品:<input type="text" name="Article" /> <br> 
		处理人:<input type="text" name="Handle" /> <br> 
		时间:<input type="text" name="Time" /> <br> 
		损坏描述:<input type="text" name="Description" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getRepairList.do" method="get">
		二十八、获取报修记录：<br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getRankForMe.do" method="get">
		二十九、获取本周个人排名：<br>
		账号:<input type="text" name="Account" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getOnDutySign.do" method="get">
		三十、获取值班签到：<br>
		活动id（第几节）:<input type="text" name="ActiveID" /> <br> 
		组别:<input type="text" name="GroupCode" /> <br> 
		日期:<input type="text" name="Date" /> <br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getSignByDate.do" method="get">
		三十一、根据日期去获取活动的签到情况：<br>
		活动id:<input type="text" name="ActiveID" /> <br> 
		日期:<input type="text" name="Date" /> <br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getSignByRule.do" method="get">
		三十二、根据活动类型和日期去获取活动的签到情况：<br>
		活动类型:<input type="text" name="Rule" /> <br> 
		日期:<input type="text" name="Date" /> <br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./deleteGoods.do" method="get">
		三十三、根据id删除物品：<br>
		物品id:<input type="text" name="GoodsID" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./addGoods.do" method="get">
		三十四、添加物品：<br>
		物品名称:<input type="text" name="Name" /> <br> 
		物品类型:<input type="text" name="Type" /> <br> 
		物品数量:<input type="text" name="Quantity" /> <br> 
		物品价格:<input type="text" name="Price" /> <br> 
		物品说明:<input type="text" name="Description" /> <br> 
		物品地点:<input type="text" name="Location" /> <br> 
		物品照片:<input type="text" name="ImageUrl" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getGoodsByName.do" method="get">
		三十五、根据物品名字查物品：<br>
		物品名称:<input type="text" name="Name" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getLoadUser.do" method="get">
		三十六、获取物品当前租借人信息：<br>
		物品id:<input type="text" name="GoodsID" /> <br> 
		归还状态:<input type="text" name="Status" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./changeGoods.do" method="get">
		三十七、根据id修改物品信息<br>
		物品名ID:<input type="text" name="ID" /> <br> 
		物品名称:<input type="text" name="Name" /> <br> 
		物品类型:<input type="text" name="Type" /> <br> 
		物品数量:<input type="text" name="Quantity" /> <br> 
		物品价格:<input type="text" name="Price" /> <br> 
		物品说明:<input type="text" name="Description" /> <br> 
		物品地点:<input type="text" name="Location" /> <br> 
		物品照片:<input type="text" name="ImageUrl" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./changeUserType.do" method="get">
		三十八、修改用户类型<br>
		账号:<input type="text" name="Account" /> <br> 
		用户类型:<input type="text" name="Type" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./auth.do" method="get">
		三十九、获取上传凭证接口：<br>
		存储空间名:<input type="text" name="Bucket" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getDutyTimeForWeek.do" method="get">
		四十、获取本周值班时间：<br>
		学号:<input type="text" name="studentNum" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getDutyTimeForWeekByGroup.do" method="get">
		四十一、根据组别获取本周值班时间：<br>
		组别:<input type="text" name="GroupCode" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getActivityForNoDuty.do" method="get">
		四十二、根据云子id获取活动 无值班表方式：<br>
		云子id:<input type="text" name="sensoroId" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getInterestGroupList.do" method="get">
		四十三、获取兴趣小组：<br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./changeInterestGroup.do" method="get">
		四十四、修改兴趣小组：<br>
		学号:<input type="text" name="Account" /> <br> 
		兴趣小组id:<input type="text" name="InterestGroup" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./getUpdateInfo.do" method="get">
		四十五、检测更新：<br>
		<input type="submit" value="提交" /> <br> <br>
	</form>
	
	<form action="./changeUpdateInfo.do" method="get">
		四十六、修改更新内容：<br>
		更新描述:<input type="text" name="appDescribe" /> <br> 
		应用版本:<input type="text" name="appVersion" /> <br> 
		应用地址:<input type="text" name="appUrl" /> <br> 
		是否显示扫描云子:<input type="text" name="showScansSensor" /> <br> 
		<input type="submit" value="提交" /> <br> <br>
	</form>
</body>
</html>