package utils.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.xiaheng.utils.CodeBean;

/**
 * 短信接口
 * 
 * @author Administrator
 *
 */
public enum CodeType {
	register("register"),

	update("update"),

	forget("forget"),

	bind("bind");

	/**
	 * 调试模式为true 不发送真实短信， 非调试模式为false 发送真实短信
	 */
	private final boolean debug = false;

	/**
	 * 签名
	 */
	private final String sign = "【点就乐】";

	/**
	 * 用户名
	 */
	private final String name = "18105165851";

	/**
	 * 密码
	 */
	private final String pwd = "22E1CE71E3F3BD299C9DC4653241";

	/**
	 * 短信内容
	 * 
	 * @return
	 */
	public String getContent() {
		return "尊敬的用户，您的验证码为：{0} ！请及时完成验证！";
	};

	/**
	 * 容器
	 */
	private static final Map<String, Map<String, Object[]>> codeMap = new HashMap<String, Map<String, Object[]>>();

	static {
		for (CodeType type : CodeType.values()) {
			codeMap.put(type.tags, new HashMap<String, Object[]>());
		}
	}

	private String tags;

	/**
	 * 获取短信类型
	 * 
	 * @param tags
	 */
	private CodeType(String tags) {
		this.tags = tags;
	}

	/**
	 * 存放容器内 返回验证码
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public String putCodeMap(String phone, String code) {
		Map<String, Object[]> map = codeMap.remove(this.tags);
		map.put(phone, new Object[] { code, System.currentTimeMillis() });
		codeMap.put(this.tags, map);
		return code;
	};

	public Object[] getCodeMap(String phone) {
		Map<String, Object[]> map = codeMap.get(this.tags);
		if (map == null) {
			return null;
		}
		return map.get(phone);
	}

	/**
	 * 返回码
	 * 
	 * @param success
	 * @param message
	 * @return
	 */
	private CodeBean<Map<String, Object>> renderCode(boolean success,
			String message, Map<String, Object> data) {
		return new CodeBean<Map<String, Object>>(success, message, data);
	}

	/**
	 * 返回码
	 * 
	 * @param success
	 * @param message
	 * @return
	 */
	private CodeBean<Map<String, Object>> renderCode(boolean success,
			String message) {
		return new CodeBean<Map<String, Object>>(success, message);
	}

	/**
	 * 检查验证码的有效性
	 * 
	 * @param phone
	 * @param inputCode
	 * @return
	 */
	public CodeBean<Map<String, Object>> checkCode(String phone,
			String inputCode) {
		if (!Validator.isMobile(phone)) {
			return renderCode(false, "手机号码格式不正确");
		}
		Map<String, Object[]> map = codeMap.get(this.tags);
		Object[] obj = map.get(phone);
		if (obj == null)
			return renderCode(false, "请获取验证码");

		String code = obj[0].toString();
		Long InvalidTime = Long.parseLong(obj[1].toString()) + 300 * 1000;
		if (System.currentTimeMillis() > InvalidTime) {
			return renderCode(false, "验证码已失效");
		} else {
			if (!code.equals(inputCode)) {
				return renderCode(false, "验证码错误");
			}
			// map.remove(phone);
			codeMap.put(this.tags, map);
		}
		return new CodeBean<Map<String, Object>>(true, "验证码验证成功");
	}

	/**
	 * 发送验证码
	 * 
	 * @param phone
	 * @return
	 */
	public CodeBean<Map<String, Object>> sendCode(String phone, String IPV4,
			Integer platform) {
		if (!Validator.isMobile(phone)) {
			return renderCode(false, "手机号码格式不正确");
		}
		Object[] obj = this.getCodeMap(phone);
		if (obj != null) {
			Long InvalidTime = Long.parseLong(obj[1].toString()) + 5 * 60 * 1000;
			if (System.currentTimeMillis() <= InvalidTime) {
				return renderCode(false, "验证码五分钟内有效");
			}
		}
		String code = this.putCodeMap(phone,
				(int) (Math.random() * 8999 + 1000) + "");
		String content = MessageFormat.format(this.getContent(), code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		if (sendCode(content, phone)) {
			this.putCodeMap(phone, code);
			if (debug) {
				return renderCode(true, "验证码发送成功===" + content, map);
			}
			return renderCode(true, "验证码发送成功", map);
		} else {
			return renderCode(false, "验证码发送失败", map);
		}
	}

	/**
	 * 发送验证码
	 * 
	 * @author Chuck Don
	 * @since 2016年12月15日
	 * @param content
	 * @param phone
	 * @return
	 */
	private boolean sendCode(String content, String phone) {
		if (debug) {
			return true;
		}
		boolean flag = false;
		try {
			StringBuffer sb = new StringBuffer(
					"http://sms.1xinxi.cn/asmx/smsservice.aspx?");
			sb.append("name=" + name);
			sb.append("&pwd=" + pwd);
			sb.append("&mobile=" + phone);
			sb.append("&content=" + URLEncoder.encode(content, "UTF-8"));
			sb.append("&stime=");
			sb.append("&sign=" + URLEncoder.encode(sign, "UTF-8"));
			sb.append("&type=pt&extno=");
			URL url = new URL(sb.toString());
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String inputline = in.readLine();
			System.out.println(inputline);
			flag = true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;

	}

}
