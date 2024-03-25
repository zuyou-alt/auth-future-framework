package auth.future.api.configcenter.model;

/**
 * @author hzy
 * @since 2023-09-21
 **/
public record PageConfigInfoVo(Long pageNo,Long size,String classifyId,String appKey,String name,String configKey,String content,Integer configEnable)  {

}
