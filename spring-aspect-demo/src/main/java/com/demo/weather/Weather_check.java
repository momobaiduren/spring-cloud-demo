package com.demo.weather;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * created by zhanglong and since  2019/12/10  6:23 下午
 *
 * @description: 描述
 */
public class Weather_check {
    /**
     *
     * 获取实时天气1<br>
     * 方 法 名： getTodayWeather <br>
     *
     * @param Cityid  城市名
     *
     */
//    注：参数字符串，如果拼接在请求链接之后，需要对中文进行 URLEncode   字符集 UTF-8，转化方式在下面，直接传过去就好了
    public static Map<String, Object> getTodayWeather1(String Cityid)
        throws IOException, NullPointerException {
        // 连接和风天气的API
        String url1= "https://free-api.heweather.net/s6/weather/now?location="+Cityid+"&key=3c3fa198cacc4152b94b20def11b2455";

        URL url = new URL(url1);
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String datas = sb.toString();
            //截取[]转化为json格式
            datas = datas.replace(datas.substring(datas.indexOf(":")+1,datas.indexOf(":")+2),"");
            datas = datas.replace(datas.substring(datas.length()-2,datas.length()-1),"");
            JSONObject jsonData = JSONObject.parseObject(datas);
            JSONObject info = jsonData.getJSONObject("HeWeather6");
            JSONObject jsonData1 = JSONObject.parseObject(info.getString("basic").toString());
            JSONObject jsonData2 = JSONObject.parseObject(info.getString("update").toString());
            JSONObject jsonData3 = JSONObject.parseObject(info.getString("now").toString());
            map.put("location",jsonData1.getString("location").toString());
            map.put("parent_city",jsonData1.getString("parent_city").toString());
            map.put("admin_area",jsonData1.getString("admin_area").toString());
            map.put("cnty",jsonData1.getString("cnty").toString());

            String time = jsonData2.getString("loc").toString();

            String week = strToDate(time);

            map.put("week",week);
            map.put("time",jsonData2.getString("loc").toString());

            map.put("tmp",jsonData3.getString("tmp").toString());
            map.put("wind_dir",jsonData3.getString("wind_dir").toString());
            map.put("cond_txt",jsonData3.getString("cond_txt").toString());
            map.put("cond_code",jsonData3.getString("cond_code").toString());
            System.out.println(map);
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } finally {
            //关闭流
//            try {
//                if(br!=null){
//                    br.close();
//                }
//
//            } catch ( Exception  e) {
//                e.printStackTrace();
//            }
        }

        return map;

    }

    /**
     * 时间获得星期
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static String strToDate(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(formatter.parse(strDate));
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(c.getTime());
        return week;
    }
    /**
     * 字符集转码
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String url) throws UnsupportedEncodingException {
        if(url == null) {
            return null;
        }

        final String reserved_char = ";/?:@=&";
        String ret = "";
        for(int i=0; i < url.length(); i++) {
            String cs = String.valueOf( url.charAt(i) );
            if(reserved_char.contains(cs)){
                ret += cs;
            }else{
                ret += URLEncoder.encode(cs, "utf-8");
            }
        }
        return ret.replace("+", "%20");
    }

//    以下用的国家气象局提供的天气预报接口，该接口也是免费的

    /**
     *
     * 获取实时天气2<br>
     * 方 法 名： getTodayWeather <br>
     *
     * @param Cityid
     *            城市编码
     */
    public static Map<String, Object> getTodayWeather2(String Cityid)
        throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("http://www.weather.com.cn/data/cityinfo/" + Cityid
            + ".html");
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String datas = sb.toString();
            System.out.println(datas);
            JSONObject jsonData = JSONObject.parseObject(datas);
            JSONObject info = jsonData.getJSONObject("weatherinfo");
            map.put("city", info.getString("city").toString());// 城市
            map.put("temp1", info.getString("temp1").toString());// 最高温度
            map.put("temp2", info.getString("temp2").toString());// 最低温度
            map.put("weather", info.getString("weather").toString());//天气
            map.put("ptime", info.getString("ptime").toString());// 发布时间

        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }

    public static void main(String[] args) {
        try {
            //测试获取实时天气1(包含风况，湿度)
            Map<String, Object> map = getTodayWeather1("北京");
            System.out.println(map);


            //测试获取实时天气2(包含天气，温度范围)
            Map<String, Object> map2 = getTodayWeather2("101110908"); //这块填的是城市编码
            System.out.println(map2.get("city") + "\t" + map2.get("temp1")
                + "\t" + map2.get("temp2") + "\t" + map2.get("weather")
                + "\t" + map2.get("ptime"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
