# TextComponents [Adventure Edition]

A simple [TextComponents](https://github.com/TubMC/TextComponents) implementation that provides support for [Adventure](https://docs.advntr.dev/index.html)

## Why?

I understand how pointless this seems, making a text implementation work on text implementation built on other text implementations is frankly dumb. However, this implementation reduces the amount of repeat work I will have to complete to support adventure compatible server software.

## Implementation Specific Methods

The following methods are added to all IComponents in this implementation:

| Name     | Parameters    | Return Type   | Function                                          |
|----------|---------------|---------------|---------------------------------------------------|
|asComponent||[Component](https://github.com/KyoriPowered/adventure/blob/main/4/api/src/main/java/net/kyori/adventure/text/Component.java)|Calls `toAdventure`, provided for the interface [ComponentLike](https://github.com/KyoriPowered/adventure/blob/main/4/api/src/main/java/net/kyori/adventure/text/ComponentLike.java)|
|toAdventure||[Component](https://github.com/KyoriPowered/adventure/blob/main/4/api/src/main/java/net/kyori/adventure/text/Component.java)|Unwraps the component into it's adventure counterpart|
|sendTo|[Audience](https://github.com/KyoriPowered/adventure/blob/main/4/api/src/main/java/net/kyori/adventure/audience/Audience.java)||Sends the message onto the audience's chat|
|sendAsActionBarTo|[Audience](https://github.com/KyoriPowered/adventure/blob/main/4/api/src/main/java/net/kyori/adventure/audience/Audience.java)||Sends the message onto the audience's actionbars|

## Installation

TextComponents is available on Maven from either the [Official Maven Repository](https://repo.bb1.fun/#/releases/com/tubmc/text-components-adventure) or [JitPack](https://jitpack.io/#TubMC/TextComponents-Adventure)

### Official Repository

The latest version is hosted on an [Official Maven Repository](https://repo.bb1.fun/#/releases/com/tubmc/text-components-adventure)

First include the repository:

```xml
<repository>
  <id>bb1-repository-releases</id>
  <name>BradBot_1's Repository</name>
  <url>https://repo.bb1.fun/releases</url>
</repository>
```

Then add the dependency:

```xml
<dependency>
  <groupId>com.tubmc</groupId>
  <artifactId>text-components-adventure</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Jitpack

If the official repository is down or you choose not to trust it you can always pull it from [JitPack](https://jitpack.io/#TubMC/TextComponents-Adventure)

First include the repository:

```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```

Then add the dependency:

```xml
<dependency>
  <groupId>com.github.BradBot1</groupId>
  <artifactId>text-components-adventure</artifactId>
  <version>LATEST</version>
</dependency>
```

### Local Installation

Just run the following commands:

```shell
git clone https://github.com/TubMC/TextComponents-Adventure.git
cd TextComponents-Adventure
mvn clean install
```

It will then be accessible from your [local Maven Repoistory](https://www.javatpoint.com/maven-repository)

Now you can simply add the following dependency without a repository:

```xml
<dependency>
  <groupId>com.tubmc</groupId>
  <artifactId>text-components-adventure</artifactId>
  <version>1.0.0</version>
</dependency>
```