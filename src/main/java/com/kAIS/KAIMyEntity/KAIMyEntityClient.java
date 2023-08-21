package com.kAIS.KAIMyEntity;

import com.kAIS.KAIMyEntity.register.KAIMyEntityRegisterClient;
import com.kAIS.KAIMyEntity.renderer.MMDAnimManager;
import com.kAIS.KAIMyEntity.renderer.MMDModelManager;
import com.kAIS.KAIMyEntity.renderer.MMDTextureManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.stream.Collectors;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.Enumeration;
import java.util.ArrayList;
import java.security.CodeSource;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Vector3f;

public class KAIMyEntityClient implements ClientModInitializer {
    public static final Logger logger = LogManager.getLogger();
    public static int usingMMDShader = 1;
    public static boolean reloadProperties = false;
    static String gameDirectory = MinecraftClient.getInstance().runDirectory.getAbsolutePath();
    static String metaConfig = gameDirectory + "/mods/KAIMyEntity/meta.version";
    static final int BUFFER = 512;
    static final long TOOBIG = 0x6400000; // Max size of unzipped data, 100MB
    static final int TOOMANY = 1024;      // Max number of files
    //public static String[] debugStr = new String[10];

    @Override
    public void onInitializeClient() {
        logger.info("KAIMyEntity InitClient begin...");
        String metaVersion = readMetaVersion();
        String modVersion = getModVersion();
        if (!metaVersion.equals(modVersion)) {
            logger.info(String.format("Meta version is %s, mod version is %s, need update!", metaVersion, modVersion));
            updateMetaFile();
            writeMetaVersion(modVersion);
            logger.info("Meta file update finished!");
        } else {
            logger.info(String.format("Meta version is %s, mod version is %s, no update needed!", metaVersion, modVersion));
        }
        MMDModelManager.Init();
        MMDTextureManager.Init();
        MMDAnimManager.Init();
        KAIMyEntityRegisterClient.Register();
        logger.info("KAIMyEntity InitClient successful.");
    }

    private String getModVersion() {
        try {
            InputStream in = getClass().getResourceAsStream("/kaimyentity.meta.version");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            return reader.readLine();
        } catch (Exception e) {
            logger.error("Get mod version failed!");
        }
        return "unknown";
    }

    private static String readMetaVersion()
    {
        File metaConfigFile = new File(metaConfig);
        try {
            if (!metaConfigFile.exists()) {
                return "unknown";
            }
            FileReader fr = new FileReader(metaConfigFile);
            BufferedReader br = new BufferedReader(fr);
            String version = br.readLine();
            br.close();
            return version;
        } catch (IOException e) {
            logger.error("Read meta.version failed!");
            return "unknown";
        }
    }

    private static void writeMetaVersion(String verison)
    {
        File metaConfigFile = new File(metaConfig);
        try {
            if (!metaConfigFile.exists()) {
                metaConfigFile.createNewFile();
            }
            FileWriter fw = new FileWriter(metaConfigFile);
            fw.write(verison);
            fw.close();
        } catch (IOException e) {
            logger.error("Write meta.version failed!");
        }
    }

    private void resetFolder(String folder)
    {
        File folderFile = new File(folder);
        if (folderFile.exists()){
            folderFile.delete();
        }
        folderFile.mkdir();
    }

    private class ResetResourceTask {
        public String resourceDir;
        public String targetDir;
        public boolean clear;
    };

    private void resetDirFromResource(ArrayList<ResetResourceTask> tasks)
    {
        try{
            for (var task : tasks) {
                if (task.clear) {
                    resetFolder(task.targetDir);    
                }
            };

            CodeSource src = KAIMyEntityClient.class.getProtectionDomain().getCodeSource();
            if(src == null) {
                return;
            }
            URL jar = src.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            while(true) {
                ZipEntry e = zip.getNextEntry();
                if (e == null) {
                    break;
                }
                if (e.isDirectory()) {
                    continue;
                }
                String path = e.getName();
                for (var task : tasks) {
                    if (!path.startsWith(task.resourceDir)) {
                        continue;
                    }
                    logger.info("Copy " + path + " to " + task.targetDir);
                    // get file name of path
                    String name = path.substring(task.resourceDir.length());
                    File file = new File(task.targetDir + "/" + name);
                    FileUtils.copyURLToFile(getClass().getResource("/" + path), file, 30000, 30000);
                }
            }
        } catch (IOException e) {
            logger.error(String.format("Reset meta folder failed, %s", e.toString()));
        }
    }

    private void updateMetaFile()
    {
        try {
            String entityPath = gameDirectory + "/mods/KAIMyEntity";
            File KAIMyEntityFolder = new File(entityPath);
            if (!KAIMyEntityFolder.exists()) {
                logger.info("KAIMyEntity folder not found, recreate it!");
                KAIMyEntityFolder.mkdir();
            }

            String assetsPath = "assets/kaimyentity";
            ArrayList<ResetResourceTask> tasks = new ArrayList<ResetResourceTask>();
            tasks.add(new ResetResourceTask(){{
                resourceDir = assetsPath + "/anime/";
                targetDir = entityPath + "/DefaultAnime";
                clear = true;
            }});
            tasks.add(new ResetResourceTask(){{
                resourceDir = assetsPath + "/shader/";
                targetDir = entityPath + "/DefaultShader";
                clear = true;
            }});
            tasks.add(new ResetResourceTask(){{
                resourceDir = assetsPath + "/entityplayer.template/";
                targetDir = entityPath + "/EntityPlayer";
                clear = true;
            }});

            if (NativeFunc.isWindows) {
                tasks.add(new ResetResourceTask(){{
                    resourceDir = assetsPath + "/lib/windows/";
                    targetDir = entityPath + "/lib";
                    clear = true;
                }});
            } else if (NativeFunc.isLinux) {
                tasks.add(new ResetResourceTask(){{
                    resourceDir = assetsPath + "/lib/linux/";
                    targetDir = entityPath + "/lib";
                    clear = true;
                }});
            } else if (NativeFunc.isAndroid) {
                tasks.add(new ResetResourceTask(){{
                    resourceDir = assetsPath + "/lib/android/";
                    targetDir = entityPath + "/lib";
                    clear = false;
                }});
            }
            resetDirFromResource(tasks);
        } catch (Exception e) {
            logger.error("Failed to update meta file, " + e.toString());
        }
    }

    public static String calledFrom(int i){
        StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
        if (steArray.length <= i) {
            return "";
        }
        return steArray[i].getClassName();
    }

    public static Vector3f str2Vec3f(String arg){
        Vector3f vector3f = new Vector3f();
        String[] splittedStr = arg.split(",");
        if (splittedStr.length != 3){
            return new Vector3f(0.0f);
        }
        vector3f.x = Float.valueOf(splittedStr[0]);
        vector3f.y = Float.valueOf(splittedStr[1]);
        vector3f.z = Float.valueOf(splittedStr[2]);
        return vector3f;
    }
    
    public static void drawText(String arg, int x, int y){
        MinecraftClient MCinstance = MinecraftClient.getInstance();
        MatrixStack mat;
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        mat = RenderSystem.getModelViewStack();
        mat.push();
        //instance.textRenderer.draw(mat, arg, x, y, -1);
        mat.pop();
    }
}
