package utils;

import java.io.Serializable;

/**
 * @author sophia
 */
public class ApiResult implements Serializable {

	private static final long serialVersionUID = -1594767577500901474L;

	private boolean success;
	private String msg;
	private Object data;

	public ApiResult() {
		super();
	}

	public ApiResult(boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
