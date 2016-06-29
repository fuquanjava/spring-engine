package captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICaptchaManager {
    void genCaptcha(HttpServletRequest request, HttpServletResponse response);
}
