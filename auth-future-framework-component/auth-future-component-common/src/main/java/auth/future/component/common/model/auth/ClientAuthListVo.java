package auth.future.component.common.model.auth;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author hzy
 * @since 2023-09-29
 **/
public class ClientAuthListVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String clientId;

    private String clientName;
    /**
     * 客户唯一标识
     */
    private String clientKey;

    /**
     * 授权范围，多个范围用逗号分隔
     */
    private String scopes;

    List<ClientRoleVo>  clientRoleVoList;

    Object clientSaResourceVoList;

    public Object getClientSaResourceVoList() {
        return clientSaResourceVoList;
    }

    public void setClientSaResourceVoList(Object clientSaResourceVoList) {
        this.clientSaResourceVoList = clientSaResourceVoList;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public List<ClientRoleVo> getClientRoleVoList() {
        return clientRoleVoList;
    }

    public void setClientRoleVoList(List<ClientRoleVo> clientRoleVoList) {
        this.clientRoleVoList = clientRoleVoList;
    }


   public static class ClientRoleVo implements Serializable{

       @Serial
       private static final long serialVersionUID = 1L;

        private String roleId;

        private String roleName;

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }




}
