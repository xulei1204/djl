package model;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.xiaheng.core.jfinal.BaseModel;
import com.xiaheng.utils.CodeBean;

/**
 * 广告 model
 *
 * @version 1.0
 * @since 2017-6-14 14:50:36
 */
public class Ad extends BaseModel<Ad> {

	private static final long serialVersionUID = 1L;

	public static final Ad dao = new Ad();

	@Override
	public String tableName() {
		return "a2017_djl_ad";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * 保存
	 * 
	 * @author Chuck Don
	 * @since 2016年12月20日
	 * @param model
	 * @return
	 */
	public CodeBean<JSONObject> saveModel(JSONObject where) {
		Ad model = new Ad();
		Table table = TableMapping.me().getTable(this.getClass());
		for (String columnLabel : where.keySet()) {
			if (table.hasColumnLabel(columnLabel)) {// 有没有找到这一列
				model.set(columnLabel, where.get(columnLabel));
			}
		}
		String uuid = getUUID();

		model.set(this.tableKey(), uuid);
		model.set("addTime", new Date());

		CodeBean<JSONObject> bean = new CodeBean<JSONObject>();
		bean.setSuccess(model.save());
		if (bean.isSuccess()) {
			bean.setMessage("发布成功");
		} else {
			bean.setMessage("发布失败");

		}
		JSONObject obj = new JSONObject();
		obj.put("id", uuid);
		if ("1".equals(model.getStr("classifyId"))) {
			// 标识公益广告，不需要设置红包
			obj.put("is", false);
		} else {
			obj.put("is", true);
		}
		bean.setData(obj);
		return bean;
	}

	/**
	 * @方法名:显示详情
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月28 9:34:22
	 * @修改:
	 */
	public Record showDetail(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ad.title,ad.video,ad.cover,ad.content,ad.id,ad.addTime,date_format(ad.addTime,'%Y-%m-%d') as time,ad.commentNum,ad.lookNum,ad.zanNum,member.nickname,member.pic ");
		sql.append(" from " + this.tableName() + " as ad ");
		sql.append(" left join " + new Member().tableName() + " as member");
		sql.append(" on ad.memberId=member.U_id");
		sql.append(" where ad.id=?");

		return Db.findFirst(sql.toString(), id);
	}

	/**
	 * @方法名:下三篇
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月29 10:13:43
	 * @修改:
	 */
	public List<Record> getNext(Record re) {
		StringBuffer sql = new StringBuffer();
		sql.append("select LEFT(title,15) as title,id ");
		sql.append(" from " + this.tableName());
		sql.append(" where isDel='0' and addTime>?");
		return Db.find(sql.toString(), re.get("addTime"));

	}
}
