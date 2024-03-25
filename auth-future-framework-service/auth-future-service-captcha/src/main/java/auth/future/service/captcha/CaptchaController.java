package auth.future.service.captcha;

import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.spring.application.ImageCaptchaApplication;
import cloud.tianai.captcha.spring.vo.CaptchaResponse;
import cloud.tianai.captcha.spring.vo.ImageCaptchaVO;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author hzy
 * @since 2023-09-26
 **/
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Resource
    private ImageCaptchaApplication imageCaptchaApplication;

    @RequestMapping("/generate")
    public CaptchaResponse<ImageCaptchaVO> genCaptcha(@RequestParam(value = "type", required = false)String type) {
        if (StringUtils.isBlank(type)) {
            type = CaptchaTypeConstant.SLIDER;
        }
        if ("RANDOM".equals(type)) {
            int i = ThreadLocalRandom.current().nextInt(0, 4);
            if (i == 0) {
                type = CaptchaTypeConstant.SLIDER;
            } else if (i == 1) {
                type = CaptchaTypeConstant.CONCAT;
            } else if (i == 2) {
                type = CaptchaTypeConstant.ROTATE;
            } else{
                type = CaptchaTypeConstant.WORD_IMAGE_CLICK;
            }
        }
        return imageCaptchaApplication.generateCaptcha(type);
    }
}
