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
		
		// FIXME 检索页面限制版本、限制用户ID(用户名)、增加有效期
		
		// 有效的版本号可以有多个，用于作为全局开关限制所有人
		// 若版本号有效：
			// 若公版时间未过期，则可用
			// 否则若私人时间未过期，则可用
			// 都则不可用
		
		// 否则所有人不可用
		
	}
	
}
