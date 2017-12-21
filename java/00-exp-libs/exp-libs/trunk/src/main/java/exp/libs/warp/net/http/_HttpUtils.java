package exp.libs.warp.net.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exp.libs.envm.Charset;
import exp.libs.utils.encode.CharsetUtils;
import exp.libs.utils.other.StrUtils;

class _HttpUtils {

	/** 日志器 */
	protected final static Logger log = LoggerFactory.getLogger(HttpsUtils.class);
	
	/** 默认编码 */
	public final static String DEFAULT_CHARSET = Charset.UTF8;
	
	/** 连接超时, 默认1分钟 */
	public final static int CONN_TIMEOUT = 60000;

	/** 响应/读取超时 , 默认1分钟 */
	public final static int CALL_TIMEOUT = 60000;
	
	/** URL协议类型:HTTPS */
	private final static String HTTPS = "https";
	
	/** SSL实例名称 */
	private final static String TLS = "tls";
	
	/** GET请求方法名 */
	public final static String METHOD_GET = "GET";
	
	/** POST请求方法名 */
	public final static String METHOD_POST = "POST";
	
	/**
	 * <PRE>
	 * HTTP请求头参数枚举, 请求头样例:
	 * 
	 * Accept:application/json, text/javascript;
	 * Accept-Encoding:gzip, deflate, br
	 * Accept-Language:zh-CN,zh;q=0.8,en;q=0.6
	 * Connection:keep-alive
	 * Content-Length:68
	 * Content-Type:application/x-www-form-urlencoded; charset=UTF-8
	 * Cookie:l=v; sid=891ab0o9; fts=1497517922; buvid3=0EE9E160-55FF-4EA6-9B8A-D37EAB81B76927368infoc; UM_distinctid=15d88e8e6da42-01bb282a267083-414a0229-100200-15d88e8e6db5a; pgv_pvi=958242816; rpdid=olwiqlmmxldoswoipwsxw; LIVE_BUVID=ebf2cce7237945227c579bec3e986459; LIVE_BUVID__ckMd5=08a5ee5cfdbdd99f; biliMzIsnew=1; biliMzTs=0; im_seqno_1650868=9548; DedeUserID=1650868; DedeUserID__ckMd5=686caa22740f2663; SESSDATA=e6e4328c%2C1515920104%2C162b21cd; bili_jct=9db6a9c26d414e848430dac8f7c2ea9b; finger=81df3ec0; Hm_lvt_8a6d461cf92ec46bd14513876885e489=1513755792; _dfcaptcha=f63bb4f803bb08dc6b7427b0afee793e; Hm_lvt_8a6e55dbd2870f0f5bc9194cddf32a02=1513749373,1513755780,1513756902,1513817560; Hm_lpvt_8a6e55dbd2870f0f5bc9194cddf32a02=1513820331
	 * Host:api.live.bilibili.com
	 * Origin:http://live.bilibili.com
	 * Referer:http://live.bilibili.com/269706
	 * User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36
	 * </PRE>
	 */
	public class HEAD {
		
		public class KEY {
			public final static String ACCEPT = "Accept";
			public final static String ACCEPT_ENCODING = "Accept-Encoding";
			public final static String ACCEPT_LANGUAGE = "Accept-Language";
			public final static String CONNECTION = "Connection";
			public final static String CONTENT_TYPE = "Content-Type";
			public final static String COOKIE = "Cookie";
			public final static String HOST = "Host";
			public final static String ORIGIN = "Origin";
			public final static String REFERER = "Referer";
			public final static String USER_AGENT = "User-Agent";
		}
		
		public class VAL {
			
			/** 请求POST的数据是xml */
			public final static String CONTENT_TYPE_XML = 
					"application/x-javascript text/xml; charset=";
			
			/** 请求POST的数据是json */
			public final static String CONTENT_TYPE_JSON = 
					"application/x-javascript; charset=";
			
			/** 请求POST的数据是表单 */
			public final static String CONTENT_TYPE_FORM = 
					"application/x-www-form-urlencoded; charset=";
		}
		
	}
	
	/** 私有化构造函数 */
	protected _HttpUtils() {}
	
	/**
	 * 测试URL是否有效
	 * @param url url路径
	 * @return true:有效; false:无效
	 */
	public static boolean testValid(final String url) {
		boolean isValid = false;
		try {
			HttpURLConnection conn = 
					(HttpURLConnection) new URL(url).openConnection();
			isValid = isResponseOK(conn);
		} catch (Exception e) {}
		return isValid;
	}
	
	/**
	 * 判断HTTP请求是否响应成功
	 * @param conn
	 * @return
	 */
	protected static boolean isResponseOK(HttpURLConnection conn) {
		boolean isOk = false;
		try {
			isOk = (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {}
		return isOk;
	}
	
	/**
	 * 对URL进行编码
	 * @param url url路径
	 * @return 编码后URL
	 */
	public static String encodeURL(final String url) {
		return encodeURL(url, DEFAULT_CHARSET);
	}
	
	/**
	 * 对URL进行编码
	 * @param url url路径
	 * @param charset 编码字符集
	 * @return 编码后URL
	 */
	public static String encodeURL(final String url, final String charset) {
		String encodeURL = "";
		try {
			encodeURL = URLEncoder.encode(url, charset);
			
		} catch (Exception e) {
			log.error("对URL以 [{}] 编码失败: {}", charset, url, e);
		}
		return encodeURL;
	}

	/**
	 * 对URL进行解码
	 * @param url url路径
	 * @return 解码后URL
	 */
	public static String decodeURL(final String url) {
		return decodeURL(url, DEFAULT_CHARSET);
	}
	
	/**
	 * 对URL进行解码
	 * @param url url路径
	 * @param charset 编码字符集
	 * @return 解码后URL
	 */
	public static String decodeURL(final String url, final String charset) {
		String decodeURL = "";
		try {
			decodeURL = URLDecoder.decode(url, charset);
			
		} catch (Exception e) {
			log.error("对URL以 [{}] 解码失败: {}", charset, url, e);
		}
		return decodeURL;
	}
	
	/**
	 * 把请求参数转换成URL的KV串形式并进行编码
	 * @param requestParams 请求参数集
	 * @return ?&key1=val1&key2=val2&key3=val3
	 */
	public static String encodeRequests(Map<String, String> requestParams) {
		return encodeRequests(requestParams, DEFAULT_CHARSET);
	}
	
	/**
	 * 把请求参数转换成URL的KV串形式并进行编码
	 * @param requestParams 请求参数集
	 * @param charset 参数字符编码
	 * @return ?&key1=val1&key2=val2&key3=val3
	 */
	public static String encodeRequests(
			Map<String, String> requestParams, final String charset) {
		if(requestParams == null || requestParams.isEmpty() || 
				CharsetUtils.isInvalid(charset)) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder("?");
		Iterator<String> keyIts = requestParams.keySet().iterator();
		while(keyIts.hasNext()) {
			String key = keyIts.next();
			String val = requestParams.get(key);
			try {
				val = URLEncoder.encode(val, charset);
			} catch (Exception e) {
				val = "";
			}
			
			if(StrUtils.isNotEmpty(key, val)) {
				sb.append("&").append(key).append("=").append(val);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 构造HTTP/HTTPS连接
	 * @param url 目标地址
	 * @param method 请求方法：GET/POST
	 * @param headParams 请求头参数
	 * @param connTimeout 连接超时(ms)
	 * @param readTimeout 读取超时(ms)
	 * @return HTTP连接(失败返回null)
	 * @throws Exception
	 */
	public static HttpURLConnection createHttpConn(URL url, String method, 
			Map<String, String> headParams, int connTimeout, int readTimeout) 
					throws Exception {
		HttpURLConnection conn = null;
		if (url == null) {
			return conn;
		}
		
		// HTTPS连接
		if(HTTPS.equals(url.getProtocol())) {
			SSLContext ssl = SSLContext.getInstance(TLS);
			ssl.init(new KeyManager[0], new TrustManager[] { new _X509TrustManager() }, new SecureRandom());
			HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
			httpsConn.setSSLSocketFactory(ssl.getSocketFactory());
			httpsConn.setHostnameVerifier(new HostnameVerifier() {

				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			conn = httpsConn;
			
		// HTTP连接
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}

		// 设置固有请求参数
		conn.setRequestMethod(method);
		conn.setConnectTimeout(connTimeout);
		conn.setReadTimeout(readTimeout);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		// 设置自定义请求头参数
		Iterator<String> keyIts = headParams.keySet().iterator();
		while(keyIts.hasNext()) {
			String key = keyIts.next();
			String val = headParams.get(key);
			if(StrUtils.isNotEmpty(key, val)) {
				conn.setRequestProperty(key, val);
			}
		}
		return conn;
	}

	/**
	 * 关闭HTTP/HTTPS连接
	 * @param httpClient
	 */
	public static void close(HttpURLConnection conn) {
		if(conn != null) {
			conn.disconnect();
		}
	}
	
	/**
	 * 创建HttpClient会话
	 * @return
	 */
	public static HttpClient createHttpClient() {
		return createHttpClient(CONN_TIMEOUT, CALL_TIMEOUT);
	}

	/**
	 * 创建HttpClient会话
	 * @param connTimeOut
	 * @param callTimeout
	 * @return
	 */
	public static HttpClient createHttpClient(int connTimeOut, int callTimeout) {
		HttpConnectionManagerParams managerParams = new HttpConnectionManagerParams();
		managerParams.setConnectionTimeout(connTimeOut);
		managerParams.setDefaultMaxConnectionsPerHost(2);
		managerParams.setSoTimeout(callTimeout);
		
		HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
		httpConnectionManager.setParams(managerParams);

		HttpClient httpClient = new HttpClient(new HttpClientParams());
		httpClient.setHttpConnectionManager(httpConnectionManager);
		return httpClient;
	}
	
	/**
	 * 关闭HttpClient会话
	 * @param httpClient
	 */
	public static void close(HttpClient httpClient) {
		if(httpClient != null) {
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
		}
	}
	
}
