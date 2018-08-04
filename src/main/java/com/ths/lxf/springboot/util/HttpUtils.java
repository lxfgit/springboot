package com.ths.lxf.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 响应ajax请求，在没有发生异常或者只发生MyException时使用
     * @param response
     * @param data
     */
    public static void renderData(HttpServletResponse response, Object data) {
        PrintWriter writer = null;
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            String json = JSON.toJSONString(data, SerializerFeature.DisableCircularReferenceDetect);
            writer = response.getWriter();
            writer.print(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.flush();
            }
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip==null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip==null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-OP");
        }
        if (ip==null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_Client_Ip");
        }
        if (ip==null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip==null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 向指定的url发送GET请求
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public static String sendGet(String url, String param) throws IOException {
        String result = "";
        BufferedReader in = null;
        try {
            String urlString = url + "?" + param;
            URL realUrl = new URL(urlString);
            logger.info("请求地址为：", realUrl);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1");
            connection.setConnectTimeout(100 * 1000);
            connection.setReadTimeout(100 * 1000);
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "-->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("GET请求出现异常:" + e);
            throw e;
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定的url发送POST请求
     * @param url
     * @param param
     * @param charset
     * @return
     */
    public static String sendPost(String url, String param, String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = null;
        try{
            URL realUrl = new URL(url);
            logger.info("请求地址为：", realUrl);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1");
            connection.setConnectTimeout(100 * 1000);
            connection.setReadTimeout(100 * 1000);
            /*发送psot请求需要加入以下两行*/
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (StringUtil.isNull(charset)) {
                out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            } else {
                out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), charset));
            }
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            logger.info("返回值：" + result);
        } catch (Exception e) {
            logger.error("POST请求出现异常:" + e);
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }



}
