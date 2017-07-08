package controller.api;

import model.Comment;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class CommentController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/comment";
	}

	/**
	 * @方法名:显示广告评论
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月29 3:08:37
	 * @修改:
	 */
	public void showComment() {
		int page = getParaToInt("page", 1);
		int pageSize = getParaToInt("pageSize", 10);
		String ADID = getPara("ADID", "");
		if (StringUtils.isBlank(ADID)) {
			renderJSON(false, "请求错误");
		}
		Comment model = new Comment();
		Page<Record> result = model.getListByADID(ADID, pageSize, page);
		if (result.getList().size() == 0) {
			renderJSON("203", "没有更多数据了");
		}
		renderJSON("200", "请求成功", result);
	}

	/**
	 * @方法名:添加评论
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月29 4:33:02
	 * @修改:
	 */
	public void addComment() {
		String ADID = getPara("ADID", "");
		String memberId = getPara("u", "");
		String content = getPara("content", "");

		if (StringUtils.isBlank(ADID) || StringUtils.isBlank(memberId)) {
			renderJSON(false, "请求错误");
		}
		Comment model = new Comment();
		model.set("ADID", ADID);
		model.set("memberId", memberId);
		model.set("content", content);

		boolean success = model.saveModel(model);
		if (success) {
			renderJSON(success, "提交评论成功");
			return;
		}
		renderJSON(success, "提交评论失败");

	}
}
