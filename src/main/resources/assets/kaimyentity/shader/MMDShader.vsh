#version 140

in vec3 Position;
in vec3 Normal;
in vec2 UV0;
in ivec2 UV2;

uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec2 texCoord;
out vec4 vertexColor;

void main(){
    texCoord = UV0;
    vertexColor = texture(Sampler2, clamp((UV2 / 256.0), vec2(0.5/16.0), vec2(15.0/16.0))) * vec4(1.0, 1.0, 1.0, 1.0);
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
}
