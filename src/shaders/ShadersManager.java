package shaders;

public class ShadersManager {

	public static final StaticShader shade = new StaticShader("shade");

	public static StaticShader get(String tag) throws IllegalArgumentException {
		if (tag.equals("shade")) {
			return shade;
		}
		throw new IllegalArgumentException("Shader not found.");
	}

}
