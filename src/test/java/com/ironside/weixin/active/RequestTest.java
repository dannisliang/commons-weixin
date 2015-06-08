package com.ironside.weixin.active;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ironside.weixin.WeixinException;
import com.ironside.weixin.active.entity.AccessToken;
import com.ironside.weixin.active.entity.IpAddresses;
import com.ironside.weixin.active.entity.UserInfo;
import com.ironside.weixin.active.entity.UserList;

public class RequestTest {
	
	// private final String appid = "wxdce9a330da720609";
	private final String appid = "wxdce9a330da720609a";
	private final String secret = "a9c33e27b711b683514f8b6404776967";
	private final String openid = "oC2dusx6l3O4EM5fpMpuAADJrVxM";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAccessToken() {
		AccessToken accessToken = null;
		try {
			accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
		} catch (WeixinException e) {
			e.printStackTrace();
		}
		assertTrue(accessToken.getExpiresIn()>7000 && accessToken.getExpiresIn()<8000);
		assertTrue(accessToken.isOver()==false);
		assertFalse(accessToken.getAccessTime()==0);
	}

	@Test
	public void testGetIpAddress() {
		AccessToken accessToken = null;
		IpAddresses ipAddress = null;
		try {
			accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
			ipAddress = ActiveRequest.getInstance().getIpAddress(accessToken);
		} catch (WeixinException e) {
			e.printStackTrace();
		}
		assertTrue(ipAddress.getIpList().size()>0);
	}
	
	@Test 
	public void testGetUserInfo() {
		AccessToken accessToken = null;
		UserInfo userInfo = null;
		try {
			accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
			userInfo = UserRequest.getInstance().getUserInfo(accessToken, openid);
		} catch (WeixinException e) {
			e.printStackTrace();
		}
		assertEquals(userInfo.getOpenid(), openid);
		assertEquals(userInfo.getNickName(), "雪庭");
		assertEquals(userInfo.getCity(), "兰州市");
		assertEquals(userInfo.getProvince(), "甘肃");
	}
	
	@Test
	public void testGetUserList() {
		AccessToken accessToken = null;
		UserList userList = null;
		try {
			accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
			userList = UserRequest.getInstance().getUserList(accessToken, null);
		} catch (WeixinException e) {
			e.printStackTrace();
		}
		Assert.notNull(userList);
		UserList.UserListData data = userList.getData();
		Assert.notNull(data);
	}
	
	@Test
	public void testSetUserRemark() {
		AccessToken accessToken = null;
		try {
			accessToken = ActiveRequest.getInstance().getAccessToken(appid, secret);
			UserRequest.getInstance().setUserRemark(accessToken, "{\"openid\":\"oC2dusx6l3O4EM5fpMpuAADJrVxM\",\"remark\":\"机器\"}");
		} catch (WeixinException e) {
			e.printStackTrace();
		}
	}
}