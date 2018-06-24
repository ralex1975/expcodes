package exp.bilibili.protocol.bean.other;

import exp.bilibili.plugin.envm.LotteryType;
import exp.libs.utils.other.StrUtils;

/**
 * <PRE>
 * 抽奖房间
 * </PRE>
 * <B>PROJECT：</B> bilibili-plugin
 * <B>SUPPORT：</B> EXP
 * @version   1.0 2017-12-17
 * @author    EXP: <a href="http://www.exp-blog.com">www.exp-blog.com</a>
 * @since     jdk版本：jdk1.6
 */
public class LotteryRoom {

	/** 抽奖房间号 */
	private int roomId;
	
	/** 抽奖编号 */
	private String raffleId;
	
	/** 抽奖开始时间 */
	private long startTime;
	
	/** 抽奖类型 */
	private LotteryType type;
	
	public LotteryRoom(int roomId, LotteryType type) {
		this(roomId, "", type);
	}
	
	public LotteryRoom(int roomId, String raffleId, LotteryType type) {
		this.roomId = roomId;
		this.raffleId = (StrUtils.isEmpty(raffleId) ? "" : raffleId);
		this.startTime = System.currentTimeMillis();
		this.type = (type == null ? LotteryType.ENGERY : type);
	}

	public int getRoomId() {
		return roomId;
	}

	public String getRaffleId() {
		return raffleId;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public LotteryType TYPE() {
		return type;
	}

}
