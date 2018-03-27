package exp.crawler.qq.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;

import exp.crawler.qq.bean.QQCookie;
import exp.libs.utils.verify.RegexUtils;
import exp.libs.warp.net.http.HttpClient;
import exp.libs.warp.net.http.HttpUtils;

/**
 * <PRE>
 * XHR工具类
 * </PRE>
 * <B>PROJECT：</B> bilibili-plugin
 * <B>SUPPORT：</B> EXP
 * @version   1.0 2018-03-23
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class XHRUtils {

	/** 私有化构造函数 */
	protected XHRUtils() {}
	
	/**
	 * 获取XHR请求头
	 * @param cookie
	 * @return
	 */
	public static Map<String, String> getHeader(QQCookie cookie) {
		Map<String, String> header = new HashMap<String, String>();
		header.put(HttpUtils.HEAD.KEY.ACCEPT, "image/webp,image/*,*/*;q=0.8");
		header.put(HttpUtils.HEAD.KEY.ACCEPT_ENCODING, "gzip, deflate, sdch");
		header.put(HttpUtils.HEAD.KEY.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en;q=0.6");
		header.put(HttpUtils.HEAD.KEY.CONNECTION, "keep-alive");
		header.put(HttpUtils.HEAD.KEY.COOKIE, cookie.toNVCookie());
		header.put(HttpUtils.HEAD.KEY.USER_AGENT, HttpUtils.HEAD.VAL.USER_AGENT);
		return header;
	}
	
	/**
	 * 从Http会话的响应报文中提取cookie信息
	 * @param client Http会话客户端
	 * @param cookie cookie对象容器
	 */
	public static void takeResponseCookies(HttpClient client, QQCookie cookie) {
		HttpMethod method = client.getHttpMethod();
		if(method != null) {
			Header[] rspHeaders = method.getResponseHeaders();
			for(Header rspHeader : rspHeaders) {
				if(HttpUtils.HEAD.KEY.SET_COOKIE.equals(rspHeader.getName())) {
					cookie.add(rspHeader.getValue());
				}
			}
		}
	}
	
	/**
	 * 从Http会话的请求报文中提取cookie信息.
	 * 	(已修改过commons-httpclient的源码, 重定向页面Respone的Set-Cookie已存储到Request的Cookie中)
	 * 
	 * @param client Http会话客户端
	 * @param cookie cookie对象容器
	 * @param filterRegex 值过滤正则
	 */
	public static void takeRequestCookies(HttpClient client, QQCookie cookie, String filterRegex) {
		HttpMethod method = client.getHttpMethod();
		if(method != null) {
			Header[] rqsHeaders = method.getRequestHeaders();
			for(Header rqsHeader : rqsHeaders) {
				if(HttpUtils.HEAD.KEY.COOKIE.equals(rqsHeader.getName()) && 
						RegexUtils.contains(rqsHeader.getValue(), filterRegex)) {
					cookie.add(rqsHeader.getValue());
				}
			}
		}
	}

	/**
	 * 从XHR响应报文中的回调函数提取JSON内容
	 * @param callback 回调函数字符串
	 * @return JSON
	 */
	public static String toJson(String callback) {
		return RegexUtils.findFirst(callback.replace("\\/", "/"), "_Callback\\(([\\s\\S]*)\\);$");
	}
	
	/**
	 * 从URL地址中提取主Host地址
	 * @param url
	 * @return
	 */
	public static String toHost(String url) {
		return RegexUtils.findFirst(url, "http://([^/]*)/");
	}
	
}
