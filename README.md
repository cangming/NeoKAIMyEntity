# NeoKAIMyEntity

## Acknowledgement
It's a fork version from KAIMyEntity and KAIMyEntity-C which let you to replace the Steve with customize MMD model.
Thanks for the authors for their contribution to the original projects.
- kjkjkAIStudio
- asuka-mio
- Gengorou-C

## How to use

### What you need to prepare
* All files needed except for Minecraft fabric, fabric api and model file are builtin in the mod jar file.

#### indispensable
* Minecraft fabric loader and fabric api (Need download)
* [this mod](https://github.com/cangming/NeoKAIMyEntity/releases) (Need download)
* 3D model (PMX or PMD)
* [KAIMyEntitySaba.dll](https://github.com/Gengorou-C/KAIMyEntitySaba/releases/tag/20221215)

#### almost indispensable

* [default VMD files](https://github.com/Gengorou-C/KAIMyEntity-C/releases/tag/requiredFiles)
* [lightMap.png](https://github.com/Gengorou-C/KAIMyEntity-C/releases/tag/requiredFiles)

#### recommended

* dedicated VMD files for each 3D models
* [model.properties](https://github.com/Gengorou-C/KAIMyEntity-C/releases/tag/requiredFiles)

### Installation

1. Download appropriate jar file according to your minecraft fabric version, and put it in mods folder.  
    - [This mod jar](https://github.com/cangming/NeoKAIMyEntity/releases)
    - [fabric api](https://github.com/FabricMC/fabric/releases)
2. Run minecraft.  
3. KAIMyEntity folder in mods folder will be created and override folders in KAIMyEntity show as following if meta version mismatch, which record in meta.version. You should place custom vmd and shader file in your EntityPlayer_cangming folder which mention later.
    - DefaultAnime
    - DefaultShader
    - lib
    - EntityPlayer
4. Open KAIMyEntity folder, and copy and paste EntityPlayer folder.  
5. Rename the copied EntityPlayer folder "EntityPlayer_(YourName)", e.g. "EntityPlayer_cangming"  
7. Put 3D model files in EntityPlayer_(YourName) folder. If you want to customize motion or sharder, also put them in.
8. Rename the 3D model file "model.pmx" (or "model.pmd").  
9. Select world, and start the game.

### Exmaple of directory tree

```bash
.
├── config
├── logs
├── mods
│   │──NeoKAIMyEntity.jar
│   └── KAIMyEntity
|       ├── DefaultAnime
|       │   └── default VMD files
|       ├── DefaultShader
|       │   ├── MMDShader.fsh
|       │   └── MMDShader.vsh
|       ├── EntityPlayer
|       │   ├── lightMap.png
|       │   ├── model.properties
|       ├── EntityPlayer_(Player Name)
|       │   ├── Texture files
|       │   ├── dedicated VMD files
|       │   ├── dedicated shader files (MMDShader.fsh, MMDShader.vsh)
|       │   ├── lightMap.png
|       │   ├── model.properties
|       │   └── model.pmx (or model.pmd)
|       └── (entity ID) (e.g. minecraft.horse)
|           ├── Texture files
|           ├── dedicated VMD files
|           ├── lightMap.png
|           └── model.pmx (or model.pmd)
├── saves
├── shaderpacks
└── ...
```

## Motion list

### Player

* idle.vmd
* walk.vmd
* sprint.vmd
* sneak.vmd
* swingRight.vmd
* swingLeft.vmd
* fall.vmd      (Drop from high position)
* elytraFly.vmd (Use elytra)
* fly.vmd       (Fly in create mode or jump down)
* flyHover.vmd  (Fly hover in create mode)
* jump.vmd      (Jump motion before get into the highest point)
* swim.vmd
* onClimbable.vmd
* onClimbableUp.vmd
* onClimbableDown.vmd
* sleep.vmd
* ride.vmd
* die.vmd
* custom_[1-4].vmd
* itemActive_[itemName]\_[Left or Right]_[using or swinging].vmd  
(e.g. itemActive_minecraft.shield_Left_using.vmd)  
(dedicated motion for each items)
* onHorse.vmd
* crawl.vmd
* lieDown.vmd

### entity

* idle.vmd
* walk.vmd
* swim.vmd
* ridden.vmd
* driven.vmd

## others

* If you want to change model size, or item angle, you need to edit model.properties.  
* Presss 0 to change to default shader, if you want to use other shader plugin like Iris. Default is MMD shader.
* Issues
    * Please creating issue on issue page if you have problem, I will try to look for possible solution.
    * You can just send pull request to me if you have some interest idea, or want to fix some annoying problem. 
    * I will focus to fix issue and maintain release with mainline fabric version now. Forge and old fabric version may be possible but not guarantee.
    * There are several default motion need to be redesign, which are just copy from other motion now. You may find some resource from other package and take them as your custom motion. But if you have design want to share with people, don't hesitate to send PR.
        * fall.vmd
        * fly.vmd
        * flyHover.vmd
        * jump.vmd
