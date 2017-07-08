package controller.admin;

import model.LottoRecord;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin")
public class LottoRecordController extends CRUD {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "admin/lottoRecord";
	}

	/**
	 * @方法名:每月抽奖参与列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 10:42:31
	 * @修改:
	 */
	public void joinMember() {
		if (isPost()) {
			String lottoId = getPara("id");
			LottoRecord model = new LottoRecord();
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			Page<Record> result = model.getList(page, pageSize, lottoId);
			renderJSON(true, "请求成功", result);
		} else {
			setAttr("id", getPara("id"));

			renderJsp("lotto/lottoRecord.jsp");
		}

	}
}
