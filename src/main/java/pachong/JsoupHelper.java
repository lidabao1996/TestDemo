package pachong;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * @author zhuxiongxian
 * @version 1.0
 * @created at 2017年3月17日 下午6:06:32
 */
public class JsoupHelper {

    /**
     * 获取连接
     *
     * @param url
     *            请求url
     * @param params
     *            参数
     * @param charset
     *            参数编码方式
     * @param headers
     *            头部信息
     * @return
     */
    public static Connection getConnection(String url, Map<String, String> params, String charset,
                                           Map<String, String> headers) {
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iter = params.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                paramList.add(new BasicNameValuePair(key, value));
            }
            try {
                String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(paramList, charset));
                StringBuffer sb = new StringBuffer();
                sb.append(url);
                if (url.indexOf("?") > 0) {
                    sb.append("&");
                } else {
                    sb.append("?");
                }
                sb.append(paramStr);
                url = sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Connection conn = Jsoup.connect(url);
        conn.timeout(10000); // 10秒超时
        conn.ignoreContentType(true);

        if (headers != null) {
            Iterator<Entry<String, String>> iter = headers.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> entry = iter.next();
                String key = entry.getKey();
                String value = entry.getValue();
                conn.header(key, value);
            }
        }

        return conn;
    }

    public static String get(String url, Map<String, String> params, String charset,
                             Map<String, String> headers) {
        String result = "";
        try {
            Connection conn = getConnection(url, params, charset, headers);
            Response response = conn.execute();
            result = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
