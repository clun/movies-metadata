package fr.clunven.docu.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

/**
 * From Image to Base64
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class ImageUtils {
	
	/** Expected format. */
	public static enum ImageType { png, jpg, jpeg };
	
	/**
	 * Remove constructor.
	 */
	private ImageUtils() {
	}

	/**
	 * Encode image to string
	 *
	 * @param image The image to encode
	 * @param type jpeg, bmp, ...
	 * @return encoded string
	 */
	public static String fromFileToBase64(InputStream is, ImageType type) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			// Reading
			BufferedImage image = ImageIO.read(is);
			// Resizing
			BufferedImage resizedImage = new BufferedImage(130, 180, image.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(image, 0, 0, 130, 180, null);
			g.dispose();
			// Write into outpustream
			ImageIO.write(resizedImage, type.toString(), bos);
			// Convert to base64
			return new String(Base64.getEncoder().encode(bos.toByteArray()));
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot convert image to base64", e);
		} finally {
			try {
				bos.close();
			} catch (IOException e) {}
		}
	}
	
	/**
	 * Convert image file to inline data to be diplay as HTML.
	 *
	 * @param is
	 * 		target image
	 * @param type
	 * 		image format
	 * @return
	 * 		inline expression
	 */
	public static String inlineImage(InputStream is, ImageType type) {
		return "data:image/"+ type.toString() + ";base64," + fromFileToBase64(is, type);
	}
	
	/**
	 * Decode string to image
	 * @param imageString The string to decode
	 * @return decoded image
	 */
	public static OutputStream fromBase64ToImage(String imageString, ImageType type) {
		ByteArrayInputStream bis = null;
		try {
			bis = new ByteArrayInputStream(Base64.getDecoder().decode(imageString));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(ImageIO.read(bis),type.toString(), baos);
			return baos;
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot convert image to base64", e);
		} finally {
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {}
			}
		}
	}
}