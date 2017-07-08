package model;

import com.xiaheng.core.jfinal.BaseModel;

/**
 * 考勤记录图片 model
 *
 * @version 1.0
 * @since 2017-5-16 14:48:27
 */
public class Pic extends BaseModel<Pic> {

	private static final long serialVersionUID = 1L;

	public static final Pic dao = new Pic();

	@Override
	public String tableName() {
		return "a2017_djl_pic";
	}

	@Override
	public String tableKey() {
		return "id";
	}

}
