package auth.future.component.common.commonenum;

import auth.future.component.common.exception.IdentityException;
import cn.hutool.core.util.StrUtil;
/**
 * 用户身份枚举
 * 10000普通用户、01000系统管理员、00100安全保密员、00010安全审计员
 */
public enum IdentityEnum {
    DEFAULT_USER("10000","普通用户"),
    SYS_ADMIN("01000","系统管理员"),
    SEC_ADMIN("00100","安全保密员"),
    AUD_ADMIN("00010","安全审计员");

    private final String identity;

    private final String des;

    IdentityEnum(String identity, String des) {
        this.identity = identity;
        this.des = des;
    }
    public static boolean isDefaultUser(String identity){
       return verify(IdentityEnum.DEFAULT_USER,identity);
    }
    public static boolean isSysAdmin(String identity){
        return verify(IdentityEnum.SYS_ADMIN,identity);
    }
    public static boolean isSecAdmin(String identity){
        return verify(IdentityEnum.SEC_ADMIN,identity);
    }
    public static boolean isAudAdmin(String identity){
        return verify(IdentityEnum.AUD_ADMIN,identity);
    }

    /**
     * 判断用户身份
     * 1. 身份标识必须有五位
     * 2. 首位为1则为普通用户
     * @param identity  身份标识
     * @return 判断结果 true 包含普通用户身份  false 不包含普通用户身份
     */
    private static boolean verify(IdentityEnum identityEnum, String identity){
        if (StrUtil.isBlank(identity) || identity.length()<5){
            throw new IdentityException("用户身份不合法！");
        }
        String substring;
        String identityFlag;
        if (IdentityEnum.DEFAULT_USER.equals(identityEnum)){
            substring = identityEnum.getIdentity().substring(0,1);
            identityFlag = identity.substring(0,1);
        }else  if (IdentityEnum.SYS_ADMIN.equals(identityEnum)){
            substring = identityEnum.getIdentity().substring(1,2);
            identityFlag = identity.substring(1, 2);
        }else if (IdentityEnum.SEC_ADMIN.equals(identityEnum)){
            substring = identityEnum.getIdentity().substring(2, 3);
            identityFlag = identity.substring(2, 3);
        }else if (IdentityEnum.AUD_ADMIN.equals(identityEnum)){
            substring = identityEnum.getIdentity().substring(3, 4);
            identityFlag = identity.substring(3, 4);
        }else {
            return false;
        }
        return substring.equals(identityFlag);
    }


    public String getIdentity() {
        return identity;
    }

    public String getDes() {
        return des;
    }

    public static void main(String[] args) {
        System.out.println(IdentityEnum.isDefaultUser("1000"));
    }
}
