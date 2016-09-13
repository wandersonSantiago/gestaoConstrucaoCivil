package br.com.system.gestaoConstrucaoCivil.security;

import java.io.IOException;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class ContextProfileInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

	@Override
	public void initialize(ConfigurableWebApplicationContext ctx) {
	
		ConfigurableEnvironment environment = ctx.getEnvironment();
		// logic to decide active profiles
		Properties proper = new Properties();
		try {
			proper.load(getClass().getResourceAsStream("/conf/ambiente"));
			
			String profiles = proper.getProperty("profile").trim();
			environment.setActiveProfiles(profiles);
			
			enableSSL();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 private void enableSSL() {
	        TrustManager[] trustAllCerts = new TrustManager[]{
	            new X509TrustManager() {
	                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                    return null;
	                }
	 
	                public void checkClientTrusted(
	                        java.security.cert.X509Certificate[] certs, String authType) {
	                }
	 
	                public void checkServerTrusted(
	                        java.security.cert.X509Certificate[] certs, String authType) {
	                }
	            }
	        };
	 
	        try {
	            SSLContext sc = SSLContext.getInstance("SSL");
	            sc.init(null, trustAllCerts, new java.security.SecureRandom());
	            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        } catch (Exception e) {
	        }
	    }


}
