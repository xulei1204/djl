package model;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.xiaheng.core.jfinal.BaseModel;
import com.xiaheng.utils.CodeBean;

/**
 * model
 *
 * @version 1.0
 * @since 2017-6-16 13:44:41
 */
public class Feedback extends BaseModel<Feedback> {

	private static final long serialVersionUID = 1L;

	public static final Feedback dao = new Feedback();

	@Override
	public String tableName() {
		return "a2017_djl_feedback";
	}

	@Override
	public String tableKey() {
		return "id";
	}

	/**
	 * @方法名:保存
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月22 9:40:44
	 * @修改:
	 */
	public CodeBean<String> save(JSONObject where) {
		Feedback model = new Feedback();
		Table table = TableMapping.me().getTable(this.getClass());
		for (String columnLabel : where.keySet()) {
			if (table.hasColumnLabel(columnLabel)) {// 有没有找到这一列
				model.set(columnLabel, where.get(columnLabel));
			}
		}
		String uuid = getUUID();
		model.set(model.tableKey(), uuid);
		model.set("addTime", new Date());
		model.set("isDel", "0");
		boolean success = model.save();
		CodeBean<String> bean = new CodeBean<String>();
		if (success) {
			bean.setMessage("提交成功");
			bean.setData(uuid);
			bean.setSuccess(true);
		} else {
			bean.setSuccess(false);
			bean.setMessage("保存失败");
		}

		return bean;
	}

}
