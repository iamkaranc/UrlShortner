package com.karanc.UrlShortner.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karanc.UrlShortner.Entity.Url;
import com.karanc.UrlShortner.Json.ResponseJson;
import com.karanc.UrlShortner.Service.UrlShortnerService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/url/shortner")
public class UrlShortnerController {

    public static final Gson gson = new GsonBuilder().setDateFormat("dd/mm/yyyy HH:mm:ss").create();

//    @Autowired
//    TTLCache ttlCache;

    Map<String, Url> globalMap = new HashMap<>();
    @Autowired
    private UrlShortnerService urlShortnerService;

    @PostMapping(value = "/generate/url")
    public ResponseEntity<String> generateUrl(@RequestBody String body) {
        Url url = null;
        ResponseJson responseJson = null;
        try {
            url = gson.fromJson(body, Url.class);
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(urlShortnerService.returnErrorResponse("101", "Unable to parse request body")), HttpStatusCode.valueOf(500));
        }

        try {
                if(url != null) {
                    String longUrl = url.getLongUrl();
                    List<String> timeAccessed = new ArrayList<>();
                    if(globalMap.get(longUrl) != null) {
                        url = globalMap.get(longUrl);
                        if(url.getTimeAccessed() != null) {
                            timeAccessed = url.getTimeAccessed();
                            timeAccessed.add(Calendar.getInstance().getTime().toString());
                            url.setTimeAccessed(timeAccessed);
                        } else {
                            timeAccessed.add(Calendar.getInstance().getTime().toString());
                            url.setTimeAccessed(timeAccessed);
                        }
                    }
                }
                responseJson = urlShortnerService.generateUrl(url);
                if(responseJson.getError() == null) {
                    //Success part
                    globalMap.put(url.getShortUrl(), url);
                    //ttlCache.put(url.getShortUrl(), url, url.getTtlSeconds() != null  ? Long.valueOf(url.getTtlSeconds()) : 120);
                    return new ResponseEntity<>(gson.toJson(responseJson), HttpStatusCode.valueOf(200));

                } else {
                    //Failure part
                    return new ResponseEntity<>(gson.toJson(responseJson), HttpStatusCode.valueOf(200));
                }



        } catch(Exception e){
            return new ResponseEntity<>(gson.toJson(urlShortnerService.returnErrorResponse("500", "Internal Server Error")), HttpStatusCode.valueOf(500));

        }

    }

    @GetMapping(value = "/get/url")
    public ResponseEntity<String> getUrl(@RequestParam String alias) {
        try {

            Url url = redirect(alias);

            assert url != null;
            //return new ResponseEntity<>(url.getLongUrl(), HttpStatusCode.valueOf(302));

            return ResponseEntity.status(HttpStatusCode.valueOf(302)).location(URI.create(url.getLongUrl())).build();




        } catch(Exception e){
            //return new ResponseEntity<>(gson.toJson(urlShortnerService.returnErrorResponse("500", "Internal Server Error")), HttpStatusCode.valueOf(500));
            return null;
        }

    }

    private Url redirect(String alias) {
        if(StringUtils.isNotEmpty(alias)) {
            Url url = globalMap.get(alias);
            if(url != null) {
                return url;
            }

        }
        return null;
    }

    @GetMapping(value = "/get/url/analytics")
    public ResponseEntity<String> getUrlAnalytics(@RequestParam String alias) {
        try {

            Url url = redirect(alias);
            return new ResponseEntity<>(gson.toJson(url), HttpStatusCode.valueOf(200));




        } catch(Exception e){
            return new ResponseEntity<>(gson.toJson(urlShortnerService.returnErrorResponse("500", "Internal Server Error")), HttpStatusCode.valueOf(500));

        }

    }

//    @Scheduled(fixedRate = 60000)
//    public void removeTTL() {
//        if(!globalMap.isEmpty()) {
//            for(Url url: globalMap.values()) {
//               globalMap.remove(url);
//
//            }
//        }
//    }


}
