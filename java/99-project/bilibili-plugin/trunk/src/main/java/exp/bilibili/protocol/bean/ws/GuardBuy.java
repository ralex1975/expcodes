package exp.bilibili.protocol.bean.ws;

import net.sf.json.JSONObject;
import exp.bilibili.protocol.envm.BiliCmd;
import exp.bilibili.protocol.envm.BiliCmdAtrbt;
import exp.libs.utils.format.JsonUtils;

/**
 * 
 * <PRE>
 * 
	(直播间内)新船员上船消息
	{
	  "cmd": "GUARD_BUY",
	  "data": {
	    "uid": 14356317,
	    "username": "\u8865\u5200\u706c\u5200",
	    "guard_level": 3,
	    "num": 1
	  },
	  "roomid": "390480"
	}
 * </PRE>
 * @version   1.0 2017-12-17
 * @author    EXP: <a href="http://www.exp-blog.com">www.exp-blog.com</a>
 * @since     jdk版本：jdk1.6
 */
public class GuardBuy extends WelcomeGuard {

	private int num;
	
	public GuardBuy(JSONObject json) {
		super(json);
		this.cmd = BiliCmd.GUARD_BUY;
	}
	
	@Override
	protected void analyse(JSONObject json) {
		super.analyse(json);
		
		JSONObject data = JsonUtils.getObject(json, BiliCmdAtrbt.data); {
			this.num = JsonUtils.getInt(data, BiliCmdAtrbt.num, 0);
		}
	}

	public int getNum() {
		return num;
	}

}