package beengine.api.util;

import lombok.ToString;

import javax.annotation.concurrent.Immutable;

@Immutable
@ToString
public final class Color {
	
	public static final Color WHITE = new Color(255, 255, 255);
	
	/** The red value of this colour. */
	public final int r;
	
	/** The green value of this colour. */
	public final int g;
	
	/** The blue value of this colour. */
	public final int b;

	/** The alpha (opacity) value of this colour. */
	public final int a;

	public Color(int r, int g, int b) {
		this(r, g, b, 0xff);
	}
	
	public Color(int r, int g, int b, int a) {
		this.r = r & 0xff;
		this.g = g & 0xff;
		this.b = b & 0xff;
		this.a = a & 0xff;
	}
	
	/**
	 * Mixes the supplied list of colours together to produce a result colour.
	 */
	public static Color mix (Color ...colors) {
		if (colors.length == 0) return new Color(0, 0, 0); // todo make constant
		if (colors.length == 1) return colors[0];
		Color color1 = colors[0];
		
		if (colors.length == 2) {
			Color c2 = colors[0];
			return new Color(
				(color1.r + c2.r) / 2,
				(color1.g + c2.g) / 2,
				(color1.b + c2.b) / 2,
				(color1.a + c2.a) / 2
			);
		}
		
		int a = color1.a;
		int r = color1.r;
		int g = color1.g;
		int b = color1.b;
		
		for (var color : colors) {
			a += color.a;
			r += color.r;
			g += color.g;
			b += color.b;
		}
		int len = colors.length + 1;
		
		return new Color(r / len, g / len, b / len, a / len);
	}

	/**
	 * Returns a Color from the supplied RGB colour code (24-bit)
	 */
	public static Color fromRGB (int code) {
		return new Color((code >> 16) & 0xff, (code >> 8) & 0xff, code & 0xff);
	}

	/**
	 * Returns a Color from the supplied ARGB colour code (32-bit)
	 */
	public static Color fromARGB (int code) {
		return new Color((code >> 16) & 0xff, (code >> 8) & 0xff, code & 0xff, (code >> 24) & 0xff);
	}
	
	/**
	 * Returns an ARGB 32-bit colour value.
	 */
	public int toARGB () {
		return (this.a << 24) | (this.r << 16) | (this.g << 8) | this.b;
	}

	/**
	 * Returns a Color from the supplied RGBA colour code (32-bit)
	 */
	public static Color fromRGBA (int c) {
		return new Color((c >> 24) & 0xff, (c >> 16) & 0xff, (c >> 8) & 0xff, c & 0xff);
	}

	/**
	 * Returns an RGBA unsigned 32-bit colour value.
	 */
	public int toRGBA () {
		return (this.r << 24) | (this.g << 16) | (this.b << 8) | this.a;
	}
	
	/**
	 * Returns a little-endian RGBA colour value.
	 */
	public int toABGR() {
		return (a << 24) | (b << 16) | (g << 8) | r;
	}
	
	/**
	 * @return Color
	 */
	public static Color fromABGR(int code){
		return new Color(code & 0xff, (code >> 8) & 0xff, (code >> 16) & 0xff, (code >> 24) & 0xff);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Color color = (Color) o;
		return a == color.a &&
			r == color.r &&
			g == color.g &&
			b == color.b;
	}
}