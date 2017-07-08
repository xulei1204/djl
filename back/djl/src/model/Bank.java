package model;

import com.xiaheng.core.jfinal.BaseModel;

/**
 * 银行 model
 *
 * @version 1.0
 * @since 2017-7-5 19:28:42
 */
public class Bank extends BaseModel<Bank> {

	private static final long serialVersionUID = 1L;
	
	public static final Bank dao = new Bank();
	
	
	@Override
	public String tableName() {
		return "a2017_djl_bank";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	

}
