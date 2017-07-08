package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.ProjectUtil;

/**
 * 红包记录 model
 *
 * @version 1.0
 * @since 2017-6-14 15:38:30
 */
public class RedpacketRecord extends BaseModel<RedpacketRecord> {

	private static final long serialVersionUID = 1L;

	public static final RedpacketRecord dao = new RedpacketRecord();

	@Override
	public String tableName() {
		return "a2017_djl_redpacket_record";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:根据用户id获取红包领取记录
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月3 10:47:13
	 * @修改:
	 */
	public Page<Record> getPage(String u, int pageSize, int page) {

		StringBuffer select = new StringBuffer();
		select.append("select re.money,date_format(re.addTime,'%Y-%M_%d') as time,LEFT(ad.title,5) as title ");
		StringBuffer from = new StringBuffer();
		from.append("from " + this.tableName() + " as re ");
		from.append(" left join " + new Ad().tableName()
				+ " as ad on re.ADID=ad.id ");
		from.append(" where memberId=?");
		return Db.paginate(page, pageSize, select.toString(), from.toString(),
				u);
	}

	/**
	 * @方法名:查询用户上次领取红包时间，是否满足领取红包资格
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月30 1:47:33
	 * @修改:
	 */
	public CodeBean<String> checkRecord(String u, String ADID) {

		CodeBean<String> bean = new CodeBean<String>();
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.HOUR, -1);
		Date before = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) as count from " + this.tableName());
		sql.append(" where memberId=? and ADID=? and addTime between ? and ?");
		ArrayList<String> list = new ArrayList<String>();
		list.add(u);
		list.add(ADID);
		list.add(sdf.format(before));
		list.add(sdf.format(now));
		long count = 0;

		count = Db.queryLong(sql.toString(), list.toArray());

		if (count >= 2) {
			bean.setMessage("请等会儿再来领取吧！");
			bean.setSuccess(false);
			bean.setCode("204");
			return bean;
		}
		bean.setSuccess(true);
		return bean;
	}

	/**
	 * @方法名:用户是否满足本月抽奖条件（上个月至少领取过一次红包）
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 4:44:48
	 * @修改:
	 */
	public boolean isMeetForPrize(String uid) {

		// 上个月
		String lastMonth = ProjectUtil.lastMonth();

		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from " + this.tableName());
		sql.append(" where memberId=? and addTime like ?");
		ArrayList<String> list = new ArrayList<String>();
		list.add(uid);
		list.add("%" + lastMonth + "%");
		long count = Db.queryLong(sql.toString(), list.toArray());
		return count > 0 ? true : false;
	}
}
