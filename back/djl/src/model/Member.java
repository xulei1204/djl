package model;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;
import com.xiaheng.utils.CodeBean;

/**
 * 用户表 model
 *
 * @version 1.0
 * @since 2017-5-16 14:48:27
 */
public class Member extends BaseModel<Member> {

	private static final long serialVersionUID = 1L;

	public static final Member dao = new Member();

	@Override
	public String tableName() {
		return "a2017_djl_member";
	}

	@Override
	public String tableKey() {
		return "U_id";
	}

	/**
	 * @方法名:未审核用户列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月14 7:16:41
	 * @修改:
	 */

	public Page<Record> getListDel(int page, int pageSize, JSONObject where) {

		return this.getPagingDel(page, pageSize, where);
	}

	/**
	 * @方法名:验证账户余额
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月21 10:47:50
	 * @修改:
	 */
	public CodeBean<Record> checkMoney(String U_id, String money) {

		CodeBean<Record> bean = new CodeBean<Record>();
		StringBuffer sql = new StringBuffer();
		sql.append("select money from ");
		sql.append(this.tableName());
		sql.append(" where U_id=?");
		sql.append(" and money>=?");
		ArrayList<String> list = new ArrayList<String>();
		list.add(U_id);
		list.add(money);
		Record result = Db.findFirst(sql.toString(), list.toArray());
		if (result == null) {
			bean.setSuccess(false);
			bean.setMessage("余额不足");
			return bean;
		}
		bean.setSuccess(true);
		bean.setMessage("余额充足");
		bean.setData(result);
		return bean;
	}
}
