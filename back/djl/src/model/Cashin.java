package model;

import com.xiaheng.core.jfinal.BaseModel;

/**
 * 提现 model
 *
 * @version 1.0
 * @since 2017-6-14 16:00:12
 */
public class Cashin extends BaseModel<Cashin> {

	private static final long serialVersionUID = 1L;
	
	public static final Cashin dao = new Cashin();
	
	
	@Override
	public String tableName() {
		return "a2017_djl_cashin";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	

}
