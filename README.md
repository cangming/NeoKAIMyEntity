# NeoKAIMyEntity

## Acknowledgement
It's a fork version from KAIMyEntity and KAIMyEntity-C which let you to replace the Steve with customize MMD model.
Thanks for the authors for their contribution to the original projects.
- kjkjkAIStudio
- asuka-mio
- Gengorou-C

## How to use

### What you need to prepare

#### indispensable
* Minecraft fabric mod
* [this mod](https://github.com/Gengorou-C/KAIMyEntity-C/releases)
* 3D model (PMX or PMD)
* [KAIMyEntitySaba.dll](https://github.com/Gengorou-C/KAIMyEntitySaba/releases/tag/20221215)
* [MMDShader.fsh, MMDShader.vsh](https://github.com/Gengorou-C/KAIMyEntity-C/releases/tag/requiredFiles)

#### almost indispensable

* [default VMD files](https://github.com/Gengorou-C/KAIMyEntity-C/releases/tag/requiredFiles)
* [lightMap.png](https://github.com/Gengorou-C/KAIMyEntity-C/releases/tag/requiredFiles)

#### recommended

* dedicated VMD files for each 3D models
* [model.properties](https://github.com/Gengorou-C/KAIMyEntity-C/releases/tag/requiredFiles)

### Installation

(1) Download appropriate jar file, and put it in mods folder.  
(2) Run minecraft.  
(3) If KAIMyEntity folder does't exist in Game directory, this mod will download a ZIP file and extract it.  
(4) If KAIMyEntitySaba.dll does't exist in Game directory, it will be downloaded.  
(5) Open KAIMyEntity folder, and copy and paste EntityPlayer folder.  
(6) Rename the copied EntityPlayer folder "EntityPlayer_(YourName)".  
 (e.g.) "EntityPlayer_Gengorou-C"  
(7) Put 3D model files in EntityPlayer_(YourName) folder.  
(8) Rename the 3D model file "model.pmx" (or "model.pmd").  
(9) Select world, and start the game.

### Exmaple of directory tree

```bash
.
├── config
├── KAIMyEntity
│   ├── DefaultAnim
│   │   └── default VMD files
│   ├── EntityPlayer
│   │   ├── Texture files
│   │   ├── dedicated VMD files
│   │   ├── lightMap.png
│   │   ├── model.properties
│   │   └── model.pmx (or model.pmd)
│   ├── EntityPlayer_(Player Name)
│   │   ├── Texture files
│   │   ├── dedicated VMD files
│   │   ├── lightMap.png
│   │   ├── model.properties
│   │   └── model.pmx (or model.pmd)
│   ├── (entity ID) (e.g. minecraft.horse)
│   │   ├── Texture files
│   │   ├── dedicated VMD files
│   │   ├── lightMap.png
│   │   └── model.pmx (or model.pmd)
│   └── Shader
│       ├── MMDShader.fsh
│       └── MMDShader.vsh
├── logs
├── mods
│   └──KAIMyEntityC.jar
├── saves
├── shaderpacks
├── KAIMyEntitySaba.dll
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
* jump.vmd
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

