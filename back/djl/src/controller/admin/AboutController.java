package controller.admin;

import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.CRUD;

@RouteViewPath("admin/")
public class AboutController extends CRUD {

	@Override
	protected String route() {

		return "admin/about";
	}

}
