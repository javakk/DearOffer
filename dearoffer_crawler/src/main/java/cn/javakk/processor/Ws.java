package cn.javakk.processor;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: JavaKK
 * @Date: 2019/4/19 17:05
 */
public class Ws {

    private static String url = "http://sapwsd50.sapdns.cn:8350/dir/wsdl?p=sa/5a06a1ae70a7385ebbc1b77b83e8d4db";

    private static String cookie = "saplb_*=(NWAPID01_DP1_02)24851750; MYSAPSSO2=AjExMDAgAA9wb3J0YWw6V1NUX1VTRVKIAAdkZWZhdWx0AQAIV1NUX1VTRVICAAMwMDADAANEUDEEAAwyMDE5MDQyMjAxNDcFAAQAAAAICgAIV1NUX1VTRVL%2FAQUwggEBBgkqhkiG9w0BBwKggfMwgfACAQExCzAJBgUrDgMCGgUAMAsGCSqGSIb3DQEHATGB0DCBzQIBATAiMB0xDDAKBgNVBAMTA0RQMTENMAsGA1UECxMESjJFRQIBADAJBgUrDgMCGgUAoF0wGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEHATAcBgkqhkiG9w0BCQUxDxcNMTkwNDIyMDE0NzE2WjAjBgkqhkiG9w0BCQQxFgQU5xpNe5pthnLIKVuKlk2VVbDeewgwCQYHKoZIzjgEAwQvMC0CFQCoF0zW!KX2vKHuj3YSFNb2QgmK7QIUEBkthdCeE2lvvJC38gWb4bTjbUE%3D; JSESSIONID=B0blrWxxInS-7K9ERvyyqPEM1blCagEmNXsB_SAPAa7H7z_Yql3pKp3el-Fpw0iV; JSESSIONMARKID=DnnwDAeCd0iRiRiVutxjQ2DTiVXUjtgX3YTiY1ewE";


    private static Pattern p =  Pattern.compile("(http://)+((\\w)+\\.)((\\w)+\\.)+(cn:8350/)+(\\S+)");

    public static void main(String[] args) throws Exception {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        request.addHeader("Cookie", cookie);
        HttpResponse response = client.execute(request);


        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String strResult = EntityUtils.toString(response.getEntity());
            String host = getHost(strResult);
            setSysClipboardText("#" + url + "\n" + host);
            System.out.println("Complete!");
        }

    }

    public static String getHost(String url){
        if(url==null||url.trim().equals("")){
            return "";
        }
        String host = "";

        Matcher matcher = p.matcher(url);
        if(matcher.find()){
            host = matcher.group();
        }
        return host.substring(0, host.length() - 1);
    }

    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }
}
