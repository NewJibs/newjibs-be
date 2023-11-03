package com.ssafy.newjibs.api.house;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class HouseApi {
    @Value("${open-api.service-key}")
    private String serviceKey;

    public URL getHouseDealUrl(int regionCode, int dealYmd) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + regionCode);
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + dealYmd);
        return new URL(urlBuilder.toString());
    }

    public URL getHouseInfoUrl(int pageNo, int numOfRows, int regionCode, int dealYmd) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows);
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + regionCode);
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + dealYmd);
        return new URL(urlBuilder.toString());
    }
}
