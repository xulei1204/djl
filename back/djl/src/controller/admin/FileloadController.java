/*
 * @(#) FileloadController.java 2016年12月22日
 *
 * Copyright (c) 2015, XiaHeng NetWork. All Rights Reserved.
 * XiaHeng NetWork. CONFIDENTIAL
 */
package controller.admin;

import java.util.ArrayList;
import java.util.List;

import utils.interceptor.LoginAdminInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.upload.UploadFile;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.ProjectUtil;
import com.xiaheng.utils.UploadUtils;

/**
 * 附件上传
 * 
 * @Description
 * 
 * @author Chuck Don
 * @version 1.0
 * @since 2016年12月22日
 */
@Before(LoginAdminInterceptor.class)
public class FileloadController extends CRUD {

	@Override
	public String route() {
		// TODO Auto-generated method stub
		return "/admin/file";
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

	/**
	 * @方法名:富文本编辑器上传图片
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月16 4:51:07
	 * @修改:
	 */
	public void uploadImgByEdit() {
		List<UploadFile> files = getFiles();
		CodeBean<?> bean = UploadUtils
				.uploadFile(files, ".jpg|.png", 1024 * 10);
		if (bean.isSuccess()) {
			JSONObject json = new JSONObject();
			json.put("code", 0);
			json.put("msg", "上传成功");
			JSONObject data = new JSONObject();
			@SuppressWarnings("unchecked")
			ArrayList<String> path = (ArrayList<String>) bean.getData();
			data.put("src", ProjectUtil.getLoaclURL(getRequest()) + path.get(0));
			json.put("data", data);
			renderJson(json);
		}
	}
}
