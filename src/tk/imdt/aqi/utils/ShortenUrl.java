package tk.imdt.aqi.utils;

import org.json.JSONObject;

import tk.imdt.aqi.CustomData;
import tk.imdt.aqi.SSLHttp;

public class ShortenUrl {
	public static String shortUrl(String url) {
		JSONObject obj = new JSONObject().put("longUrl", url);
		SSLHttp sslhttp = new SSLHttp("https://www.googleapis.com/urlshortener/v1/url?key=" + CustomData.googleMapKey);
		//return sslhttp.post(obj);
		return (new JSONObject(sslhttp.post(obj)).getString("id"));
		
	}
}
