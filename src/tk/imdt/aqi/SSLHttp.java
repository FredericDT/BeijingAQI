package tk.imdt.aqi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class SSLHttp {
	private HttpPost post = null;
	private HttpGet get = null;
	private HttpResponse res = null;
	public HttpResponse getHttpResponse() {
		return this.res;
	}
	private static CloseableHttpClient getHttpClient() {  
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create();  
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();  
        registryBuilder.register("http", plainSF);  
//鎸囧畾淇′换瀵嗛挜瀛樺偍瀵硅薄鍜岃繛鎺ュ鎺ュ瓧宸ュ巶  
        try {  
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
            //淇′换浠讳綍閾炬帴  
            TrustStrategy anyTrustStrategy = new TrustStrategy() {  
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {  
                    return true;  
                }
            };  
            SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy).build();  
            LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
            registryBuilder.register("https", sslSF);  
        } catch (KeyStoreException e) {  
            throw new RuntimeException(e);  
        } catch (KeyManagementException e) {  
            throw new RuntimeException(e);  
        } catch (NoSuchAlgorithmException e) {  
            throw new RuntimeException(e);  
        }  
        org.apache.http.config.Registry<ConnectionSocketFactory> registry = registryBuilder.build();  
        //璁剧疆杩炴帴绠＄悊鍣�  
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);  
//      connManager.setDefaultConnectionConfig(connConfig);  
//      connManager.setDefaultSocketConfig(socketConfig);  
        //鏋勫缓瀹㈡埛绔�  
        return HttpClientBuilder.create().setConnectionManager(connManager).build();  
    }  
	public SSLHttp(String url) {
		this.post = new HttpPost(url);
		this.get = new HttpGet(url);
	}
	public String get(String cookie, boolean isAllowFlow) {
		HttpClient client = getHttpClient();
		this.get.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		HttpParams params = new BasicHttpParams();
		params.setParameter("http.protocol.handle-redirects",isAllowFlow);
		this.get.setParams(params);
		this.get.setHeader("Content-Type", "text/html; charset=utf-8");
		this.get.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
		String response = "";  
        try {  
            this.res = client.execute(this.get);  
            response = this.res.getStatusLine().getStatusCode() + "";
            if(this.res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
            	//response = res.getEntity().getContent().toString();
                HttpEntity entity = this.res.getEntity();
                response = EntityUtils.toString(entity);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
        return response;

	}
	public String get(String cookie) {
		return get(cookie, false);
	}
	public String post(String param) {
		HttpClient client = getHttpClient();
		//HttpParams params = new BasicHttpParams();
		//params.setParameter("http.protocol.handle-redirects",false);
		//this.post.setParams(params);
		this.post.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		this.post.getParams().setParameter("http.protocol.single-cookie-header", true);
		//this.post.setHeader("Content-Type", "application/json");
		this.post.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        String response = "";  
        try {  
            StringEntity s = new StringEntity(param);  
            //s.setContentEncoding("UTF-8");  
            s.setContentType("application/x-www-form-urlencoded");  
            this.post.setEntity(s);  
              
            this.res = client.execute(this.post);  
            response = this.res.getStatusLine().getStatusCode() + "";
            if(this.res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
            	//response = res.getEntity().getContent().toString();
                HttpEntity entity = this.res.getEntity();  
                response = EntityUtils.toString(entity);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
        return response;
	}
	public String post(String param, boolean isAllowFlow) {
		HttpParams params = new BasicHttpParams();
		params.setParameter("http.protocol.handle-redirects", isAllowFlow);
		this.post.setParams(params);

		return post(param);
	}
	public String post(JSONObject json){ 
		HttpClient client = getHttpClient();
		this.post.setHeader("Content-Type", "application/json");
		this.post.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        String response = "";  
        try {  
            StringEntity s = new StringEntity(json.toString());  
            //s.setContentEncoding("UTF-8");  
            s.setContentType("application/json");  
            this.post.setEntity(s);  
              
            this.res = client.execute(this.post);  
            response = this.res.getStatusLine().getStatusCode() + "";
            if(this.res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
            	//response = res.getEntity().getContent().toString();
                HttpEntity entity = this.res.getEntity();  
                response = EntityUtils.toString(entity);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
        return response;
    }
    /** 
     * 根据地址获得数据的字节流 
     * @param strUrl 网络连接地址 
     * @return 
     */  
    public static byte[] getImageFromNetByUrl(String strUrl){  
        try {  
            URL url = new URL(strUrl);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");  
            //conn.setConnectTimeout(5 * 1000);  
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据  
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据  
            return btImg;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    /** 
     * 从输入流中获取数据 
     * @param inStream 输入流 
     * @return 
     * @throws Exception 
     */  
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  
    public static String uploadFile(String url, String fileUrl) throws ClientProtocolException, IOException {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPost uploadFile = new HttpPost(url);
    	MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    	builder.addTextBody("chat_id", "48212282");

    	// This attaches the file to the POST:
    	//File f = new File("[/path/to/upload]");
    	builder.addBinaryBody("photo", getImageFromNetByUrl(fileUrl));

    	HttpEntity multipart = builder.build();
    	uploadFile.setEntity(multipart);
    	CloseableHttpResponse response = httpClient.execute(uploadFile);
    	HttpEntity responseEntity = response.getEntity();
    	return //EntityUtils.toString(
    			response.getStatusLine() + "";
    			//);
    }
    
	public HttpPost getHttpPost() {
		return this.post;
	}
	public void setGetEntitiesHeader() {
		
		//this.post.setHeader("Cookie", Data.cookie());
		//this.post.setHeader("Origin", "https://ingress.com");
		//this.post.setHeader("Referer", "https://ingress.com/intel");
		//this.post.setHeader("x-csrftoken", Data.csrftoken);
	}
}
