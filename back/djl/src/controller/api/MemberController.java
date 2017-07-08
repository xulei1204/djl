package controller.api;

import model.Admin;
import model.Consume;
import model.Dividend;
import model.Finalset;
import model.Member;

import org.apache.commons.lang3.StringUtils;

import utils.project.CodeType;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.EncodeUtils;
import com.xiaheng.utils.ProjectUtil;
import com.xiaheng.utils.UploadUtils;

@RouteViewPath("api")
public class MemberController extends BaseController {

	@Override
	protected String route() {
		return "api/member";
	}

	/**
	 * @方法名:注册获取验证码
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月23 9:33:29
	 * @修改:
	 */
	public void sendCodeRe() {
		String tel = getPara("tel");
		String type = getPara("type", "");
		Member model = new Member();
		JSONObject where = new JSONObject();
		where.put("tel", tel);
		where.put("username", tel);
		where.put("type", type);
		Record member = model.showModel(where);
		if (member != null) {
			renderJSON(false, "该手机号已被注册！");
			return;
		}
		CodeBean<?> bean = CodeType.register.sendCode(tel, null, null);
		renderJSON(bean);
	}

	/**
	 * @方法名:用户注册
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月22 3:28:32
	 * @修改:
	 */
	public void register() {
		String tel = getPara("tel");
		String password = getPara("password");
		String type = getPara("type", "");// 注册类型 1.普通用户 2.业主
		String code = getPara("code");// 验证码
		JSONObject where = new JSONObject();
		where.put("username", tel);
		where.put("tel", tel);
		where.put("type", type);
		Member model = new Member();
		Record member = model.showModel(where);
		if (!CodeType.register.checkCode(tel, code).isSuccess()) {
			// 验证验证码
			renderJSON(false, "验证码不正确");
			return;
		}
		if (member != null) {
			// 确认没有被注册
			renderJSON(false, "该手机号已被注册！");
			return;
		}
		where.put("pic", "file/default.jpg");
		where.put("nickname", tel);
		where.put("password", EncodeUtils.MD5Encode(password));
		if ("1".equals(type)) {
			renderJSON(generalRegister(where));
		} else if ("2".equals(type)) {
			where.put("idcardA", getPara("idcardA", ""));
			where.put("idcardB", getPara("idcardB", ""));
			where.put("license", getPara("license", ""));
			renderJSON(ownerRegister(where));
		}

	}

	/**
	 * @方法名:普通用户注册
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月23 1:50:50
	 * @修改:
	 */
	public CodeBean<?> generalRegister(JSONObject userInfo) {
		Member member = new Member();
		CodeBean<?> bean = member.modifyModel(userInfo);
		if (bean.isSuccess()) {
			bean.setMessage("注册成功");
		}
		return bean;
	}

	/**
	 * @方法名:业主注册
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月23 2:45:36
	 * @修改:
	 */
	public CodeBean<?> ownerRegister(JSONObject userInfo) {

		Member model = new Member();
		// 平台是否开启注册审核
		Finalset set = new Finalset();
		if ("1".equals(set.getValueById("1"))) {
			userInfo.put("isDel", "0");
		}
		CodeBean<?> bean = model.modifyModel(userInfo);
		if (bean.isSuccess()) {

			JSONObject where = new JSONObject();
			where.put("username", userInfo.get("username"));
			Record member = model.showModel(where);
			// 创建后台账户
			Admin admin = new Admin();
			admin.set("U_id", member.get(model.tableKey()));
			admin.set("userName", userInfo.get("username"));
			admin.set("password", userInfo.get("password"));
			admin.set("userRole", "2");

			admin.saveModel(admin);

			bean.setMessage("注册成功，请前往登陆！");
		}
		return bean;
	}

	/**
	 * @方法名:登录
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月23 3:34:21
	 * @修改:
	 */
	public void login() {
		String tel = getPara("tel");
		String password = getPara("password");
		String type = getPara("type");
		JSONObject where = new JSONObject();
		where.put("username", tel);
		where.put("password", EncodeUtils.MD5Encode(password));
		where.put("type", type);
		Member model = new Member();
		Record member = model.showModel(where);
		if (member == null) {
			renderJSON(false, "账号密码错误,或者身份选择错误");
			return;
		}
		member.remove("password");
		renderJSON(true, "登陆成功", member);
	}

	/**
	 * @方法名:我的收益
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月4 10:33:32
	 * @修改:
	 */
	public void showEarnings() {
		String u = getPara("u", "");
		JSONObject data = new JSONObject();
		Dividend dividend = new Dividend();
		Double frist = dividend.getDividendByUid(u, "1");
		Double second = dividend.getDividendByUid(u, "2");
		Consume consume = new Consume();
		Double redpacket = consume.getConsumeSum(u, "3");
		data.put("redpacket", redpacket);// 红包收益
		data.put("frist", frist);// 一级收益
		data.put("second", second);// 二级收益
		data.put("count", redpacket + frist + second);// 收益总额
		renderJSON(true, "请求成功", data);
	}

	/**
	 * @方法名:修改头像
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月5 3:49:11
	 * @修改:
	 */
	public void updateHeadpic() {
		String jsonStr = getPara("JSON");
		JSONObject data = JSONObject.parseObject(jsonStr);
		if (data.isEmpty()) {
			renderJSON(false, "上传失败");
			return;
		}
		String base64 = data.getString("pic");
		String path = UploadUtils.GenerateImage(base64.toString(),
				ProjectUtil.getRealPath(getRequest()), "file/",
				UploadUtils.ImageFormat.JPG);
		Member model = new Member();
		if (path == null || StringUtils.isBlank(data.getString("uid"))) {
			renderJSON(false, "上传失败");
			return;
		}
		model.set(model.tableKey(), data.getString("uid"));
		model.set("pic", path);
		success = model.updateModel(model);
		if (success) {
			renderJSON(true, "修改头像成功", path);
			return;
		}
		renderJSON(true, "修改头像失败");
	}

	/**
	 * @方法名:修改昵称
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月5 4:25:52
	 * @修改:
	 */
	public void updateNickname() {

		String nickname = getPara("nickname", "");
		String uid = getPara("uid", "");
		if (StringUtils.isBlank(nickname) || StringUtils.isBlank(uid)) {
			renderJSON(false, "修改失败");
			return;
		}

		Member model = new Member();
		model.set(model.tableKey(), uid);
		model.set("nickname", nickname);
		success = model.updateModel(model);
		if (success) {
			renderJSON(true, "修改昵称成功", nickname);
			return;
		}
		renderJSON(false, "修改昵称失败");
	}

	/**
	 * @方法名:修改密码
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月5 4:55:13
	 * @修改:
	 */
	public void updatePsword() {

		String prePsword = getPara("prePsword", "");
		String newPsword = getPara("newPsword", "");
		String againPsword = getPara("againPsword", "");
		String uid = getPara("uid", "");
		JSONObject where = new JSONObject();
		Member model = new Member();
		where.put("password", prePsword);
		System.out.println(model.showModel(uid, where));
		if (model.showModel(uid, where) == null) {
			renderJSON("203", "原密码不正确");
			return;
		}

		if (!newPsword.trim().equals(againPsword.trim())) {
			renderJSON(false, "201", "两次密码输入不一致");
			return;
		}

		model.put(model.tableKey(), uid);
		model.put("password", againPsword.trim());
		success = model.saveModel(model);
		if (success) {
			renderJSON(true, "修改密码成功");
			return;
		}
		renderJSON(false, "修改昵称失败");
	}

}
