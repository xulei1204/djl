package model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 菜单表 model
 *
 * @version 1.0
 * @since 2017-5-16 14:48:27
 */
public class Menu extends BaseModel<Menu> {

	private static final long serialVersionUID = 1L;

	public static final Menu dao = new Menu();

	@Override
	public String tableName() {
		return "a2017_djl_menu";
	}

	@Override
	public String tableKey() {
		return "menuId";
	}

	public Page<Record> getPaging(int page, int pageSize, JSONObject json) {
		// TODO Auto-generated method stub
		StringBuffer select = new StringBuffer();
		select.append(" select menu.menuId,menu.menuName,menu.menuUrl,pMenu.menuName pMenuName,menu.sort ");
		StringBuffer from = new StringBuffer();
		from.append(" from " + Menu.dao.tableName());
		from.append(" menu left join " + Menu.dao.tableName());
		from.append(" pMenu on menu.pMenuId = pMenu.menuId ");
		from.append(" where menu.isDel='0' ");
		return Db.paginate(page, pageSize, select.toString(), from.toString());
	}

	public Record showModel(String modelId, JSONObject where) {
		// TODO Auto-generated method stub
		Record menu = super.showModel(modelId, where);
		menu.set("list", getParentMenu());
		return menu;
	}

	/**
	 * 所有父级菜单
	 * 
	 * @author Chuck Don
	 * @since 2016年12月21日
	 * @return
	 */
	public List<Record> getParentMenu() {
		return Db.find(" select menuId,menuName from " + Menu.dao.tableName()
				+ " where pMenuId='0' ");
	}

	/**
	 * 获取菜单列表
	 * 
	 * @author Chuck Don
	 * @since 2016年12月20日
	 * @param role
	 * @return
	 */
	public List<Record> menuList(String role) {
		StringBuffer select = new StringBuffer();
		select.append(" select menu.menuName,menu.menuUrl,menu.sort,menu.pMenuId,menu.menuId ");
		select.append(" from " + RoleMenu.dao.tableName());
		select.append(" roleMenu left join " + this.tableName());
		select.append(" menu on menu.menuId = roleMenu.menuId ");
		select.append(" where roleMenu.roleId = ?   ");
		select.append(" order by menu.sort asc   ");
		return Db.find(select.toString(), role);
	}

	/**
	 * @方法名:权限列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2016年12月22 2:44:04
	 * @修改:
	 */
	public List<Record> showAllMenu(String id) {
		StringBuffer select = new StringBuffer();
		select.append(" SELECT a.menuName,COUNT(b.roleMenuId) as `count` ");
		select.append(" from " + this.tableName());
		select.append(" a left join " + RoleMenu.dao.tableName());
		select.append(" b on a.menuId=b.menuId and b.roleId=?  ");
		select.append("  GROUP BY a.menuName ");
		return Db.find(select.toString(), id);
	}

}
