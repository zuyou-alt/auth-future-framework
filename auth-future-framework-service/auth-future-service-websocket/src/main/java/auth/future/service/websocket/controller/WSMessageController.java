package auth.future.service.websocket.controller;

import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.socket.WSMessageInfo;
import auth.future.service.websocket.service.business.BusinessWSMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * websocket消息发送管理
 * @author hzy
 * @since 2024-03-01
 **/
@Tag(name = "WS消息管理")
@RestController
@RequestMapping("/ws")
public class WSMessageController {

    @Resource
    private BusinessWSMessageService businessWSMessageService;

    @Operation(summary = "发送消息")
    @PostMapping("/sendMessage")
    public ApiResult<Object> sendMessage(@RequestBody WSMessageInfo wsMessageInfo){
        businessWSMessageService.sendMessage(wsMessageInfo);
        return ApiResult.success("发送成功！");
    }
}
