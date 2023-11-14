package cn.aireco.platform.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.alibaba.fastjson.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class HttpUtils {

    public HttpUtils() {
    }

    public static String request(String url) {
        return request(url, "GET");
    }

    public static String request(String url, boolean replaceSpace) {
        return request(url, "GET", (Map)null, replaceSpace);
    }

    public static String request(String url, String requestType) {
        return request(url, requestType, (Map)null);
    }

    public static String request(String url, String requestType, Map<String, String> header) {
        return request(url, requestType, header, true);
    }

    public static String request(String url, String requestType, Map<String, String> header, boolean replaceSpace) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("request url can't be null");
        } else {
            StringBuilder sb = new StringBuilder();
            InputStream raw = null;
            BufferedInputStream reader = null;

            try {
                URL httpUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection)httpUrl.openConnection();
                connection.setRequestMethod(requestType.toUpperCase());
                if (header != null) {
                    Iterator iterator = header.entrySet().iterator();

                    while(iterator.hasNext()) {
                        Entry<String, String> item = (Entry)iterator.next();
                        connection.setRequestProperty((String)item.getKey(), (String)item.getValue());
                    }
                }

                raw = connection.getInputStream();
                reader = new BufferedInputStream(raw);
                byte[] buffer = new byte[1024];
                String encoding = connection.getContentEncoding();
                if (StringUtils.isEmpty(encoding)) {
                    encoding = "UTF-8";
                } else {
                    boolean support = Charset.isSupported(encoding);
                    if (!support) {
                        encoding = "UTF-8";
                    }
                }

                Charset charset = Charset.forName(encoding);
                boolean var12 = false;

                int size;
                while((size = reader.read(buffer, 0, buffer.length)) != -1) {
                    if (size > 0) {
                        sb.append(new String(buffer, 0, size, charset));
                    }
                }

                String result = sb.toString();
                String var14 = replaceSpace && result.indexOf("\n") != -1 ? result.replaceAll("\\s*|\t|\r|\n", "") : result;
                return var14;
            } catch (IOException var18) {
            } finally {
                IOUtils.close(reader);
                IOUtils.close(raw);
            }

            return "";
        }
    }

    public static String post(String url) {
        return request(url, "POST");
    }

    public static String post(String url, Map<String, String> header) {
        return request(url, "POST", header);
    }

    public static String post(String url, Map<String, String> header, String argument) {
        return post(url, header, argument, true);
    }

    public static String post(String url, Map<String, String> header, String argument, boolean replaceSpace) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("post url can't be null");
        } else {
            StringBuilder sb = new StringBuilder();
            InputStream raw = null;
            BufferedInputStream reader = null;
            OutputStream write = null;

            try {
                URL httpUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection)httpUrl.openConnection();
                connection.setRequestMethod("POST");
                if (header != null) {
                    Iterator iterator = header.entrySet().iterator();

                    while(iterator.hasNext()) {
                        Entry<String, String> item = (Entry)iterator.next();
                        connection.setRequestProperty((String)item.getKey(), (String)item.getValue());
                    }
                }

                if (StringUtils.isNotEmpty(argument)) {
                    connection.setDoOutput(true);
                    write = connection.getOutputStream();
                    write.write(argument.getBytes());
                }

                raw = connection.getInputStream();
                reader = new BufferedInputStream(raw);
                byte[] buffer = new byte[1024];
                String encoding = connection.getContentEncoding();
                if (StringUtils.isEmpty(encoding)) {
                    encoding = "UTF-8";
                } else {
                    boolean support = Charset.isSupported(encoding);
                    if (!support) {
                        encoding = "UTF-8";
                    }
                }

                Charset charset = Charset.forName(encoding);
                boolean var13 = false;

                int size;
                while((size = reader.read(buffer, 0, buffer.length)) != -1) {
                    if (size > 0) {
                        sb.append(new String(buffer, 0, size, charset));
                    }
                }

                String result = sb.toString();
                String var15 = replaceSpace && result.indexOf("\n") != -1 ? result.replaceAll("\\s*|\t|\r|\n", "") : result;
                return var15;
            } catch (IOException var19) {
                var19.printStackTrace();
            } finally {
                IOUtils.close(reader);
                IOUtils.close(raw);
                IOUtils.close(write);
            }

            return "";
        }
    }

    public static String post(String url, Map<String, String> header, InputStream in, boolean replaceSpace) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("post url can't be null");
        } else {
            StringBuilder sb = new StringBuilder();
            InputStream raw = null;
            BufferedInputStream reader = null;
            OutputStream write = null;

            try {
                URL httpUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection)httpUrl.openConnection();
                connection.setRequestMethod("POST");
                if (header != null) {
                    Iterator iterator = header.entrySet().iterator();

                    while(iterator.hasNext()) {
                        Entry<String, String> item = (Entry)iterator.next();
                        connection.setRequestProperty((String)item.getKey(), (String)item.getValue());
                    }
                }

                byte[] buffer;
                if (in != null) {
                    connection.setDoOutput(true);
                    write = connection.getOutputStream();
                    buffer = new byte[1024];
                    boolean var22 = false;

                    int size;
                    while((size = in.read(buffer, 0, buffer.length)) != -1) {
                        if (size > 0) {
                            write.write(buffer, 0, size);
                        }
                    }
                }

                raw = connection.getInputStream();
                reader = new BufferedInputStream(raw);
                buffer = new byte[1024];
                String encoding = connection.getContentEncoding();
                if (StringUtils.isEmpty(encoding)) {
                    encoding = "UTF-8";
                } else {
                    boolean support = Charset.isSupported(encoding);
                    if (!support) {
                        encoding = "UTF-8";
                    }
                }

                Charset charset = Charset.forName(encoding);
                boolean var13 = false;

                int size;
                while((size = reader.read(buffer, 0, buffer.length)) != -1) {
                    if (size > 0) {
                        sb.append(new String(buffer, 0, size, charset));
                    }
                }

                String result = sb.toString();
                String var15 = replaceSpace && result.indexOf("\n") != -1 ? result.replaceAll("\\s*|\t|\r|\n", "") : result;
                return var15;
            } catch (IOException var19) {
            } finally {
                IOUtils.close(reader);
                IOUtils.close(raw);
                IOUtils.close(write);
            }

            return "";
        }
    }

    public static String getJsonRequest(HttpServletRequest request) throws IOException {
        String charset = request.getCharacterEncoding();
        if (StringUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }

        StringBuilder sb = new StringBuilder();

        try {
            InputStream raw = request.getInputStream();
            Throwable var4 = null;

            try {
                BufferedInputStream reader = new BufferedInputStream(raw);
                Throwable var6 = null;

                try {
                    byte[] buffer = new byte[1024];
                    boolean var8 = false;

                    int size;
                    while((size = reader.read(buffer, 0, buffer.length)) != -1) {
                        if (size > 0) {
                            sb.append(new String(buffer, 0, size, charset));
                        }
                    }
                } catch (Throwable var32) {
                    var6 = var32;
                    throw var32;
                } finally {
                    if (reader != null) {
                        if (var6 != null) {
                            try {
                                reader.close();
                            } catch (Throwable var31) {
                                var6.addSuppressed(var31);
                            }
                        } else {
                            reader.close();
                        }
                    }

                }
            } catch (Throwable var34) {
                var4 = var34;
                throw var34;
            } finally {
                if (raw != null) {
                    if (var4 != null) {
                        try {
                            raw.close();
                        } catch (Throwable var30) {
                            var4.addSuppressed(var30);
                        }
                    } else {
                        raw.close();
                    }
                }

            }
        } catch (IOException var36) {
            throw new IOException(var36);
        }

        String result = sb.toString();
        return result;
    }
}

