package model;

import com.xiaheng.core.jfinal.BaseModel;

/**
 * banner图 model
 *
 * @version 1.0
 * @since 2017-5-16 14:48:22
 */
public class Banner extends BaseModel<Banner> {

	private static final long serialVersionUID = 1L;
	
	public static final Banner dao = new Banner();
	
	
	@Override
	public String tableName() {
		return "a2017_djl_banner";
	}

	@Override
	public String tableKey() {
		return "id";
	}

}
