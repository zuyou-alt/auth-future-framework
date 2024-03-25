package auth.future.component.common.model;

/**
 * 全局响应体
 * @author hzy
 * @since 2023-08-20
 **/
public record ApiPageResponse<T> (Integer state, T Data,String message, Long currentPage,Long size,Long total) {
    /**
     * 响应成功方法
     * @param code 响应状态码
     * @param data 响应数据
     * @param message 响应信息
     * @param currentPage 当前页码
     * @param size 每页条数
     * @param total 总数
     * @return 响应对象
     * @param <T> 响应数据泛型
     */
    public static <T> ApiPageResponse<T> success(Integer code, T data, String message,Long currentPage,Long size,Long total){
        return new ApiPageResponse<>(code,data,message,currentPage,size,total);
    }

    public static <T> ApiPageResponse<T> success(T data, String message,Long currentPage,Long size,Long total){
        return new ApiPageResponse<>(200,data,message,currentPage,size,total);
    }

    public static <T> ApiPageResponse<T> success(String message,Long currentPage,Long size,Long total){
        return new ApiPageResponse<>(200,null,message,currentPage,size,total);
    }

    public static <T> ApiPageResponse<T> success(Long currentPage,Long size,Long total){
        return new ApiPageResponse<>(200,null,"success",currentPage,size,total);
    }

    public static <T> ApiPageResponse<T> fail(Integer code, String message){
        return new ApiPageResponse<>(code,null,message,null,null,null);
    }

    public static <T> ApiPageResponse<T> fail(String message){
        return new ApiPageResponse<>(500,null,message,null,null,null);
    }
}


