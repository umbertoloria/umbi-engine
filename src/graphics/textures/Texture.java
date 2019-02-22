package graphics.textures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Texture {

	private static int newtSlot = 0;

	public static Texture mario, sprites;

	public static void loadAll() {
		mario = new Texture("mario.png");
		sprites = new Texture("mariomaker.png");
	}

	public static void cleanAll() {
		mario.destroy();
		sprites.destroy();
	}

	private int texture, slot;
	private int width, height;

	public Texture(String file) {
		int[] pixels;
		try {
			BufferedImage image = ImageIO.read(Texture.class.getResourceAsStream(file));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		int[] data = new int[width * height];
		int transparency = convertARGBtoABGR(pixels[0]);
		data[0] = 0;
		for (int i = 1; i < width * height; i++) {
			data[i] = convertARGBtoABGR(pixels[i]);
			if (data[i] == transparency) {
				data[i] = 0;
			}
		}
//		for (int i = 0; i < width * height; i++) {
//			data[i] = convertARGBtoABGR(pixels[i]);
//		}
		texture = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, texture);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		glBindTexture(GL_TEXTURE_2D, 0);
		slot = newtSlot++;
	}

	private int convertARGBtoABGR(int color) {
		int a = (color & 0xff000000) >> 24;
		int r = (color & 0xff0000) >> 16;
		int g = (color & 0xff00) >> 8;
		int b = (color & 0xff);
		return a << 24 | b << 16 | g << 8 | r;
	}

	public void bind() {
		glActiveTexture(GL_TEXTURE0 + slot);
		glBindTexture(GL_TEXTURE_2D, texture);
	}

	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	private void destroy() {
		glDeleteTextures(texture);
	}

	public int getSlot() {
		return slot;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
