package controller.admin;

import java.util.Date;

import model.Ad;
import model.China;
import model.Classify;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.HttpCode;

@RouteViewPath("admin")
public class AdController extends CRUD {

	@Override
	protected String route() {
		return "admin/ad";
	}

	/**
	 * @方法名:根据uid获取列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 4:24:54
	 * @修改:
	 */
	public void showList() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			Record admin = (Record) getSession().getAttribute("admin");
			String uid = admin.getStr("U_id");// 业主用户id
			JSONObject where = new JSONObject();
			String jsonStr = getPara("where", "{}");
			JSONObject json = new JSONObject();
			json = JSONObject.parseObject(jsonStr);
			where.put("memberId", uid);
			Ad model = new Ad();
			Page<Record> result = model.getPaging(page, pageSize, where, json);
			Classify classify = new Classify();
			String classifyId = "";
			Record classifyRe;
			for (Record re : result.getList()) {
				classifyId = re.getStr("classifyId");
				classifyRe = classify.showModel(classifyId, new JSONObject());
				re.set("classifyId", classifyRe.getStr("title"));
			}

			renderJSON(true, "请求成功", result);
		} else {
			renderJsp("ad/index.jsp");
		}

	}

	/**
	 * @方法名:新增页面
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 7:37:18
	 * @修改:
	 */
	public void showAdd() {
		String id = getPara("id");
		setAttr("op", getPara("op"));
		if (StringUtils.isNotBlank(id)) {
			Ad model = new Ad();
			Record result = model.showModel(id, new JSONObject());
			setAttr("v", result);
		}

		// 省列表
		China china = new China();
		setAttr("proList", china.getProList());

		// 分类集合
		Classify classify = new Classify();
		setAttr("classify", classify.getList());
		renderJsp("ad/modify.jsp");

	}

	/**
	 * @方法名:下一步->保存广告类容->设置红包
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月20 2:00:34
	 * @修改:
	 */
	public void next() {
		String where = getPara("where");
		JSONObject json = new JSONObject();
		if (StringUtils.isBlank(where)) {
			renderJSON(HttpCode.UNSUPPORTED, "操作失败");
			return;
		}
		json = JSONObject.parseObject(where);
		Ad model = new Ad();
		// 城市
		China china = new China();
		String pid = json.getString("Pid");
		String cid = json.getString("Cid");
		json.put("pname", china.findName(pid));
		json.put("cname", china.findName(cid));
		json.put("isDel", "-1");
		// 如果是公益广告直接发布
		if ("1".equals(json.getString("classifyId"))) {
			json.put("isDel", "0");
		}
		CodeBean<?> bean;
		if (StringUtils.isBlank(json.getString("id"))) {
			bean = model.saveModel(json);
		} else {
			bean = model.modifyModel(json);
		}
		renderJSON(bean);

	}

	/**
	 * @方法名:刷新发布
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月21 4:04:58
	 * @修改:
	 */
	public void refresPublish() {
		String id = getPara("id");
		if (StringUtils.isBlank(id)) {
			renderJSON(false, "操作失败");
			return;
		}
		Ad model = new Ad();
		JSONObject where = new JSONObject();
		where.put("id", id);
		where.put("addTime", new Date());
		CodeBean<?> bean = model.modifyModel(where);
		if (bean.isSuccess()) {
			bean.setMessage("刷新成功");
		}
		renderJSON(bean);
	}
}
