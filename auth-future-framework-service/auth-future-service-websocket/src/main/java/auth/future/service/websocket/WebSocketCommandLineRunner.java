package auth.future.service.websocket;

import auth.future.component.websocket.ImServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author hzy
 * @since 2024-03-01
 **/
@Component
public class WebSocketCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ImServer.startServer(8907);
    }
}
