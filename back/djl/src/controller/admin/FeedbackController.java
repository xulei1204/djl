package controller.admin;

import java.util.ArrayList;
import java.util.List;

import model.Feedback;
import model.Member;
import model.Pic;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.HttpCode;

@RouteViewPath("admin")
public class FeedbackController extends CRUD {

	@Override
	protected String route() {
		return "admin/feedback";
	}

	/**
	 * @方法名:显示列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月16 1:48:19
	 * @修改:
	 */
	public void showViewList() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			String sql = "";
			Feedback model = new Feedback();
			Member member = new Member();
			Pic picModel = new Pic();
			Page<Record> result = model.getPaging(page, pageSize);
			List<Record> picList = new ArrayList<Record>();
			for (Record re : result.getList()) {
				Record memberRe = member.showModel(re.getStr("memberId"),
						new JSONObject());
				re.set("nickName", memberRe.getStr("nickname"));
				sql = "select pic from " + picModel.tableName()
						+ " where isDel='0' and relationId=?";
				picList = Db.find(sql, re.getStr(model.tableKey()));
				re.set("pic", picList);
			}
			renderJSON(true, "数据已更新", result);
		} else {
			renderJsp("feedback/index.jsp");
		}
	}

	/**
	 * @方法名:新增反馈
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月21 8:43:44
	 * @修改:
	 */

	public void addFeedback() {
		if (isPost()) {
			String where = getPara("where");
			JSONObject json = new JSONObject();
			if (StringUtils.isBlank(where)) {
				renderJSON(HttpCode.UNSUPPORTED, "操作失败");
				return;
			}
			json = JSONObject.parseObject(where);
			JSONArray img = (JSONArray) json.remove("img");
			Feedback model = new Feedback();
			CodeBean<?> bean = model.save(json);
			if (bean.isSuccess()) {// 保存图片
				for (Object path : img) {
					Pic picModel = new Pic();
					picModel.set("pic", path);
					picModel.set("relationId", bean.getData());
					picModel.set("type", "2");
					picModel.saveModel(picModel);
				}
			}
			renderJSON(bean);
		} else {
			Record admin = (Record) getSession().getAttribute("admin");
			setAttr("U_id", admin.getStr("U_id"));
			renderJsp("feedback/modify.jsp");
		}

	}
}
