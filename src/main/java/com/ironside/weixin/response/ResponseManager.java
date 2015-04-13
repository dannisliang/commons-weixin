package com.ironside.weixin.response;

import java.util.Date;
import java.util.Properties;

import org.springframework.util.Assert;

import com.ironside.weixin.request.entity.AbstractBaseEntity;
import com.ironside.weixin.request.entity.TextEntity;
import com.ironside.weixin.response.entity.AbstractBaseResponse;
import com.ironside.weixin.response.entity.TextResponse;

/**
 * 回复实体管理
 * @author ZXJ
 * @sine 1.0 at 2015年4月9日
 */
public class ResponseManager {
	
	private String xmlFileName;
	private XmlParse xmlParse;
	
	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFile) {
		this.xmlFileName = xmlFile;
	}
	
	public XmlParse getXmlParse() {
		return xmlParse;
	}

	public void setXmlParse(XmlParse xmlParse) {
		this.xmlParse = xmlParse;
	}	

	/** 取得文本回复实体 */
	public 	TextResponse getTextResponse() {
		// 取得xml字符串
		String str = xmlParse.getXmlString("text");
		// 取得xml名字和值对应
		Properties properties = xmlParse.parse(str);
		// 根据名字和值对应生成对象
		return  doTextResponse(properties);
	}
	
	/**
	 * 基础解析
	 * @param properties 解析后的properties
	 * @param entity 用于基础解析的实体
	 */
	private void doBaseAnalyze(Properties properties, AbstractBaseResponse entity) {
		String toUserName = properties.getProperty(TextEntity.TO_USER_NAME);
		Assert.hasText(toUserName);
		String fromUserName = properties.getProperty(TextEntity.FORM_USER_NAME);
		Assert.hasText(fromUserName);
		String createTimeStr = properties.getProperty(TextEntity.CREATE_TIME);
		Assert.hasText(createTimeStr);
		// 将时间整形转换为对象
		Date createTime = new Date(Long.parseLong(createTimeStr));
		Assert.notNull(createTime);
		
		entity.setToUserName(toUserName);
		entity.setFromUserName(fromUserName);
		entity.setCreateTime(createTime);
	}	

	/**
	 * 根据名字和值对应生成对象
	 * @param properties xml名字和值对应
	 * @return 文本回复实体
	 */
	private TextResponse doTextResponse(Properties properties) {
		/*
		 <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[fromUser]]></FromUserName>
		 <CreateTime>12345678</CreateTime>
		 <MsgType><![CDATA[text]]></MsgType>
		 <Content><![CDATA[你好]]></Content>
	  	 </xml>
		 */
		TextResponse entity = new TextResponse();
		doBaseAnalyze(properties, entity);
		String content = properties.getProperty(TextEntity.CONTENT);
		Assert.hasText(content);
		
		entity.setContent(content);
		return entity;
	}

	/** 取得图片回复实体 */
	// public 	ImageResponse getImageResponse();
	
	/** 取得语音回复实体 */
	// public 	VoiceResponse getVoiceResponse();
	
	/** 取得视频回复实体 */
	// public 	VideoResponse getVideoResponse();
	
	/** 取得音乐回复实体 */
	// public 	MusicResponse getMusicResponse();
	
	/** 取得图文回复实体 */
	// public 	NewsResponse getNewsResponse();

}
