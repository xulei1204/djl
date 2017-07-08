package controller.admin;

import model.Menu;
import model.RoleMenu;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;
import com.xiaheng.utils.CodeBean;

@RouteViewPath("admin/")
public class RoleController extends CRUD {

	@Override
	public String route() {
		// TODO Auto-generated method stub
		return "/admin/role";
	}

	/**
	 * @方法名:用户权限列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2016年12月22 2:49:36
	 * @修改:
	 */
	public void showPermMenu() {

		if (isPost()) {
			int page = getParaToInt("offset", 1);
			int pageSize = getParaToInt("limit", 10);
			page = (page / pageSize);
			page++;
			StringBuffer select = new StringBuffer();
			select.append(" SELECT b.roleMenuId,a.menuId,a.menuName,COUNT(b.roleMenuId) as `count` ");
			StringBuffer from = new StringBuffer();
			from.append(" from " + Menu.dao.tableName());
			from.append(" a left join " + RoleMenu.dao.tableName());
			from.append(" b on a.menuId=b.menuId and b.roleId=? ");
			from.append(" GROUP BY a.menuName ");
			Page<Record> result = Db.paginate(page, pageSize,
					select.toString(), from.toString(), getPara("id"));
			renderJSON(true, "数据已更新", result);
		} else {
			setAttr("id", getPara("id"));
			renderJsp("role/permission.jsp");
		}
	}

	/**
	 * @方法名:添加权限
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2016年12月22 8:10:26
	 * @修改:
	 */

	public void addPerm() throws Exception {
		String roleId = getPara("roleId");
		String menuId = getPara("menuId");
		JSONObject obj = new JSONObject();
		obj.put("roleId", roleId);
		obj.put("menuId", menuId);
		RoleMenu model = new RoleMenu();
		renderJSON(model.addPerm(obj));
	}

	/**
	 * @方法名:删除权限
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2016年12月22 9:11:06
	 * @修改:
	 */
	public void delPerm() throws Exception {
		String id = getPara("id");
		RoleMenu model = new RoleMenu();
		CodeBean<Object> success = model.deleteModel(id, true);
		renderJSON(success);

	}
}
