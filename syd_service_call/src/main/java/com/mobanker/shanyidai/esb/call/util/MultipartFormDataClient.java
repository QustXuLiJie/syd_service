package com.mobanker.shanyidai.esb.call.util;

import org.apache.http.conn.ssl.X509HostnameVerifier;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 管黎明
 *         <p>
 *         创建时间:2015年10月14日
 */
public class MultipartFormDataClient {
    private final String urlStr;

    private String charset = DEFAULT_CHARSET;
    /**
     * 默认编码
     **/
    public static final String DEFAULT_CHARSET = "UTF-8";

    private static final String BOUNDARY = "-----qiqi777458";
    private static final String BOUNDARY2 = "--" + BOUNDARY;
    private static final String NEXT_LINE = "\r\n";
    private static final String END_STRING = NEXT_LINE + BOUNDARY2 + "--";

    public MultipartFormDataClient(String url) {
        this.urlStr = url;
    }

    /**
     * 进行MultipartFormData请求
     *
     * @param strParams            文字参数
     * @param files                文件参数
     * @param containChineseParams 是否包含中文参数
     * @return
     * @throws Exception
     */
    public String doPost(Map<String, String> strParams, List<MultipartFormDataFile> files, boolean containChineseParams)
            throws Exception {
        URL url = new URL(containChineseParams ? urlStr : urlStr + this.createLinkString(strParams, true));
        HttpURLConnection hc = (HttpURLConnection) url.openConnection();
        hc.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        hc.setRequestProperty("Charsert", charset);
        hc.setDoOutput(true);
        hc.setDoInput(true);
        hc.setConnectTimeout(100000);// 连接超时单位毫秒 //
        hc.setReadTimeout(200000);// 读取超时 单位毫秒
        hc.setUseCaches(false);
        hc.setRequestMethod("POST");
        hc.connect();

        OutputStream out = hc.getOutputStream();

        // 通过multipart传递普通参数，对方不会去获取
        if (containChineseParams) {
            StringBuilder strSb = new StringBuilder();
            if (strParams != null && !strParams.isEmpty()) {
                for (Entry<String, String> mapEntry : strParams.entrySet()) {
                    strSb.append(BOUNDARY2).append(NEXT_LINE);
                    strSb.append("Content-Disposition: form-data; name=\"").append(mapEntry.getKey()).append("\"")
                            .append(NEXT_LINE);
                    strSb.append("Content-Type: text/html; charset=").append(charset).append(NEXT_LINE);
                    strSb.append("Content-Transfer-Encoding: 8bit").append(NEXT_LINE);
                    strSb.append(NEXT_LINE);
                    strSb.append(mapEntry.getValue()).append(NEXT_LINE);
                }
            }
            out.write(strSb.toString().getBytes(charset));
        }

        StringBuilder fileSb = new StringBuilder();
        if (!(files == null || files.isEmpty())) {
            for (MultipartFormDataFile file : files) {
                if (file == null) {
                    continue;
                }
                fileSb.append(BOUNDARY2).append(NEXT_LINE);
                fileSb.append("Content-Disposition: form-data; name=\"").append(file.getFieldName()).append("\"; ")
                        .append("filename=\"").append(file.getFileName()).append("\"").append(NEXT_LINE);
                fileSb.append("Content-Type:").append(file.getContentType()).append(NEXT_LINE);
                fileSb.append("Content-Transfer-Encoding: binary").append(NEXT_LINE);
                fileSb.append(NEXT_LINE);
                out.write(fileSb.toString().getBytes(charset));

                DataInputStream in = new DataInputStream(file.getFileStream());
                int bytes = 0;
                byte[] bufferOut = new byte[1024];
                while ((bytes = in.read(bufferOut)) != -1) {
                    out.write(bufferOut, 0, bytes);
                }
                in.close();
            }
        } else {
            if (out != null) {
                out.close();
            }
            throw new RuntimeException("没有文件流需要发送!");
        }
        out.write(END_STRING.getBytes(charset));
        out.close();

        InputStream is = hc.getInputStream();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bos.write(ch);
        }
        hc.disconnect();
        return new String(bos.toByteArray(), charset);
    }


    /**
     * 进行MultipartFormData请求
     *
     * @param strParams            文字参数
     * @param files                文件参数
     * @param containChineseParams 是否包含中文参数
     * @return
     * @throws Exception
     */
    public String doPostForHttps(Map<String, String> strParams, List<MultipartFormDataFile> files, boolean containChineseParams)
            throws Exception {

        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);

        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
            @Override
            public void verify(String host, SSLSocket ssl) throws IOException {
            }

            @Override
            public void verify(String host, X509Certificate cert)
                    throws SSLException {
            }

            @Override
            public void verify(String host, String[] cns, String[] subjectAlts)
                    throws SSLException {
            }

            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };

        URL url = new URL(containChineseParams ? urlStr : urlStr + this.createLinkString(strParams, true));
        HttpsURLConnection hc = (HttpsURLConnection) url.openConnection();
        hc.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        hc.setRequestProperty("Charsert", charset);
        hc.setDoOutput(true);
        hc.setDoInput(true);
        hc.setConnectTimeout(100000);// 连接超时单位毫秒 //
        hc.setReadTimeout(200000);// 读取超时 单位毫秒
        hc.setUseCaches(false);
        hc.setRequestMethod("POST");
        hc.setSSLSocketFactory(ctx.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        hc.connect();

        OutputStream out = hc.getOutputStream();

        // 通过multipart传递普通参数，对方不会去获取
        if (containChineseParams) {
            StringBuilder strSb = new StringBuilder();
            if (strParams != null && !strParams.isEmpty()) {
                for (Entry<String, String> mapEntry : strParams.entrySet()) {
                    strSb.append(BOUNDARY2).append(NEXT_LINE);
                    strSb.append("Content-Disposition: form-data; name=\"").append(mapEntry.getKey()).append("\"")
                            .append(NEXT_LINE);
                    strSb.append("Content-Type: text/html; charset=").append(charset).append(NEXT_LINE);
                    strSb.append("Content-Transfer-Encoding: 8bit").append(NEXT_LINE);
                    strSb.append(NEXT_LINE);
                    strSb.append(mapEntry.getValue()).append(NEXT_LINE);
                }
            }
            out.write(strSb.toString().getBytes(charset));
        }

        StringBuilder fileSb = new StringBuilder();
        if (!(files == null || files.isEmpty())) {
            for (MultipartFormDataFile file : files) {
                fileSb.append(BOUNDARY2).append(NEXT_LINE);
                fileSb.append("Content-Disposition: form-data; name=\"").append(file.getFieldName()).append("\"; ")
                        .append("filename=\"").append(file.getFileName()).append("\"").append(NEXT_LINE);
                fileSb.append("Content-Type:").append(file.getContentType()).append(NEXT_LINE);
                fileSb.append("Content-Transfer-Encoding: binary").append(NEXT_LINE);
                fileSb.append(NEXT_LINE);
                out.write(fileSb.toString().getBytes(charset));

                DataInputStream in = new DataInputStream(file.getFileStream());
                int bytes = 0;
                byte[] bufferOut = new byte[1024];
                while ((bytes = in.read(bufferOut)) != -1) {
                    out.write(bufferOut, 0, bytes);
                }
                in.close();
            }
        } else {
            if (out != null) {
                out.close();
            }
            throw new RuntimeException("没有文件流需要发送!");
        }
        out.write(END_STRING.getBytes(charset));
        out.close();

        InputStream is = hc.getInputStream();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bos.write(ch);
        }
        hc.disconnect();
        return new String(bos.toByteArray(), charset);
    }

    /**
     * map.put("a","bc"); map.put("d","ef"); 返回 接字符串 :?a=bc&d=ef
     *
     * @param params 参数map
     * @param encode 是否进行url编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createLinkString(Map<String, String> params, boolean encode)
            throws UnsupportedEncodingException {
        if (params == null || params.isEmpty()) {
            return "";
        }
        // String str="";
        String charset = params.get("encoding") == null ? DEFAULT_CHARSET : params.get("encoding");

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuffer sb = new StringBuffer();
        sb.append("?");
        for (String key : keys) {
            String value = params.get(key);
            if (value == null) {
                continue;
            }
            String encodedValue = null;
            if (encode) {
                encodedValue = URLEncoder.encode(value, charset);
            }
            sb.append(key).append("=").append(encodedValue).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
