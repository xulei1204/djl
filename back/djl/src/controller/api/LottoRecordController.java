package controller.api;

import model.Lotto;
import model.LottoRecord;
import model.RedpacketRecord;

import com.jfinal.plugin.activerecord.Record;
import com.xiaheng.annotation.RouteViewPath;
import com.xiaheng.core.jfinal.BaseController;
import com.xiaheng.utils.ProjectUtil;

@RouteViewPath("api")
public class LottoRecordController extends BaseController {

	@Override
	protected String route() {
		// TODO Auto-generated method stub
		return "api/lottorecord";
	}

	/**
	 * @方法名:用户抽奖
	 * @参数:
	 * @输出:
	 * @备注:
	 * @作者: 徐磊
	 * @时间: 2017年7月7 4:37:31
	 * @修改:
	 */
	public void prizeDraw() {

		String uid = getPara("u");
		// 此用户是否满足抽奖资格
		RedpacketRecord redRe = new RedpacketRecord();
		if (redRe.isMeetForPrize(uid)) {
			renderJSON(false, "抱歉！您不满足抽奖条件");
			return;
		}
		// 是否在抽时间
		if (ProjectUtil.getNowDate() > 10) {
			renderJSON(false, "抱歉！当前不在抽奖时间！");
			return;
		}

		// 本月是否开启抽奖
		Lotto lotto = new Lotto();
		Record lottoResult = lotto.isOpenPrize();
		if (lottoResult == null) {
			renderJSON(false, "抱歉！本月抽奖还未开启！");
			return;
		}

		// 是否已经开奖
		if (lottoResult.getInt("status") == 2) {
			renderJSON(false, "抱歉！本月抽奖已结束！");
			return;
		}

		// 是否还有抽奖机会
		String lottoId = lottoResult.getStr(lotto.tableKey());
		LottoRecord lottoRe = new LottoRecord();
		if (lottoRe.queryPrizeSurplus(uid, lottoId) <= 0) {
			renderJSON(false, "抱歉！您已经没有抽奖机会了！");
			return;
		}

	}
}
