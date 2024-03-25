package auth.future.component.authentication.cors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "auth.component.common.cors.allowed")
public class CorsAllowed {

    private boolean credentials = true;

    private List<String> headers;

    private List<String> methods;

    private List<String> origins;

    public boolean isCredentials() {
        return credentials;
    }

    public void setCredentials(boolean credentials) {
        this.credentials = credentials;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }
}
