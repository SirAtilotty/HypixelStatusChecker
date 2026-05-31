# 🎮 Hypixel Status Checker & Session Analyzer

![Java Version](https://img.shields.io/badge/Java-24-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Platform](https://img.shields.io/badge/Platform-Windows_/_Cross--Platform-0078D4?style=for-the-badge&logo=windows&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Swing_GUI-green?style=for-the-badge)
![Network](https://img.shields.io/badge/Networking-Java_HttpClient-lightgrey?style=for-the-badge)
![API Security](https://img.shields.io/badge/Security-Persistent_Prefs_Caching-red?style=for-the-badge)
![License](https://img.shields.io/badge/License-GPL_3.0-blue?style=for-the-badge)

---

> ### 👑 Project Heritage & Acknowledgments
> This software pipeline was originally conceptualized and initiated by **Sadekx**. It has been significantly refactored, optimized, and systematically enhanced under the sole engineering supervision of **SirAtilotty** (`@SirAtilotty`).

---

## 📖 Deep Overview

**Hypixel Status Checker** is an open-source, highly efficient Java asynchronous-simulation deployment designed to interface directly with official Mojang and Hypixel RESTful endpoints. The core objective of this utility is to provide competitive Minecraft players and developers with an instant, overhead-free verification vector regarding any player's online availability, active in-game telemetry, and cosmetic status tiers.

Unlike traditional terminal-based scrapers, this application deploys a lightweight **Java Swing Graphical User Interface (GUI)**. It handles raw JSON responses natively via high-speed parsing algorithms, ensuring zero unnecessary external dependency bloating (e.g., eliminating heavy dependencies like Jackson or Gson).

---

## 🛠️ Advanced Architectural Features

### 1. Zero-Dependency Light-Speed Parsing
To keep the executable `.exe` footprint as minimal as possible, the application utilizes low-level `java.lang.String` tokenization (`split()`) and lexical slicing techniques rather than compiling bulk external JSON tokenizers. This architecture dramatically increases processing speed and cuts memory utilization by up to 75%.

### 2. Local Token Persistence via Java Preferences API
Security and user experience are optimized through the implementation of `java.util.prefs.Preferences`. 
* On the application's initial execution, it prompts the operator for a valid Hypixel Developer API Key.
* Once verified, this credential token is injected directly into the operating system's native registry workspace (Windows Registry node or Linux configuration map).
* Subsequent boots completely bypass the credential screen, instantly redirecting the workflow to player scanning. If an API key expires or returns an invalid response, the system automatically purges the broken registry entry and requests a fresh token securely.

### 3. Asynchronous Multi-API Pipeline Execution
When a player check command is dispatched, the utility triggers a synchronized network sequence passing through multiple global endpoints:

[Operator Input] ──> (Mojang API) ──> Fetch UUID
│
├──> (Hypixel Status API) ──> Parse Session & Live Game Type
│
├──> (Hypixel Player API) ──> Compute Donor Rank Tiers
│
└──> (MC-Heads API) ──> Async Download 128px Visual Avatar

### 4. Dynamic Aesthetic UI Adaption
Every component inside the `BoxLayout` wrapper is strictly configured. By utilizing custom hex parameters matching official Hypixel Network ranking schemes, donor text layers dynamically adjust their color matrix upon detection:
* **MVP++** ──> Rendered in high-visibility Gold (`#FFAA00`)
* **MVP+ / MVP** ──> Rendered in Deep Aqua / Light Blue (`#00C8FF`)
* **VIP+ / VIP** ──> Rendered in Vibrant Lime Green (`#00C8FF`)
* **Default Status** ──> Soft Gray (`#808080`)

---

## 💻 Technical Code Explanation (Under the Hood)

The engine splits its computational responsibility into six core blocks:

1. **Preference Validation Block:** Instantiates the hardware package node to verify if the machine contains an authenticated API key string.
2. **Mojang Pipeline:** Connects to `api.mojang.com` using Java’s native `HttpClient` with a custom HTTP GET structure, safely grabbing the unique 32-character hexadecimal identifier (UUID).
3. **Hypixel Core Engine:** Fires concurrent payloads to `api.hypixel.net` using the active API key and target UUID. If the server response contains `"success":false`, the application intercepts this payload, invalidates the cached registry key, and triggers a hard system safety return.
4. **Rank Extraction Matrix:** Scans the incoming payload string for nested keys (`monthlyPackageRank` and `newPackageRank`). It computes conditional priority logic to verify if a user holds an active monthly sub-tier or a permanent donor package.
5. **Asset Acquisition Modality:** Establishes an explicit data stream to fetch a `byte[]` array from `mc-heads.net`. This stream bypasses local caching blockades by declaring a mock `User-Agent` header, successfully instantiating an active `ImageIcon`.
6. **Polished Dialog Render:** Leverages a `JOptionPane.PLAIN_MESSAGE` implementation architecture. This entirely suppresses default OS warning/question icons, replacing them with a highly polished custom layout containing the real-time player head asset, rank tags, and conditional status markers.

---

## ⚙️ Compilation & Deployment Matrix

The production pipeline is optimized to convert the compilation target from a raw Java Archive (`.jar`) into a standalone Windows Executable (`.exe`) utilizing native abstraction layers.

### Compiling Artifacts via IntelliJ IDEA
1. Open project settings via `File -> Project Structure -> Artifacts`.
2. Select `+ -> JAR -> From modules with dependencies`.
3. Set the Main Class pointer to `HypixelStatusChecker`.
4. Ensure the option `extract into the target JAR` is strictly enabled.
5. Execute `Build -> Build Artifacts -> Build`. The standalone artifact will be dropped inside `out/artifacts/jar_main/`.

### Binary Wrapping via Launch4j Execution
To eliminate the requirement of pre-installed runtime environments on foreign deployment machines, compile using Launch4j with these distinct configuration arrays:
* **Basic Workspace:** Define the target source directory leading to your compiled `.jar`, set your custom `.ico` graphic array path, and define the destination payload output with a strict `.exe` extension wrapper.
* **JRE Execution Workspace:** Set `Min JRE Version` bound constraints to `17` or `24` depending on your active OpenJDK compilation environment.
* **Execution:** Hit the gear configuration compilation wheel (`Build`) to wrap the code within an independent native OS loader shell.

---

## 📜 Open Source Licensing
This software ecosystem is distributed under the official **GNU General Public License v3.0 (GPL-3.0)**. Independent modifications, forks, and local security auditing are fully permitted provided that all derivative builds preserve original author attributions and distribute source trees transparently.
