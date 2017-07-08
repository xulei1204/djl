package controller.api;

import java.util.List;

import model.Ad;
import model.Member;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class ADController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/ad";
	}

	/**
	 * @方法名:显示列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月19 4:24:54
	 * @修改:
	 */
	public void showList() {

		int page = getParaToInt("page", 1);
		int pageSize = getParaToInt("pageSize", 10);
		String keyword = getPara("keyword", "");
		String classifyId = getPara("classifyId", "");
		JSONObject where = new JSONObject();
		JSONObject search = new JSONObject();
		search.put("title", keyword);// 关键字
		where.put("classifyId", classifyId);// 分类
		Ad model = new Ad();
		Page<Record> result = model.getPaging(page, pageSize, where, search);
		/*
		 * Classify classify = new Classify(); String classifyId = ""; Record
		 * classifyRe; for (Record re : result.getList()) { classifyId =
		 * re.getStr("classifyId"); classifyRe = classify.showModel(classifyId,
		 * new JSONObject()); re.set("classifyId", classifyRe.getStr("title"));
		 * }
		 */
		Member member = new Member();
		Record memberRe;
		String memberId = "";
		// 发布者
		for (Record re : result.getList()) {
			memberId = re.getStr("memberId");
			memberRe = member.showModel(memberId, new JSONObject());
			re.set("nickname", memberRe.getStr("nickname"));
			re.set("headpic", memberRe.getStr("pic"));
		}
		if (result.getList().size() == 0) {
			renderJSON("203", "没有更多数据了");
		}
		renderJSON("200", "请求成功", result);
	}

	/**
	 * @方法名:显示详情
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月28 9:45:31
	 * @修改:
	 */
	public void showDetail() {
		String id = getPara("id");
		Ad model = new Ad();
		Record result = model.showDetail(id);
		if (StringUtils.isBlank(id) || result == null) {
			renderJSON(false, "请求错误");
			return;
		}
		result.set("video", result.get("video"));
		result.set("cover", result.get("cover"));
		// 下三篇
		List<Record> next = model.getNext(result);
		result.set("next", next);
		renderJSON(true, "请求成功", result);
	}

}
