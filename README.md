# 🎮 Hypixel Status Checker & Player Telemetry Card

[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#)
[![Windows](https://custom-icon-badges.demolab.com/badge/Windows-0078D6?logo=windows11&logoColor=white)](#)
[![Linux](https://img.shields.io/badge/Linux-FCC624?logo=linux&logoColor=black)](#)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)


---

> ### 👑 Project Heritage & Evolution
> This software pipeline was originally conceptualized and initiated by **Sadekx**. It has been significantly refactored, optimized, and systematically enhanced under the sole engineering supervision and architectural redesign of **SirAtilotty** (`@SirAtilotty`).

---

## 📖 Complete Functional & System Overview

### What is Hypixel Status Checker?
**Hypixel Status Checker** is a high-performance, standalone native Windows desktop utility engineered to track, analyze, and display the real-time server presence of any Minecraft player across the global Hypixel Network. 

In the competitive landscape of Minecraft multiplayer, keeping tabs on friends, team members, or rivals usually requires executing sluggish in-game commands, navigating complex third-party web scrapers, or dealing with heavy terminal scripts. This utility completely revolutionizes that workflow. By launching a single compiled lightweight binary, operators can instantaneously summon an elegant, comprehensive graphical summary card containing deep session telemetry, verified rank designations, and updated cosmetic assets directly pulled from the cloud.

### Under the Hood: How It Bridges the APIs
The software operates as a synchronized data pipeline, orchestrating multi-tier queries to official backend databases without utilizing heavy external parsing libraries (like Jackson or Gson). This keeping-it-native approach ensures a zero-dependency footprint, lightning-fast execution speed, and minimal RAM overhead.

1. **The Mojang Core Layer:** The application accepts the raw, human-readable in-game username from the interface and instantly dispatches a secure HTTP GET request to Mojang's master profile servers to securely translate the name into a permanent 32-character hexadecimal Minecraft UUID string.
2. **The Hypixel Status Layer:** Utilizing the authenticated UUID and your private developer token, the engine simultaneously probes the Hypixel Network's live session database to determine server availability, connection status, and precise network routing.
3. **The Cosmetic & Asset Layer:** Once the core data is validated, a final payload is dispatched to extract network donation metrics (rank computing) while establishing a raw binary stream to download the exact 128x128 structural skin texture of the target user.

---

## 📊 Comprehensive Feature Deep-Dive

* **Live Status & Mini-Game Detection:** The engine doesn't just state whether a player is `Online` or `Offline`. If the target player is actively connected to the server, the system parses deeper into the JSON payload to extract and render the exact server cluster or mini-game they are trapped in (e.g., *SkyBlock*, *BedWars*, *Mega Walls*, or *Murder Mystery*).
* **Automated Rank Parsing & Accurate Coloring:** The program reads native server variables (`monthlyPackageRank` and `newPackageRank`) to calculate exactly what level of donor status the scanned profile holds. Once identified, it overrides generic Swing UI text properties to tint the status tags using official Hypixel color hex codes (`MVP++` in Gold, `MVP+/MVP` in Deep Aqua, `VIP+/VIP` in Emerald Green).
* **Smart Token Caching (Preferences API):** To maintain maximum usability, the tool features a persistent memory node implemented via `java.util.prefs.Preferences`. It asks for your Hypixel Developer API Key strictly during the first launch. Once validated, this token is securely cached into the operating system's local registry workspace. Future launches completely bypass the setup dialog, dropping the operator straight into the tracking sequence.
* **Refined Visual Design & Suppression:** By enforcing a custom `JOptionPane.PLAIN_MESSAGE` architectural blueprint, all default operating system layout components—such as disruptive native blue question marks or generic error symbols—are entirely suppressed. The result is a clean, custom modular interface tailored specifically for gamers.

---

## 🔑 How to Secure Your Hypixel Developer API Key

To ensure security and prevent network abuse, the Hypixel Network requires all external applications to authenticate using a unique developer credential string. Hypixel has officially **deprecated** and disabled the old in-game `/api new` command, transitioning all key management to their cloud infrastructure. 

Follow this updated, step-by-step operational guide to obtain your free key in less than two minutes:

### Step 1: Access the Cloud Gateway
Open your preferred web browser and navigate directly to the official [Hypixel Developer Portal](https://developer.hypixel.net/dashboard).

### Step 2: Authentication
Click the login option. You will be redirected to authenticate securely via your official Microsoft account linked directly to your paid Minecraft: Java Edition profile.

### Step 3: Instantiate a Personal Application
* Once logged into the central dashboard, click on the **Applications** or **Dashboard** management tab.
* Select the **Create New App** (or *Create Personal Key*) button.
* Provide a basic name for your workspace identifier (e.g., name it `"SirAtilotty Checker"` or `"Status App"`).
* Submit the configuration form.

### Step 4: Token Extraction
* Your dashboard will dynamically generate a long, secure alphanumeric string known as your **API Key**.
* Click the copy icon to lock the string into your clipboard.

### Step 5: Final Synchronization
Run your compiled `HypixelStatusChecker.exe` tool on your computer. When the minimalist input box prompts you with **"Enter Your API Key:"**, paste your copied token and hit OK. The tool will permanently cache it, grant you master access to the network APIs, and never bother you for configuration again!

### Release Difrences

v1.1.0 - Cloud Secure Edition (Current)
Architecture: Implemented Cloudflare Serverless Proxy.

Security: API keys are no longer stored on the client side. They are managed securely in an encrypted environment variable on the server.

User Experience: Zero-config setup. Users no longer need to input API keys; the system handles all backend authentication automatically.

Compliance: Fully optimized for Hypixel API Terms of Use, ensuring high availability and zero exposure of sensitive data.

v1.0.0 - Local Persistence Legacy
Architecture: Classic client-side request structure.

Security: Relied on java.util.prefs.Preferences to store API keys locally on the user's machine.

Workflow: Required a one-time manual setup where the user provided their Hypixel Developer API key during the first launch.

Status: Deprecated. This version is no longer recommended due to security and scalability limitations.
