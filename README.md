# 🎮 Hypixel Status Checker

![Java](https://img.shields.io/badge/Java-24-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Platform](https://img.shields.io/badge/Platform-Windows-0078D4?style=for-the-badge&logo=windows&logoColor=white)
![License](https://img.shields.io/badge/License-GPL_3.0-blue?style=for-the-badge)

---

> ### 👑 Project Heritage
> This software pipeline was originally initiated by **Sadekx** and has been fully refactored, optimized, and systematically enhanced under the sole engineering supervision of **SirAtilotty** (`@SirAtilotty`).

---

## 📖 Overview

**Hypixel Status Checker** is a high-performance, lightweight Windows utility written in Java. It directly interfaces with official Mojang and Hypixel REST APIs to provide real-time tracking of any Minecraft player's network status, server-wide donor rank, and current mini-game telemetry.

The application deploys a clean, minimalist **Java Swing Graphical User Interface (GUI)** designed to extract and display raw JSON payloads with zero external dependency bloating.

---

## 🛠️ Core Features

* **Real-Time Telemetry:** Instantly identifies if a targeted player is `Online` or `Offline`.
* **Live Activity Tracking:** Automatically extracts and renders the exact mini-game the player is currently inside (e.g., *SkyBlock*, *BedWars*).
* **Rank Matrix Recognition:** Scans Hypixel backend structures to identify donor status (`MVP++`, `MVP+`, `MVP`, `VIP+`, `VIP`) and dynamically tints labels using official network color palettes.
* **Secure Token Caching:** Implements the native Java `Preferences` API to securely lock down your Hypixel Developer API Key into the OS workspace, bypassing login credentials on subsequent boots.
* **Suppressed UI Overhead:** Leverages custom `JOptionPane.PLAIN_MESSAGE` architectures to completely eliminate generic OS warning elements, delivering a refined look.

---

## ⚙️ Compilation & Deployment

The production pipeline converts the internal Java target archive (`.jar`) directly into a native Windows executable shell (`.exe`).

### 1. Artifact Export via IntelliJ IDEA
* Navigate to `File -> Project Structure -> Artifacts`.
* Add `+ -> JAR -> From modules with dependencies` and assign `HypixelStatusChecker` as the Main Class.
* Select `extract into the target JAR` to embed all pipeline layers seamlessly.
* Execute `Build -> Build Artifacts -> Build`.

### 2. Binary Wrapping via Launch4j
* **Basic Target:** Point the compiler directly to your new `.jar` file, bind your custom `.ico` emblem asset, and establish your target output as a `.exe` filename structure.
* **JRE Array:** Set the `Min JRE Version` strict enforcement boundaries to `17` or `24` to match your local OpenJDK build space.
* Execute the internal `Build` option to output the standalone Windows tool.
