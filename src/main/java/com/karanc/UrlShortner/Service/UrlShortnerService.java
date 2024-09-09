package com.karanc.UrlShortner.Service;

import com.karanc.UrlShortner.Entity.Url;
import com.karanc.UrlShortner.Json.ResponseJson;
import org.springframework.stereotype.Service;

public interface UrlShortnerService {

    ResponseJson returnErrorResponse(String errorCode, String errorMessage);

    ResponseJson generateUrl(Url url);

}
