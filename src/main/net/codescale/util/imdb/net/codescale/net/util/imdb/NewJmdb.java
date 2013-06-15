package net.codescale.util.imdb.net.codescale.net.util.imdb;

import HTTPClient.*;
import net.codescale.util.imdb.Movie;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: char0n
 * Date: 6/15/13
 * Time: 3:05 PM
 */


public class NewJmdb {

    protected static final Logger log = Logger.getLogger(NewJmdb.class);
    protected static final HashMap<String, String> defaultConnectionConfig = new HashMap<String, String>();
    protected static final String host = "http://www.imdb.com/";
    static {
        defaultConnectionConfig.put("timeout", "2000");
        defaultConnectionConfig.put("user_agent", "JMDB client");
        defaultConnectionConfig.put("referer", "http://www.imdb.com/");
    }


    public static Set<Movie> search(String query) throws UnsupportedEncodingException {
        return search(query, defaultConnectionConfig);
    }

    public static Set<Movie> search(String query, HashMap<String, String> connectionConfig) throws UnsupportedEncodingException {
        Set<Movie> movies = new HashSet<Movie>();
        String url = host + "find?tt=on&mx=20&q=" + URLEncoder.encode(query, "UTF-8");
        String content = httpRequest(url, connectionConfig);
        if (content == null) {
            return movies;
        }
        return movies;
    }

    protected static String httpRequest(String url) {
        return httpRequest(url, defaultConnectionConfig);
    }

    protected static String httpRequest(String url, HashMap<String, String> config) {
        String content = null;
        HTTPConnection connection = null;

        try {
            URL requestURL = new URL(url);
            String host = requestURL.getHost();
            String file = requestURL.getFile();
            int port = requestURL.getPort();
            port = (port == -1) ? 80 : port;

            log.debug("Getting HTTP response for: "+url);
            connection = new HTTPConnection(host, port);
            connection.setTimeout(Integer.parseInt(config.get("timeout")));
            connection.setDefaultHeaders(new NVPair[] {new NVPair("User-Agent", config.get("user_agent")),
                                         new NVPair("Referer", config.get("referer"))});
            connection.setAllowUserInteraction(false);
            connection.removeModule(CookieModule.class);
            HTTPResponse resp = connection.Get(file);
            if (resp.getStatusCode() == 200) {
                content = resp.getText();
            }
        } catch (Exception ex) {
            log.error(String.format("Error while requesting url %s", url), ex);
        } finally {
            if (connection != null) connection.stop();
        }
        return content;
    }
}
