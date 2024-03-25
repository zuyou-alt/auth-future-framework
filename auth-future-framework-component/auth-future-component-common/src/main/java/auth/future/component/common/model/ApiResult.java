package auth.future.component.common.model;

/**
 * 全局响应体
 * @author hzy
 * @since 2023-08-20
 **/
public record ApiResult<T> (Integer state, T data, String message) {
    /**
     * 响应成功方法
     * @param code 响应状态码
     * @param data 响应数据
     * @param message 响应信息
     * @return 响应对象
     * @param <T> 响应数据泛型
     */
    public static <T> ApiResult<T> success(Integer code,T data, String message){
        return new ApiResult<>(code,data,message);
    }

    public static <T> ApiResult<T> success(T data, String message){
        return new ApiResult<>(200,data,message);
    }

    public static <T> ApiResult<T> success(String message){
        return new ApiResult<>(200,null ,message);
    }

    public static <T> ApiResult<T> success(){
        return new ApiResult<>(200,null,"success");
    }

    public static <T> ApiResult<T> fail(Integer code, String message){
        return new ApiResult<>(code,null,message);
    }

    public static <T> ApiResult<T> fail(String message){
        return new ApiResult<>(500,null,message);
    }
}


