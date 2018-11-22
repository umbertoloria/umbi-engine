#version 150
in vec3 position;
out vec3 color;
void main(void) {
    gl_Position = vec4(position, 1.0);
    color = vec3(position.x + 0.5, 0.0, position.y + 0.5);
}


// ---___---___--- //


#version 150 core
in vec3 color;
out vec4 out_Color;
void main(void) {
    out_Color = vec4(color, 1.0);
}
