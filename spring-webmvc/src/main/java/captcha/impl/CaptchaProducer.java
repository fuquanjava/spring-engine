package captcha.impl;


import captcha.Captcha;

import java.util.concurrent.TransferQueue;

public class CaptchaProducer implements Runnable {
    private TransferQueue<Captcha> captchaQueue = null;
    private boolean stop = false;

    private int width;
    private int height;
    private int charCount;
    private int queueSize = 4096;

    public CaptchaProducer(TransferQueue<Captcha> captchaQueue, int width,
                           int height, int charCount) {
        this.captchaQueue = captchaQueue;
        this.width = width;
        this.height = height;
        this.charCount = charCount;
    }

    @Override
    public void run() {
        Captcha captcha = null;
        while (!stop) {
            // 生成图片
            captcha = CaptchaFactory.genImage(width, height, charCount);
            // 增加到队列
            if (!captchaQueue.tryTransfer(captcha)) {
                try {
                    if (captchaQueue.size() < queueSize) {
                        captchaQueue.put(captcha);
                    } else {
                        captchaQueue.transfer(captcha);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

}
