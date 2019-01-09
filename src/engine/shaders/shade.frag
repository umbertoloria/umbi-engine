#version 330 core

layout (location = 0) in vec4 in_position;
layout (location = 2) in vec3 in_normal;

uniform mat4 pr_matrix;
uniform mat4 vw_matrix;
uniform mat4 ml_matrix;

out vec3 normal;

void main() {
    gl_Position = pr_matrix * vw_matrix * ml_matrix * in_position;
    normal = in_normal; // vec4(pr_matrix * vw_matrix * ml_matrix * vec4(in_normal, 0)).xyz;
}

// ---___---___--- //

#version 330 core

layout (location = 0) out vec4 color;

in vec3 normal;

void main() {
    //color = cl * clamp(dot(-vec3(0,0,1), normal), 0, 1);
    color = vec4(normal, 1);
}
