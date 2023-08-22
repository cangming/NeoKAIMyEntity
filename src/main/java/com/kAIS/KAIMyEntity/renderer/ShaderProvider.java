package com.kAIS.KAIMyEntity.renderer;

import com.kAIS.KAIMyEntity.KAIMyEntityClient;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.opengl.GL46C;

import java.io.FileInputStream;
import java.io.File;

public class ShaderProvider {
    private static boolean isInited = false;
    private static int program = 0;
    private static MinecraftClient MCinstance = MinecraftClient.getInstance();
    private static final String defaultVertexPath = MCinstance.runDirectory.getAbsolutePath() + "/mods/KAIMyEntity/DefaultShader/MMDShader.vsh";
    private static final String defaultFragPath = MCinstance.runDirectory.getAbsolutePath() + "/mods/KAIMyEntity/DefaultShader/MMDShader.fsh";
    private static final String vertexPath = MCinstance.runDirectory.getAbsolutePath() + "/mods/KAIMyEntity/EntityPlayer_" + MCinstance.player.getName().getString() + "/MMDShader.vsh";
    private static final String fragPath = MCinstance.runDirectory.getAbsolutePath() + "/mods/KAIMyEntity/EntityPlayer_" + MCinstance.player.getName().getString() + "/MMDShader.fsh";

    public static void Init() {
        if (!isInited) {
            try {
                int vertexShader = GL46C.glCreateShader(GL46C.GL_VERTEX_SHADER);
                File fVertex = new File(vertexPath);
                try (FileInputStream vertexSource = new FileInputStream(fVertex.exists() ? vertexPath : defaultVertexPath)) {
                    GL46C.glShaderSource(vertexShader, new String(vertexSource.readAllBytes()));
                } catch (Exception e) {
                    KAIMyEntityClient.logger.error("Vertex shader load fail, " + e.getMessage());
                }

                int fragShader = GL46C.glCreateShader(GL46C.GL_FRAGMENT_SHADER);
                File fFrag = new File(fragPath);
                try (FileInputStream fragSource = new FileInputStream(fFrag.exists() ? fragPath : defaultFragPath)) {
                    GL46C.glShaderSource(fragShader, new String(fragSource.readAllBytes()));
                } catch (Exception e) {
                    KAIMyEntityClient.logger.error("Frag shader load fail, " + e.getMessage());
                }

                GL46C.glCompileShader(vertexShader);
                if (GL46C.glGetShaderi(vertexShader, GL46C.GL_COMPILE_STATUS) == GL46C.GL_FALSE) {
                    String log = GL46C.glGetShaderInfoLog(vertexShader, 8192).trim();
                    KAIMyEntityClient.logger.error("Failed to compile shader {}", log);
                    GL46C.glDeleteShader(vertexShader);
                }

                GL46C.glCompileShader(fragShader);
                if (GL46C.glGetShaderi(fragShader, GL46C.GL_COMPILE_STATUS) == GL46C.GL_FALSE) {
                    String log = GL46C.glGetShaderInfoLog(fragShader, 8192).trim();
                    KAIMyEntityClient.logger.error("Failed to compile shader {}", log);
                    GL46C.glDeleteShader(fragShader);
                }
                program = GL46C.glCreateProgram();
                GL46C.glAttachShader(program, vertexShader);
                GL46C.glAttachShader(program, fragShader);
                GL46C.glLinkProgram(program);
                if (GL46C.glGetProgrami(program, GL46C.GL_LINK_STATUS) == GL46C.GL_FALSE) {
                    String log = GL46C.glGetProgramInfoLog(program, 8192);
                    KAIMyEntityClient.logger.error("Failed to link shader program\n{}", log);
                    GL46C.glDeleteProgram(program);
                    program = 0;
                }
                KAIMyEntityClient.logger.info("MMD Shader Initialize finished");
            } catch (Exception e) {
                e.printStackTrace();
            }
            isInited = true;
        }
    }

    public static int getProgram() {
        if (program <= 0)
            throw new Error("Call Shader before init");
        return program;
    }
}