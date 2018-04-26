package tk.imdt.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
	private String result = "";
	private PrintWriter out = null;
	private BufferedReader in = null;
	private method method;
	private String params;
	private URL url;
	private String urlString;

	public static enum method {
		GET, POST
	}

	@SuppressWarnings("static-access")
	public String postCall() throws IOException {
		if (this.method == method.POST) {
			return post();
		} else {
			return get();
		}
	}

	public HttpUtil(String url, method method, String params) {
		this.urlString = url;
		this.params = params;
		this.method = method;
	}

	private String get() throws IOException {
		this.url = new URL(this.urlString + this.params);
		URLConnection connection = this.url.openConnection();
		connection.setUseCaches(false);
		// connection.setRequestProperty("cookie", cookie);
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		// connection.setRequestProperty("origin", this.url);
		connection.connect();
		this.in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line;
		while ((line = this.in.readLine()) != null) {
			this.result += line;
		}
		this.in.close();
		return this.result;
	}

	private String post() throws IOException {
		this.url = new URL(this.urlString);
		URLConnection connection = this.url.openConnection();
		connection.setUseCaches(false);
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		// connection.setRequestProperty("origin", "localhost");
		// connection.setRequestProperty("referer", this.url + "index.php");
		connection.setAllowUserInteraction(false);
		connection.setDoOutput(true);
		connection.setDoInput(true);

		this.out = new PrintWriter(connection.getOutputStream());
		this.out.print(params);
		this.out.flush();

		// Map<String, List<String>> map = connection.getHeaderFields();
		this.in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line;
		while ((line = this.in.readLine()) != null) {
			this.result += line;
		}
		this.out.close();
		this.in.close();
		// String finalString = connection.getHeaderFields().toString();

		return this.result;
	}
}
