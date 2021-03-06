#version 330 core

layout (location = 0) in vec3 in_position;
//layout (location = 1) in vec2 in_texture_coordinates;

uniform mat4 model;
uniform mat4 projectionView;

void main() {
    gl_Position = projectionView * model * vec4(in_position, 1.0);
}

// ---___---___--- //

#version 330 core

layout (location = 0) out vec4 out_color;

uniform vec4 color;

void main() {
    out_color = color;
}
