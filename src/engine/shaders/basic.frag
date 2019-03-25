#version 330 core

layout (location = 0) in vec3 in_position;
layout (location = 1) in vec2 in_texture_coordinates;

out vec2 texture_coordinates;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main() {
    texture_coordinates = vec2(in_texture_coordinates.x, in_texture_coordinates.y);
    gl_Position = projection * view * model * vec4(in_position, 1.0);
}

// ---___---___--- //

#version 330 core

layout (location = 0) out vec4 color;

in vec2 texture_coordinates;

uniform sampler2D texture_channel;
uniform vec2 texture_scale;
uniform vec2 texture_offset;

void main() {
    color = texture(texture_channel, (texture_coordinates + texture_offset) / texture_scale);
    if (color.w == 0) {
        discard;
    }
}
