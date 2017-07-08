package ${model.packageName};

import com.xiaheng.core.jfinal.BaseModel;

/**
 * ${model.comment} model
 *
 * @version 1.0
 * @since ${model.addTime}
 */
public class ${model.tableName} extends BaseModel<${model.tableName}> {

	private static final long serialVersionUID = 1L;
	
	public static final ${model.tableName} dao = new ${model.tableName}();
	
	
	@Override
	public String tableName() {
		return "${model.dbName}";
	}

	@Override
	public String tableKey() {
		return "${model.dbKey}";
	}
	

}