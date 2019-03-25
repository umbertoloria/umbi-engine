#version 330 core

layout (location = 0) in vec3 in_position;
layout (location = 2) in vec3 in_normal;

out vec3 normal;
out vec3 frag_pos;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main() {
    frag_pos = vec3(model * vec4(in_position, 1.0));
    normal = mat3(transpose(inverse(model))) * in_normal;
    gl_Position = projection * view * vec4(frag_pos, 1.0);
}

// ---___---___--- //

#version 330 core

layout (location = 0) out vec4 color;

in vec3 normal;
in vec3 frag_pos;

uniform vec3 light_position;
uniform vec4 light_color;
uniform vec4 object_color;

void main() {

    // ambient
    float ambientStrength = 0.2;
    vec3 ambient = ambientStrength * light_color.xyz;

    // diffuse
    vec3 norm = normalize(normal);
    vec3 lightDir = normalize(light_position - frag_pos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = diff * light_color.xyz;

    // specular
    float specularStrength = 1;
    vec3 viewDir = normalize(light_position - frag_pos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32);
    vec3 specular = specularStrength * spec * light_color.xyz;

    vec3 result = (ambient + diffuse + specular) * object_color.xyz;
    color = vec4(result, 1.0);
}
