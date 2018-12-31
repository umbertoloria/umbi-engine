package tests;

import buffers.Mesh;
import graphics.Color;
import graphics.Entity;
import layer.Layer;
import shaders.Shader;

import java.util.Random;

public class SampleLayer extends Layer {

	public void init() {
		Random r = new Random();
		// Generazione paesaggio per 3D
		float size = 20;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {

				Color c = new Color(.2f + .8f * x / size,
						.2f + .8f * y / size,
						.01f + .2f * r.nextFloat() + .8f * (x * y) / (size * size));

				Mesh mesh = Mesh.cube;
				if (r.nextBoolean()) {
					mesh = Mesh.sphere;
				} else if (r.nextBoolean()) {
					mesh = Mesh.cone;
				}

				Entity e = new Entity(mesh, Shader.basic_shader, c);
				e.setPosition(x * 3, 3 + 3 * r.nextInt(40), y * 3);
				e.setScale(2.999f);

				add(e);
			}
		}
	}

}
