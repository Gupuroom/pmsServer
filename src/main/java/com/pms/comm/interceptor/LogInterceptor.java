package com.pms.comm.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.comm.log.APILog;
import com.pms.comm.log.ERRLog;
import com.pms.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    // Success Return은 postHandle에서 Log 처리
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        ContentCachingResponseWrapper responseWrapper = getResponseWrapper(response);
        ResponseDTO responseDTO = getResponseBody(responseWrapper);

        if (responseDTO.getCode() == 200)
            APILog.info(request, responseDTO.getCode(), responseDTO.getCodeMsg());
    }

    // Error Return은 afterCompletion Log 처리
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContentCachingResponseWrapper responseWrapper = getResponseWrapper(response);
        ResponseDTO responseDTO = getResponseBody(responseWrapper);

        // Exception이 발생하면 postHandle을 타지 않는다.
        if (responseDTO.getCode() != 200) // Service 단 Error Log 처리
            ERRLog.info(request, responseDTO.getCode(), responseDTO.getCodeMsg());
    }


    private ResponseDTO getResponseBody(ContentCachingResponseWrapper responseWrapper) throws IOException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(responseWrapper.getContentAsByteArray(), ResponseDTO.class);
    }

    private ContentCachingResponseWrapper getResponseWrapper(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        }
        return new ContentCachingResponseWrapper(response);
    }
}