package model;

import com.alibaba.fastjson.JSONObject;
import com.xiaheng.core.jfinal.BaseModel;
import com.xiaheng.utils.CodeBean;

/**
 * 菜单角色关联表 model
 *
 * @version 1.0
 * @since 2017-5-16 14:48:28
 */
public class RoleMenu extends BaseModel<RoleMenu> {

	private static final long serialVersionUID = 1L;
	
	public static final RoleMenu dao = new RoleMenu();
	
	
	@Override
	public String tableName() {
		return "a2017_djl_role_menu";
	}

	@Override
	public String tableKey() {
		return "roleMenuId";
	}

	/**
	* @方法名:新增权限
	* @参数:
	* @输出:
	* @备注:
	* @作者: 徐磊
	* @时间: 2017年6月13 4:30:24
	* @修改:
	*/
	public CodeBean<Object> addPerm(JSONObject obj){		
		RoleMenu model=new RoleMenu();
		model.set("roleId", obj.get("roleId"));
		model.set("menuId", obj.get("menuId"));
		Boolean success=model.saveModel(model);
		CodeBean<Object> CodeBean=new CodeBean<Object>();
		CodeBean.setSuccess(success);
		if(success){
			CodeBean.setMessage("保存成功");
		}else{
			CodeBean.setMessage("保存失败");
		}
		return CodeBean;
	}
	
}
