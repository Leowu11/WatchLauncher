package com.fise.xiaoyu.DB.entity;

import android.text.TextUtils;
import android.util.Log;

import com.fise.xiaoyu.DB.sp.SystemConfigSp;
import com.fise.xiaoyu.config.DBConstant;
import com.fise.xiaoyu.imservice.entity.SearchElement;
import com.fise.xiaoyu.utils.pinyin.PinYin.PinYinElement;


/**
 *  群的基本信息 包含版本
 */
public class GroupInfoEntity {

	protected Long id;
	protected int type;
	protected int groupId;
	protected int version;
	protected int stats;

	public GroupInfoEntity() {

	}

	public GroupInfoEntity(Long id) {
		this.id = id;
	}

	public GroupInfoEntity(Long id, int groupId, int type, int version, int stats) {
		this.id = id;
		this.type = type;
		this.groupId = groupId;
		this.version = version;
		this.stats = stats;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStats() {
		return stats;
	}

	public void setStats(int stats) {
		this.stats = stats;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof GroupVersion))
			return false;

		GroupVersion entity = (GroupVersion) o;
		if (type != entity.type)
			return false;
		if (groupId != entity.groupId)
			return false;
		if (version != entity.version)
			return false;
		if (stats != entity.stats)
			return false;

		return true;
	}

}
