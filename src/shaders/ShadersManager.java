package shaders;

public class ShadersManager {

	public static final Shader shade = new StaticShader("shade");

	public static Shader get(String tag) throws IllegalArgumentException {
		if (tag.equals("shade")) {
			return shade;
		}
		throw new IllegalArgumentException("Shader not found.");
	}

}
