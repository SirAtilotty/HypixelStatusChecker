# Hypixel Status Checker

[![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk&logoColor=white)](#requirements)
[![Linux](https://img.shields.io/badge/Linux-AppImage-FCC624?logo=linux&logoColor=black)](https://github.com/SirAtilotty/HypixelStatusChecker/releases/tag/v1.1.0-L)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)

A small desktop tool that tells you whether a Minecraft player is online on Hypixel, which game they are in, and what rank they have. Type a username and get a card with their avatar, rank and status.

No Hypixel API key is needed. Requests go through a Cloudflare Worker that holds the key on the server side.

## Download

| Platform | File | Release |
|----------|------|---------|
| Windows  | `HypixelStatusChecker.With.Proxy.exe` | [v1.1.0](https://github.com/SirAtilotty/HypixelStatusChecker/releases/tag/v1.1.0) |
| Linux    | `HypixelStatusChecker.AppImage`       | [v1.1.0-L](https://github.com/SirAtilotty/HypixelStatusChecker/releases/tag/v1.1.0-L) |

### Requirements

Java 11 or newer. On Linux a full JDK is recommended.

### Linux

```sh
chmod +x HypixelStatusChecker.AppImage
./HypixelStatusChecker.AppImage
```

## Usage

1. Start the app.
2. Enter a Minecraft username.
3. A card shows the player's avatar, name, rank and status. If they are online, the current game type is listed as well.

Ranks are colored the way Hypixel colors them: MVP++ gold, MVP+/MVP aqua, VIP+/VIP green.

## How it works

1. The username is resolved to a UUID through the Mojang API.
2. The app calls the Cloudflare Worker's `/status` and `/player` endpoints with that UUID. The Worker forwards them to the Hypixel API with its own key, so no key ever ships with the client.
3. The avatar is downloaded from [mc-heads.net](https://mc-heads.net).
4. The result is drawn in a Swing dialog.

The client uses only the Java standard library (`java.net.http`, Swing). There are no third-party dependencies.

## Building from source

```sh
git clone https://github.com/SirAtilotty/HypixelStatusChecker.git
cd HypixelStatusChecker/Folder
javac HypixelProxy.java
java HypixelProxy
```

The Worker URL is set in `PROXY_BASE_URL` at the top of `HypixelProxy.java`. Point it at your own Worker if you deploy one.

## Privacy

The username you look up is sent to Mojang, the UUID is sent to the Cloudflare Worker (and from there to Hypixel) and to mc-heads.net. Nothing is stored on your machine.

## Credits

Started by **Sadekx** ([@mcserhat144-pixel](https://github.com/mcserhat144-pixel)). Developed and maintained by **SirAtilotty** ([@SirAtilotty](https://github.com/SirAtilotty)).

Not affiliated with Hypixel Inc. or Mojang Studios.

## License

[GPL-3.0](LICENSE)
