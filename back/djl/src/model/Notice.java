package model;

import com.xiaheng.core.jfinal.BaseModel;

/**
 * 系统通知 model
 *
 * @version 1.0
 * @since 2017-6-14 14:17:05
 */
public class Notice extends BaseModel<Notice> {

	private static final long serialVersionUID = 1L;
	
	public static final Notice dao = new Notice();
	
	
	@Override
	public String tableName() {
		return "a2017_djl_notice";
	}

	@Override
	public String tableKey() {
		return "id";
	}
	

}
