HypixelPlayerTracker
A lightweight Java application that checks Hypixel player status in real-time. Enter a username and instantly see if they're online or offline using the Hypixel API.

Features

Real-time player online/offline status
Simple GUI with Java Swing
Automatically fetches UUID via Mojang API
No hardcoded API key — you use your own


Requirements

Java 11 or higher
A Hypixel API Key (see setup below)


Setup
1. Get your Hypixel API Key

Join Hypixel: mc.hypixel.net
Type /api new in the chat
Copy the API key it gives you

2. Run the program

Launch the .jar file or run from your IDE
Enter your API key when prompted
Enter a player name and check their status


How It Works

Takes the player name as input
Fetches the UUID from the Mojang API
Uses the UUID to query the Hypixel API
Displays whether the player is online or offline


Usage
1. Run the program
2. Paste your Hypixel API key
3. Enter a Minecraft username
4. See their current status on Hypixel

APIs Used

Mojang API — for UUID lookup
Hypixel API — for player status


Notes

You need your own Hypixel API key to use this program
Avoid running the program too frequently to prevent rate limiting
The Hypixel API allows up to 300 requests per minute per key


Author
Made by SirAtilotty
