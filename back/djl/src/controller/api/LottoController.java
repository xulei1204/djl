package controller.api;

import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;

@RouteViewPath("api")
public class LottoController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/lotto";
	}

}
