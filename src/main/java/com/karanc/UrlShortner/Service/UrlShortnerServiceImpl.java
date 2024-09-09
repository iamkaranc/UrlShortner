package com.karanc.UrlShortner.Service;

import com.karanc.UrlShortner.Entity.Url;
import com.karanc.UrlShortner.Json.ErrorJson;
import com.karanc.UrlShortner.Json.ResponseJson;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService{

   public ResponseJson returnErrorResponse(String errorCode, String errorMessage) {
       ResponseJson responseJson = new ResponseJson();
       ErrorJson errorJson = new ErrorJson();
       errorJson.setErrorCode(errorCode);
       errorJson.setErrorMessage(errorMessage);
       responseJson.setError(errorJson);
       return responseJson;
   }

   @Override
   public ResponseJson generateUrl(Url url) {
       ResponseJson responseJson = new ResponseJson();
       if(url != null) {
           String longUrl = url.getLongUrl();
           if(url.getCustomAlias() != null) {
               url.setShortUrl(url.getCustomAlias());
           } else {
               url.setShortUrl(generateCustomAlias(15));
           }
           List<String> timeAccessed = new ArrayList<>();
           timeAccessed.add(Calendar.getInstance().getTime().toString());
           url.setTimeAccessed(timeAccessed);
           responseJson.setUrl(url);
       }
       return responseJson;
   }

   private String generateCustomAlias(int length) {
       String generatedString = "";
       boolean useLetters = true;
       boolean useNumbers = true;
       generatedString += RandomStringUtils.random(length, useLetters, useNumbers);
       return generatedString;
   }

}
