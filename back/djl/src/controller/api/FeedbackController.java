package controller.api;

import model.Feedback;
import model.Pic;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.ProjectUtil;
import com.xiaheng.utils.UploadUtils;

@RouteViewPath("api")
public class FeedbackController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/feedback";
	}

	/**
	 * @方法名:反馈详情
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月4 7:29:23
	 * @修改:
	 */
	public void showDetail() {
		String id = getPara("id", "");
		Feedback model = new Feedback();
		renderJSON("200", "请求成功", model.showModel(id, new JSONObject()));
	}

	/**
	 * @方法名:提交意见反馈
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月5 10:27:54
	 * @修改:
	 */
	public void submit() {
		String dataStr = getPara("JSON", "");
		JSONObject data = JSONObject.parseObject(dataStr);
		JSONArray picArr = data.getJSONArray("pic");
		if (StringUtils.isBlank(data.getString("content"))) {
			renderJSON(false, "内容不可为空");
			return;
		}
		Feedback model = new Feedback();
		model.set("content", data.getString("content"));
		model.set("memberId", data.getString("uid"));
		CodeBean<String> bean = model.saveModelSe(model);
		if (!bean.isSuccess()) {
			renderJSON(false, "保存失败");
			return;
		}

		String feedbackId = bean.getData();

		if (!picArr.isEmpty()) {
			for (Object base64 : picArr) {
				String path = UploadUtils.GenerateImage(base64.toString(),
						ProjectUtil.getRealPath(getRequest()), "file/",
						UploadUtils.ImageFormat.JPG);
				Pic pic = new Pic();
				pic.set("relationId", feedbackId);
				pic.set("type", 2);
				pic.set("pic", path);
				pic.saveModel(pic);
			}
		}
		renderJSON(true, "提交成功");
	}

}
