package com.fise.xiaoyu.DB.entity;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here

import android.text.TextUtils;
import android.util.Log;

import com.fise.xiaoyu.DB.sp.SystemConfigSp;
import com.fise.xiaoyu.config.DBConstant;
import com.fise.xiaoyu.imservice.entity.SearchElement;
import com.fise.xiaoyu.utils.pinyin.PinYin.PinYinElement;

// KEEP INCLUDES END


/**
 * Entity mapped to table UserInfo. 
 * 用户基本信息
 */
public class UserEntity extends PeerEntity {

	private int gender;
	/** Not-null value. */
	private String pinyinName;
	/** Not-null value. */
	private String realName;
	/** Not-null value. */
	private String phone;
	/** Not-null value. */
	private String email;
	private int departmentId;
	private int status;
	private int is_friend;

	private double latitude = 0.0;
	private double longitude = 0.0;
	private int battery = 0;
	private int signal = 0;
	private boolean loseMonitor = true;
	private int auth;
	private int onLine = 0;
	private int useType; 

	private String country; // 国家
	private String province;// 省
	private String city;// 市
	private String sign_info; // 签名
	private String comment = ""; // 备注
	private String groupNick; // 群昵称

	private int Friend_need_auth;
	private int Login_safe_switch;
	private int Search_allow;
	private int locationType = DBConstant.LOCATION_GPS;  //定位方式

	private int weight  = 0;  //体重
	private int height   = 0;  //身高
	private String birthday    = "";  //生日

//	
	
	// KEEP FIELDS - put your custom fields here
	private PinYinElement pinyinElement = new PinYinElement();
	private SearchElement searchElement = new SearchElement();

	// KEEP FIELDS END

	public UserEntity() {

	}

	public UserEntity(Long id) {
		this.id = id;
	}

	public UserEntity(Long id, int peerId, int gender, String mainName,
			String pinyinName, String realName, String avatar, String phone,
			String email, int departmentId, int status, int created,
			int updated, int isFriends, double longitude, double latitude,
			int battery, int signal, int auth, int onLine, int useType,
			String country, String province, String city, String sign_info,
			String comment, int Friend_need_auth, int Login_safe_switch,
			int Search_allow, String groupNick,int locationType,int weight ,int height,String birthday) { //
		this.id = id;
		this.peerId = peerId;
		this.gender = gender;
		this.mainName = mainName;
		this.pinyinName = pinyinName;
		this.realName = realName;
		this.avatar = avatar;
		this.phone = phone;
		this.email = email;
		this.departmentId = departmentId;
		this.status = status;
		this.created = created;
		this.updated = updated;
		this.is_friend = isFriends;
		this.longitude = longitude;
		this.latitude = latitude;
		this.battery = battery;
		this.signal = signal;
		this.auth = auth;
		this.onLine = onLine;
		this.useType = useType;

		this.country = country;
		this.province = province;
		this.city = city;
		this.sign_info = sign_info;
		this.comment = comment;

		this.Friend_need_auth = Friend_need_auth;
		this.Login_safe_switch = Login_safe_switch;
		this.Search_allow = Search_allow;
		this.groupNick = groupNick;


 		this.locationType = locationType;

		this.weight = weight;
		this.height = height;
		this.birthday = birthday;

	}

	public int getFriendNeedAuth() {
		return this.Friend_need_auth;
	}

	public void setFriendNeedAuth(int Friend_need_auth) {
		this.Friend_need_auth = Friend_need_auth;
	}

	public int getLoginSafeSwitch() {
		return this.Login_safe_switch;
	}

	public void setLoginSafeSwitch(int Login_safe_switch) {
		this.Login_safe_switch = Login_safe_switch;
	}

	public int getSearchAllow() {
		return this.Search_allow;
	}

	public void setSearchAllow(int Search_allow) {
		this.Search_allow = Search_allow;
	}

	public String getGroupNick() {
		return this.groupNick;
	}

	public void setGroupNick(String groupNick) {
		this.groupNick = groupNick;
	}

	public int getAuth() {
		return this.auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public int getOnLine() {
		return this.onLine;
	}

	public void setOnLine(int onLine) {
		this.onLine = onLine;
	}

	public int getUserType() {
		return this.useType;
	}

	public void setUserType(int useType) {
		this.useType = useType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLoseMonitor(boolean loseMonitor) {
		this.loseMonitor = loseMonitor;
	}

	public boolean getLoseMonitor() {
		return loseMonitor;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public int getLocationType() {
		return locationType;
	}

	public void setLocationType(int locationType) {
		this.locationType = locationType;
	}


	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public int getPeerId() {
		return peerId;
	}

	public void setPeerId(int peerId) {
		this.peerId = peerId;
	}

	public void setFriend(int isFriend) {
		this.is_friend = isFriend;
	}

	public int getIsFriend() {
		return is_friend;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	/** Not-null value. */
	public String getMainName() {
		return mainName;
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	/** Not-null value. */
	public String getPinyinName() {
		return pinyinName;
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setPinyinName(String pinyinName) {
		this.pinyinName = pinyinName;
	}

	/** Not-null value. */
	public String getRealName() {
		return realName;
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/** Not-null value. */
	public String getAvatar() {
		return SystemConfigSp.instance().getStrConfig(
				SystemConfigSp.SysCfgDimension.MSFSSERVER)
				+ avatar;
	}

	/** Not-null value. */
	public String getUserAvatar() {
		return avatar;
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/** Not-null value. */
	public String getPhone() {
		return phone;
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** Not-null value. */
	public String getEmail() {
		return email;
	}

	/**
	 * Not-null value; ensure this value is available before it is saved to the
	 * database.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(int created) {
		this.created = created;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	// KEEP METHODS - put your custom methods here

	// 国家
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	// 省份
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	// 市区
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// 签名
	public String getSign_info() {
		return sign_info;
	}

	public void setSign_info(String sign_info) {
		this.sign_info = sign_info;
	}

	// 备注
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



	//生日
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	//身高
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	//体重
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}


//	//  免打扰
//	public int getMuteNotification() {
//		return muteNotification;
//	}
//
//	public void setMuteNotification(int muteNotification) {
//		this.muteNotification = muteNotification;
//	}
//	
//	
//	//置顶
//	public int getStickyOnTop() {
//		return stickyOnTop;
//	}
//
//	public void setStickyOnTop(int stickyOnTop) {
//		this.stickyOnTop = stickyOnTop;
//	}


	@Override
	public String toString() {
		return "UserEntity{" + "id=" + id + ", peerId=" + peerId + ", gender="
				+ gender + ", mainName='" + mainName + '\'' + ", pinyinName='"
				+ pinyinName + '\'' + ", realName='" + realName + '\''
				+ ", avatar='" + avatar + '\'' + ", phone='" + phone + '\''
				+ ", email='" + email + '\'' + ", departmentId=" + departmentId
				+ ", status=" + status + ", created=" + created + ", updated="
				+ updated + ", pinyinElement=" + pinyinElement
				+ ", searchElement=" + searchElement + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserEntity))
			return false;

		UserEntity entity = (UserEntity) o;

		if (departmentId != entity.departmentId)
			return false;
		if (gender != entity.gender)
			return false;
		if (peerId != entity.peerId)
			return false;
		if (status != entity.status)
			return false;
		if (avatar != null ? !avatar.equals(entity.avatar)
				: entity.avatar != null)
			return false;
		if (email != null ? !email.equals(entity.email) : entity.email != null)
			return false;
		if (mainName != null ? !mainName.equals(entity.mainName)
				: entity.mainName != null)
			return false;
		if (phone != null ? !phone.equals(entity.phone) : entity.phone != null)
			return false;
		if (pinyinName != null ? !pinyinName.equals(entity.pinyinName)
				: entity.pinyinName != null)
			return false;
		if (realName != null ? !realName.equals(entity.realName)
				: entity.realName != null)
			return false;

		if (latitude != entity.latitude)
			return false;
		if (longitude != entity.longitude)
			return false;
		if (battery != entity.battery)
			return false;
		if (signal != entity.signal)
			return false;
		if (auth != entity.auth)
			return false;

		if (country != null ? !country.equals(entity.country)
				: entity.country != null)
			return false;
		if (province != null ? !province.equals(entity.province)
				: entity.province != null)
			return false;
		if (city != null ? !city.equals(entity.city) : entity.city != null)
			return false;
		if (sign_info != null ? !sign_info.equals(entity.sign_info)
				: entity.sign_info != null)
			return false;
		if (comment != null ? !comment.equals(entity.comment)
				: entity.comment != null)
			return false;
		if (groupNick != null ? !groupNick.equals(entity.groupNick)
				: entity.groupNick != null)
			return false;

		
//		if (muteNotification != entity.muteNotification)
//			return false;
//		
//		if (stickyOnTop != entity.stickyOnTop)
//			return false;
		
		return true;
	}


	@Override
	public int hashCode() {
		int result = peerId;
		result = 31 * result + gender;
		result = 31 * result + (mainName != null ? mainName.hashCode() : 0);
		result = 31 * result + (pinyinName != null ? pinyinName.hashCode() : 0);
		result = 31 * result + (realName != null ? realName.hashCode() : 0);
		result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + departmentId;
		result = 31 * result + status;
		return result;
	}

	public PinYinElement getPinyinElement() {
		return pinyinElement;
	}

	public SearchElement getSearchElement() {
		return searchElement;
	}

	public String getSectionName() {
		if (TextUtils.isEmpty(pinyinElement.pinyin)) {
			return "";
		}
		return pinyinElement.pinyin.substring(0, 1);
	}

	@Override
	public int getType() {
		return DBConstant.SESSION_TYPE_SINGLE;
	}

	// KEEP METHODS END

}