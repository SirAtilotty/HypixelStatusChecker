# 🎮 Hypixel Status Checker

![Java](https://img.shields.io/badge/Java-24-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Platform](https://img.shields.io/badge/Platform-Windows-0078D4?style=for-the-badge&logo=windows&logoColor=white)
![License](https://img.shields.io/badge/License-GPL_3.0-blue?style=for-the-badge)

---

> ### 👑 Project Heritage
> This software pipeline was originally initiated by **Sadekx** and has been fully refactored, optimized, and systematically enhanced under the sole engineering supervision of **SirAtilotty** (`@SirAtilotty`).

---

## 📖 What Does This Application Do?

**Hypixel Status Checker** is a fast, standalone Windows desktop utility designed to instantly track and display the real-time presence of any Minecraft player on the Hypixel Network. 

Instead of dealing with complex terminal commands or browser-based stat trackers, users simply launch the application and enter a player's username. Behind the scenes, the tool communicates directly with official Mojang and Hypixel APIs to deliver a clean, graphical summary card containing:

* **Live Status & Activity:** Instantly reveals if the targeted player is currently `Online` or `Offline`. If they are online, the application dives deeper to show the exact mini-game they are currently playing (such as *BedWars*, *SkyBlock*, or *Murder Mystery*).
* **Rank & Visuals:** Scans the Hypixel database to determine the player's exact donor rank (`MVP++`, `MVP+`, `VIP`, etc.), rendering it in its native official color. It also downloads and displays the player's real-time 128x128 Minecraft head avatar.
* **Smart API Caching:** To ensure a seamless user experience, the application asks for your Hypixel API Key only once during the first launch. It securely caches this key directly into your operating system's native preferences. On all future launches, you bypass the setup completely and jump straight to checking player statuses.

Designed with a sleek, minimalist Java Swing interface, this application suppresses default Windows warning dialogs to provide a custom, distraction-free visual aesthetic for the end-user.
