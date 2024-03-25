package auth.future.api.log.common;

import auth.future.api.log.LogServiceApi;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.component.common.model.auth.constant.SecurityConstants;
import cn.hutool.core.util.StrUtil;
import auth.future.api.log.common.func.LogContentCallback;
import auth.future.api.log.model.OperatorLogVo;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.utils.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import java.time.LocalDateTime;
import java.util.TimerTask;

public class LogFactory {
    /**
     * 记录操作日志信息
     *
     * @return 任务task
     */
    public static TimerTask recordOperatorLog(final String title, final String content, final int bizType, final Object target, final int result, final String sender) {
        return recordOperatorLog(title, () -> StrUtil.isEmpty(content)?title:content, bizType, target, result, sender);
    }

    public static TimerTask recordOperatorLog(final String title, LogContentCallback callback, final int bizType, final Object target, final int result, final String sender) {
        LoginUser loginUser = CurrentContext.getLoginUser();
        return new TimerTask() {
            @Override
            public void run() {
                OperatorLogVo operatorLogVo = new OperatorLogVo();
                operatorLogVo.setLoginId(loginUser.getLoginId());
                operatorLogVo.setUserId(loginUser.getUserId());
                operatorLogVo.setUserName(loginUser.getUserName());
                operatorLogVo.setUserAccount(loginUser.getUserAccount());
                operatorLogVo.setUserIdentity(loginUser.getIdentity());
                operatorLogVo.setOrgId(loginUser.getOrgId());
                operatorLogVo.setOrgName(loginUser.getOrgName());
                operatorLogVo.setOrgPath(loginUser.getOrgPath());
                operatorLogVo.setTitle(title);
                try {
                    String content = callback != null ? callback.getContent() : null;
                    operatorLogVo.setContent(content != null ? content : title);
                }
                catch (Exception e) {
                    operatorLogVo.setContent(String.format("获取日志内容出错：%1$s", e.getMessage()));
                }
                operatorLogVo.setRecordTime(LocalDateTime.now());
                if (target != null) {
                    if (target instanceof String) {
                        operatorLogVo.setTarget((String) target);
                    }else if (target instanceof String[] targetArr){
                        String collect = String.join(",", targetArr);
                        operatorLogVo.setTarget(collect);
                    }else {
                        operatorLogVo.setTarget(JSONObject.toJSONString(target));
                    }
                }
                operatorLogVo.setResult(result);
                operatorLogVo.setSender(sender);
                operatorLogVo.setRequestUrl("");
                operatorLogVo.setRequestParam("");
                operatorLogVo.setLocation(CurrentContext.get(SecurityConstants.LOGIN_IP)==null ?"" : CurrentContext.get(SecurityConstants.LOGIN_IP).toString());
                operatorLogVo.setAppKey(loginUser.getAppKey());
                operatorLogVo.setBizType(bizType);
                operatorLogVo.setRemark("");
                operatorLogVo.setDigest("");
                LogServiceApi bean = SpringUtil.getBean(LogServiceApi.class);
                bean.recordOperatorLog(operatorLogVo);
            }
        };
    }

}
