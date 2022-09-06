# Forge バージョン

## 1.はじめに

僕はOpenGLなんかチンプンカンプンで、なんならJavaのコードに触れたのもほぼ初めてです。  
ですので、バクが無い保証やサポートは大変厳しいです。  
というか、Forgeに移植とはいえ、そんなに大したことやってないです。  
あとOptiFineと競合する可能性があります。  

(追伸)  
ちょっと手間がかかりますけれども、不安定ですが影の描画が可能になりました。Canary版として出しています。  
[CanaryのReadMe](https://github.com/Gengorou-C/KAIMyEntity-forge/blob/1.19-forge-Canary/README_Canary.md)

## 2.使い方

### 必要なもの

* このMODのJarファイル
* 使いたい3Dモデル(pmxもしくはpmd)
* KAIMyEntitySaba.dll
* MMDShader.fsh, MMDShader.vsh
* VMDファイル
* (推奨)lightMap.png
* (必要なら)model.properties

以上のファイルを下記のように配置します。

```bash
.
├── config
├── KAIMyEntity
│   ├── DefaultAnim
│   │   └── (vmdファイル達)
│   ├── EntityPlayer
│   │   ├── (テクスチャのファイルとかフォルダとか)
│   │   ├── (あるのであればモデル専用のvmdファイル)
│   │   ├── (あるのであれば)lightMap.png
│   │   └── model.pmx(または model.pmd)
│   ├── EntityPlayer_(Your Name)
│   │   ├── (テクスチャのファイルとかフォルダとか)
│   │   ├── (あるのであればモデル専用のvmdファイル)
│   │   ├── (あるのであれば)lightMap.png
│   │   ├── (あるのであれば)model.properties
│   │   └── model.pmx(または model.pmd)
│   ├── (":"を"."に変換したエンティティのID)(ファルダ名の例「minecraft.villager」)
│   │   ├── (テクスチャのファイルとかフォルダとか)
│   │   ├── (5つのvmdファイル)
│   │   ├── (あるのであれば)lightMap.png
│   │   └── model.pmx(または model.pmd)
│   └── Shader
│       ├── MMDShader.fsh
│       └── MMDShader.vsh
├── logs
├── mods
│   └──KAIMyEntity.jar
├── saves
├── shaderpacks
├── KAIMyEntitySaba.dll
└── (その他のファイル)
```

## 3.機能

* エンティティのモデルを変更できます。

### 対応モーション

### Player

* 棒立ち(idle.vmd)
* 歩行(walk.vmd)
* スプリント(sprint.vmd)
* スニーク(sneak.vmd)
* 右腕でアイテム使用(swingRight.vmd)
* 左腕でアイテム使用(swingLeft.vmd)
* エリトラでの飛行(elytraFly.vmd)
* 水泳(swim.vmd)
* はしごなどの上り下り(onClimbable.vmd)
* 睡眠(sleep.vmd)
* 騎乗(ride.vmd)
* 死亡(die.vmd)
* 任意のタイミングで再生機能なモーション4つ(custom_[1-4].vmd)
* 特定のアイテムを特定の腕で使ったときのモーション(itemActive_[itemName]_[left or right].vmd)  
(例：itemActive_minecraft.iron_sword_right.vmd)

### other

* 棒立ち(idle.vmd)
* 歩行(walk.vmd)
* 水泳(swim.vmd)
* 乗せる(ridden.vmd)
* 乗せた状態での移動(driven.vmd)

## その他

* アイテムを持った時、使った時の角度を少し弄れるようにしました。また、モデルのサイズを変更できるようにしました。model.pmxと同じフォルダにmodel.propertiesを置いてください。書き方はReleaseに置いてあるファイルやソースコードを参考にしてください。
* 明るさに応じてモデルの色が変化するようになりました。画像が必須なのでmodel.pmxと同じフォルダにlightMap.pngを入れてください。
* MMDShader.vshとMMDShader.fshに少し変更を加えています。
