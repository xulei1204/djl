package controller.admin;

import model.Admin;
import model.Finalset;
import model.Member;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.EncodeUtils;
import com.xiaheng.utils.HttpCode;

@RouteViewPath("admin")
public class MemberController extends CRUD {

	@Override
	protected String route() {
		return "admin/member";
	}

	/**
	 * @方法名:待审核业主注册列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月14 7:11:47
	 * @修改:
	 */
	public void showCheckList() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			Member model = new Member();
			JSONObject where = new JSONObject();
			where.put("isDel", "1");
			where.put("type", "2");
			Page<Record> result = model.getListDel(page, pageSize, where);

			renderJSON(true, "数据已更新", result);
		} else {
			// 获取注册审核设置
			Record finalSet = new Finalset().showModel("1", new JSONObject());
			setAttr("finalSet", finalSet);
			renderJsp("member/checkRegister.jsp");
		}

	}

	/**
	 * @方法名:获取用户信息
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 3:26:35
	 * @修改:
	 */
	public void showInfo() {
		Record admin = (Record) getSession().getAttribute("admin");
		String uid = admin.getStr("U_id");// 业主用户id
		String type = getPara("type", "1");
		Member model = new Member();
		Record info = model.showModel(uid, new JSONObject());
		if (isPost()) {
			renderJSON(true, "请求成功", info);
			return;
		}
		setAttr("v", info);
		switch (type) {
		case "1":
			renderJsp("myspace/backgroundSet.jsp");
			break;
		case "2":
			setAttr("type", 2);
			setAttr("op", "see");
			renderJsp("member/modify.jsp");
			break;
		case "3":
			renderJsp("member/quotaSet.jsp");
			break;
		case "4":
			renderJsp("member/updPassword.jsp");
			break;
		default:
			break;
		}

	}

	/**
	 * @方法名:修改密码
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月22 10:30:37
	 * @修改:
	 */
	public void updPsword() {
		String where = getPara("where");
		JSONObject json = new JSONObject();
		if (StringUtils.isBlank(where)) {
			renderJSON(HttpCode.UNSUPPORTED, "操作失败");
			return;
		}
		json = JSONObject.parseObject(where);
		String uid = json.getString("U_id");
		String oldPsword = json.getString("oldPsword");
		String newPsword = json.getString("newPsword");
		String agPsword = json.getString("agPsword");
		Member model = new Member();
		JSONObject params = new JSONObject();
		params.put("password", EncodeUtils.MD5Encode(oldPsword));
		Record member = model.showModel(uid, params);
		if (member == null) {
			renderJSON(false, "原密码不正确！");
			return;
		}
		if (!agPsword.equals(newPsword)) {
			renderJSON(false, "两次密码输入不一致！");
			return;
		}
		params = new JSONObject();
		params.put("U_id", uid);
		String password = EncodeUtils.MD5Encode(newPsword);
		params.put("password", password);
		CodeBean<?> bean = model.modifyModel(params);
		if (bean.isSuccess()) {
			// 同时修改后台登陆密码
			Admin admin = new Admin();
			Record adminInfo = admin.showModel(params);
			params.remove("U_id");
			params.put("adminId", adminInfo.get("adminId"));
			bean = admin.modifyModel(params);
		}
		if (bean.isSuccess()) {
			bean.setMessage("修改密码成功，请重新登陆！");
			getSession().removeAttribute("admin");
		}
		renderJSON(bean);

	}

}
