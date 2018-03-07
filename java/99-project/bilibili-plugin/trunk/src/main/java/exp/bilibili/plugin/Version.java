package exp.bilibili.plugin;

import exp.libs.utils.other.LogUtils;
import exp.libs.warp.ver.VersionMgr;

/**
 * <PRE>
 * 版本类.
 *  版本信息记录在 ./src/main/resources/.verinfo 中, 请勿删除该文件.
 * </PRE>
 * <B>PROJECT：</B> exp-libs
 * <B>SUPPORT：</B> EXP
 * @version   1.0 2017-12-17
 * @author    EXP: 272629724@qq.com
 * @since     jdk版本：jdk1.6
 */
public class Version {

	/**
	 * 版本管理入口, 任何项目均不需修改此代码.
	 * @param args 入口参数（win下默认为-m, linux下强制为-p）
	 * 		[-p] 打印最后的版本信息（DOS界面）
	 * 		[-m] 版本管理（UI界面）
	 */
	public static void main(String[] args) {
		LogUtils.loadLogBackConfig();
		VersionMgr.exec(args);
		
		// TODO
		// WS- 注释  ,OCR  RSA代码整理
		// 模拟APP观看时长	(如何获取app的cookie ?? 安卓模拟器抓包？？？  手机节奏风暴不需要验证码)
		// 尝试抓取手机/模拟器的额节奏风暴抽奖请求，替换为PC的cookie，或者不会触发验证码
		// 手工打开页面总会获得某些当天礼物，不知触发原因
		// 自动投喂时若超过当前勋章亲密度则不投喂(若无佩戴勋章则不遵循此规则)
	}
	
}
