package controller.admin;

import model.Comment;
import model.Member;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin")
public class CommentController extends CRUD {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "admin/comment";
	}

	/**
	 * @方法名:显示广告评论
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月21 4:38:46
	 * @修改:
	 */
	public void showList() {
		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			String id = getPara("ADID");
			Comment model = new Comment();
			JSONObject where = new JSONObject();
			where.put("ADID", id);
			Page<Record> result = model.getPaging(page, pageSize, where);
			Record memberRe = new Record();
			Member member = new Member();
			for (Record re : result.getList()) {
				memberRe = member.showModel(re.getStr("memberId"),
						new JSONObject());
				re.set("memberName", memberRe.getStr("nickname"));

			}
			renderJSON(true, "数据更新成功", result);
		} else {
			setAttr("ADID", getPara("id"));
			renderJsp("comment/index.jsp");
		}

	}

}
