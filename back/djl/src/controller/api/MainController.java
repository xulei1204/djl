package controller.api;

import java.util.List;

import com.jfinal.upload.UploadFile;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import com.xiaheng.utils.UploadUtils;

@RouteViewPath("api")
public class MainController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "/api";
	}

	/**
	 * @方法名:上传图片
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月20 10:51:31
	 * @修改:
	 */
	public void uploadImg() {
		List<UploadFile> files = getFiles();
		renderJSON(UploadUtils.uploadFile(files, ".jpg|.png|.jpeg", 1024 * 3));
	}

	/**
	 * @方法名:上传视频
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月20 10:51:39
	 * @修改:
	 */
	public void uploadVideo() {
		List<UploadFile> files = getFiles();
		renderJSON(UploadUtils.uploadFileSe(files, ".mp4", 1024 * 30));
	}

}
