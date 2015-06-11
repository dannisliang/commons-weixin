package com.ironside.weixin.active.entity;

import java.util.List;

import org.springframework.util.Assert;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 一级菜单类
 * @author 雪庭
 * @sine 1.0 at 2015年6月11日
 */
@XStreamAlias("button")
public class Button {

	// 菜单的响应动作类型
	private String type;
	// 菜单标题，不超过16个字节，子菜单不超过40个字节 
	private String name;
	// 菜单KEY值，用于消息接口推送，不超过128字节
	private String key;
	// 网页链接，用户点击菜单可打开链接，不超过256字节 
	private String url;
	// 调用新增永久素材接口返回的合法media_id
	@XStreamAlias("media_id")
	private String mediaId;
	// 二级菜单
	@XStreamAlias("sub_button")
	private List<Button> subButtonList;
	
	/**
	 * 添加二级菜单
	 * @param subButton 二级菜单
	 */
	public void addSubButton(Button subButton) {
		Assert.isTrue(subButton.getButtonList()==null);
		if (this.subButtonList.size()>=5) {
			return;
		}
		this.type = null;
		this.key = null;
		this.url = null;
		this.mediaId = null;
		this.subButtonList.add(subButton);
	}
		
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public List<Button> getButtonList() {
		return subButtonList;
	}

	public void setButtonList(List<Button> buttonList) {
		this.subButtonList = buttonList;
	}
	
	/** 点击推事件 */
	public static String CLICK = "click";
	/** 跳转URL */
	public static String VIEW = "view";
	/** 扫码推事件 */
	public static String SCANCODE_PUSH = "scancode_push";
	/** 扫码推事件且弹出“消息接收中”提示框 */
	public static String SCANCODE_WAITMSG = "scancode_waitmsg";
	/** 弹出系统拍照发图 */
	public static String PIC_SYSPHOTO = "pic_sysphoto";
	/** 弹出拍照或者相册发图 */
	public static String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	/** 弹出微信相册发图器 */
	public static String PIC_WEIXIN = "pic_weixin";
	/** 弹出地理位置选择器 */
	public static String LOCATION_SELECT = "location_select";
	/** 下发消息（除文本消息） */
	public static String MEDIA_ID = "media_id";
	/** 跳转图文消息URL */
	public static String VIEW_LIMITED= "view_limited";



}