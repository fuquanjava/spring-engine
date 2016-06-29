package captcha.impl;

import captcha.Captcha;
import captcha.ICaptchaManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CaptchaServlet extends HttpServlet implements ICaptchaManager {
    private static transient Logger log = LoggerFactory.getLogger(CaptchaServlet.class);

    private static final long serialVersionUID = 434538970985235749L;

    private final static String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";
    private static TransferQueue<Captcha> captchaQueue = null;

    private static ExecutorService executorService = null;

    private static CaptchaProducer producer = null;
    private CaptchaConsumer consumer = null;
    private static int waitTime = 100;
    private static int width = 78;
    private static int height = 32;
    private static int charCount = 4;

    public static void startPruducer() {
        if (null == captchaQueue) {
            captchaQueue = new LinkedTransferQueue<Captcha>();
            executorService = Executors.newSingleThreadExecutor();
            producer = new CaptchaProducer(captchaQueue, width, height,
                    charCount);
            executorService.execute(producer);
        }
    }

    public static void shutdownPruducer() {
        if (null != producer) {
            producer.setStop(true);
        }
        if (null != executorService) {
            executorService.shutdown();
        }
    }

    @Override
    public void init() throws ServletException {
        // 初始化方法
        log.info("Start to init the Captcha Consumer ...");
        consumer = new CaptchaConsumer(captchaQueue, waitTime, width, height,
                charCount);
    }

    /**
     * 根据sessionid 获取目前的验证码
     *
     * @param request
     * @return
     */
    public static String getCaptchaCode(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session
                || null == session.getAttribute(CAPTCHA_SESSION_KEY))
            return null;
        return (String) session.getAttribute(CAPTCHA_SESSION_KEY);
    }

    /**
     * 验证提交的验证码
     *
     * @param request
     * @param captchaCode
     * @return
     */
    public static boolean verifyCaptcha(HttpServletRequest request,
                                        String captchaCode) {
        HttpSession session = request.getSession(false);
        if (null != session
                && null != session.getAttribute(CAPTCHA_SESSION_KEY)) {
            String initCode = (String) session
                    .getAttribute(CAPTCHA_SESSION_KEY);
            if (initCode.equalsIgnoreCase(captchaCode)) {
                session.removeAttribute(CAPTCHA_SESSION_KEY);
                return true;
            }
        }
        return false;
    }

    @Override
    public void genCaptcha(HttpServletRequest request,
                           HttpServletResponse response) {
        HttpSession session = request.getSession();
        // 开始消费图片
        Captcha captcha = consumer.getCaptcha();
        if (null != captcha) {
            // 开始输出图片
            writeImageToRespone(response, captcha.getCaptchImage());
            session.setAttribute(CAPTCHA_SESSION_KEY, captcha.getCaptchCode());
        }
    }

    private void writeImageToRespone(HttpServletResponse response,
                                     BufferedImage buffImg) {
        ServletOutputStream sos = null;
        IIOImage ioImage = null;
        ImageWriter writer = null;
        ImageOutputStream ios = null;
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            sos = response.getOutputStream();
            // 使用新的生成图片方式
            // 声明一个虚拟图片源
            ioImage = new IIOImage(buffImg, null, null);
            // 获取输出流助手
            writer = (ImageWriter) ImageIO.getImageWritersByFormatName("jpeg")
                    .next();
            // 设置参数
            ImageWriteParam param = writer.getDefaultWriteParam();
            // 显式压缩
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.2f);
            // 生成图片输出流
            ios = ImageIO.createImageOutputStream(sos);
            // 设置输出流
            writer.setOutput(ios);
            // 输出图片
            writer.write(ioImage);
            sos.flush();
        } catch (Exception e) {
            log.error("write image error:" + e);
        } finally {
            if (null != writer) {
                writer.dispose();
                writer = null;
            }
            if (null != ioImage) {
                ioImage = null;
            }
            // 关闭图片输出流
            if (null != ios) {
                try {
                    ios.close();
                } catch (SocketException se) {
                } catch (IOException e) {
                    log.error("write image error:" + e);
                }
                ios = null;
            }
            if (null != sos) {
                try {
                    sos.close();
                } catch (IOException e) {
                    log.error("write image error:" + e);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        genCaptcha(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        genCaptcha(request, response);
    }

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        genCaptcha(request, response);
    }

    public static TransferQueue<Captcha> getCaptchaQueue() {
        return captchaQueue;
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static CaptchaProducer getProducer() {
        return producer;
    }
}
