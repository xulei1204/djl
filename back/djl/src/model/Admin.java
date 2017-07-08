package model;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;
import com.xiaheng.utils.CodeBean;
import com.xiaheng.utils.EncodeUtils;

/**
 * 管理员表 model
 *
 * @version 1.0
 * @since 2017-5-16 14:48:22
 */
public class Admin extends BaseModel<Admin> {

	private static final long serialVersionUID = 1L;

	public static final Admin dao = new Admin();

	@Override
	public String tableName() {
		return "a2017_djl_admin";
	}

	@Override
	public String tableKey() {
		return "adminId";
	}

	/**
	 * 用户信息
	 * 
	 * @author Chuck Don
	 * @since 2016年12月20日
	 * @param userName
	 * @return
	 */
	public Record adminInfo(String userName) {
		StringBuffer select = new StringBuffer();
		select.append(" select admin.`password`,admin.`userName`,admin.U_id,role.`roleName`,role.roleId ");
		select.append(" from " + this.tableName());
		select.append(" admin left join " + Role.dao.tableName());
		select.append(" role on role.roleId = admin.userRole ");
		select.append(" where userName = ? ");
		return Db.findFirst(select.toString(), userName);
	}

	/**
	 * @方法名:用户列表
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年6月13 5:20:36
	 * @修改:
	 */
	public Page<Record> getList(int page, int pageSize) {

		StringBuffer sql = new StringBuffer();
		sql.append("select adminId,userName,userRole,addTime,updTime U_id");
		StringBuffer from = new StringBuffer();
		from.append(" from " + this.tableName());
		from.append(" where userRole!=2");
		from.append(" and isDel='0' ");
		Page<Record> list = Db.paginate(page, pageSize, sql.toString(),
				from.toString());
		Role model = new Role();
		Record roleModel = new Record();
		for (Record re : list.getList()) {
			String userRole = re.get("userRole");
			roleModel = model.showModel(userRole, new JSONObject());
			re.set("roleName", roleModel.get("roleName"));
		}
		return list;
	}

	public Record showModel(String modelId, JSONObject where) {
		// TODO Auto-generated method stub
		Record record = super.showModel(modelId, where);
		record.set("list", Role.dao.roleList());
		return record;
	}

	public CodeBean<?> modifyModel(BaseModel<?> model, JSONObject where) {
		// TODO Auto-generated method stub
		String passWord = where.getString("passWord");
		if (StringUtils.isNotEmpty(passWord)) {
			where.put("passWord", EncodeUtils.MD5Encode(passWord));
		}
		return super.modifyModel(where);
	}

}
