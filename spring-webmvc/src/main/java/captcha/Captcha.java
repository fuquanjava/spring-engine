package captcha;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Captcha implements Serializable{

	private BufferedImage captchImage = null;
	private String captchCode = null;

	public Captcha(BufferedImage captchImage, String captchCode) {
		this.captchImage = captchImage;
		this.captchCode = captchCode;
	}

	public BufferedImage getCaptchImage() {
		return captchImage;
	}

	public void setCaptchImage(BufferedImage captchImage) {
		this.captchImage = captchImage;
	}

	public String getCaptchCode() {
		return captchCode;
	}

	public void setCaptchCode(String captchCode) {
		this.captchCode = captchCode;
	}

}
