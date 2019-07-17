package utils;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



public class ApiUtils {
	public static ApiResult result(boolean success,String msg,Object data){
		return new ApiResult(success, msg, data);
	}
	
	public static ApiResult success(){
		return success(null);
	}
	
	public static ApiResult success(Object data){
		return result(true, "成功", data);
	}
	
	public static ApiResult fail(String msg,Object data){
		return result(false, msg, data);
	}
	
	public static ApiResult fail(String msg){
		return fail(msg, null);
	}
	
	public static void outJson(HttpServletResponse response,Object obj){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			//response.getWriter().print(JSONObject.fromObject(obj).toString());
			String json = new Gson().toJson(obj).toString();
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void outSuccess(HttpServletResponse response,Object data){
		ApiResult result = success(data);
		outJson(response, result);
	}
	public static void outSuccess(HttpServletResponse response){
		outSuccess(response,null);
	}
	
	public static void outFail(HttpServletResponse response,String msg,Object data){
		ApiResult result = fail(msg, data);
		outJson(response, result);
	}
	
	public static void outFail(HttpServletResponse response,String msg){
		outFail(response, msg,null);
	}
	
	
}
