#version 140

in vec2 texCoord;
in vec4 vertexColor;

uniform sampler2D Sampler0;

out vec4 color;

void main(){
	color = texture(Sampler0, texCoord) * vertexColor;
}
