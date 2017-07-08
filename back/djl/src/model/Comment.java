package model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 广告评论 model
 *
 * @version 1.0
 * @since 2017-6-14 14:27:54
 */
public class Comment extends BaseModel<Comment> {

	private static final long serialVersionUID = 1L;

	public static final Comment dao = new Comment();

	@Override
	public String tableName() {
		return "a2017_djl_comment";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:显示广告评论列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月29 3:10:17
	 * @修改:
	 */
	public Page<Record> getListByADID(String ADID, int pagesize, int page) {
		StringBuffer select = new StringBuffer();
		StringBuffer from = new StringBuffer();
		select.append("select comm.content,date_format(comm.addTime,'%Y-%c-%d %H:%i') as time,member.nickname,member.pic ");
		from.append("from " + this.tableName() + " as comm ");
		from.append("left join " + new Member().tableName() + " as member ");
		from.append("on comm.memberId=member.U_id ");
		from.append("where comm.ADID=? ");
		from.append("order by comm.addTime desc");

		return Db.paginate(page, pagesize, select.toString(), from.toString(),
				ADID);
	}
}
