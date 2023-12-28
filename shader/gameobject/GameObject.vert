#version 400 core

layout(location = 0) in vec2 aPosition;
layout(location = 1) in vec2 aTexcoord;

layout(std140) uniform CameraTransform {
    mat4 projMat;
    mat4 viewMat;
};

uniform mat4 modelMat;
// uniform ModelTransform { mat4 model; };

out vec2 vTexcoord;

void main() {
    vTexcoord = aTexcoord;
    gl_Position = projMat * (viewMat * (modelMat * vec4(aPosition, 1.0, 1.0)));
}