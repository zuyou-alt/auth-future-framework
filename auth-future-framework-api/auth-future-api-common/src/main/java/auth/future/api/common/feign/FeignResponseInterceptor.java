package auth.future.api.common.feign;

import auth.future.component.common.constant.HttpStatus;
import auth.future.component.common.exception.FeignRequestException;
import auth.future.component.common.model.ApiResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
/**
 * feign调用响应拦截器
 * @author hzy
 * @since 2024-03-09
 **/
@Component
public class FeignResponseInterceptor extends SpringDecoder {

    Logger log = LoggerFactory.getLogger(FeignResponseInterceptor.class);

    public FeignResponseInterceptor(ObjectFactory<HttpMessageConverters> messageConverters, ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        super(messageConverters, customizers);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        ApiResult<?> apiResult = this.checkResponse(response);
        Object data = apiResult.data();
        return super.decode(response.toBuilder().body(JSON.toJSONString(data), StandardCharsets.UTF_8).build(), type);
    }
    private ApiResult<?> checkResponse(Response response) {
        Response.Body body = response.body();
        ApiResult<?> apiResult;
        String bodyString;
        try {
            bodyString = IOUtils.toString(body.asReader(StandardCharsets.UTF_8));
            apiResult = JSON.parseObject(bodyString, ApiResult.class);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new FeignRequestException("远程调用异常！"+ e.getMessage());
        }
        Integer state = apiResult.state();
        if (HttpStatus.SUCCESS!=state){
            String url = response.request().url();
            JSONObject jsonObject = JSONObject.parseObject(bodyString);
            jsonObject.put("remoteUrl",url);
            throw new FeignRequestException(jsonObject.toJSONString());
        }
        return apiResult;
    }
}
