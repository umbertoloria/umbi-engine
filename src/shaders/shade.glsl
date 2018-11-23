#version 150

in vec3 position;
out vec3 color;
uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main() {
    gl_Position = vec4(position, 1) * transformationMatrix * projectionMatrix * viewMatrix;
    color = vec3(1,0,0);
}


// ---___---___--- //


#version 150 core

in vec3 color;

void main() {
    gl_FragColor = vec4(color, 1);
}