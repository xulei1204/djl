package model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.core.jfinal.BaseModel;

/**
 * 角色表 model
 *
 * @version 1.0
 * @since 2017-5-16 14:48:28
 */
public class Role extends BaseModel<Role> {

	private static final long serialVersionUID = 1L;

	public static final Role dao = new Role();

	@Override
	public String tableName() {
		return "a2017_djl_role";
	}

	@Override
	public String tableKey() {
		return "roleId";
	}

	public List<Record> roleList() {
		StringBuffer select = new StringBuffer();
		select.append(" select roleId id,roleName name ");
		select.append(" from " + this.tableName());
		select.append(" where isDel = '0' and roleId!=2 ");
		return Db.find(select.toString());
	}

}
