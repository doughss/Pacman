package br.ufrpe.pixengine.core.fx;

public class Pixel {
	public static final int WHITE = 0xffffffff;
	public static final int RED = 0xffff0000;
	public static final int GREEN = 0xff00ff00;
	public static final int BLUE = 0xff0000ff;
	public static final int CYAN = 0xff00ffff;
	public static final int PINK = 0xffff00ff;
	public static final int YELLOW = 0xffffff00;

	private static final int[] colors = new int[] { WHITE, RED, GREEN, BLUE, CYAN, PINK, YELLOW };

	public static int getRandomColor() {
		return colors[(int) (Math.random() * (colors.length - 1))];
	}

	public static double getAlpha(int color) {
		return (0xff & (color >> 24)) / 255.0;
	}

	public static double getRed(int color) {
		return (0xff & (color >> 16)) / 255.0;
	}

	public static double getGreen(int color) {
		return (0xff & (color >> 8)) / 255.0;
	}

	public static double getBlue(int color) {
		return (0xff & (color)) / 255.0;
	}

	public static int getColor(double a, double r, double g, double b) {
		return ((int) (a * 255f + 0.5f) << 24 | (int) (r * 255f + 0.5f) << 16 | (int) (g * 255f + 0.5f) << 8
				| (int) (b * 255f + 0.5f));
	}

	public static int getColorPower(int color, float power) {
		return getColor(1, getRed(color) * power, getGreen(color) * power, getBlue(color) * power);
	}

	public static int getMax(int c0, int c1) {
		return getColor(1, Math.max(getRed(c0), getRed(c1)), Math.max(getGreen(c0), getGreen(c1)),
				Math.max(getBlue(c0), getBlue(c1)));
	}
}
