package exp.fpf;

import exp.libs.utils.other.LogUtils;
import exp.libs.warp.ver.VersionMgr;

/**
 * <PRE>
 * 版本类.
 *  版本信息记录在 ./src/main/resources/.verinfo 中, 请勿删除该文件.
 * </PRE>
 * <B>PROJECT：</B> file-port-forwarding
 * <B>SUPPORT：</B> EXP
 * @version   1.0 2017-07-31
 * @author    EXP: <a href="http://www.exp-blog.com">www.exp-blog.com</a>
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
	}
	
}
