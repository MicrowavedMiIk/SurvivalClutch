# Survival Clutch
This is a personal Minecraft 1.8.9 mod I’ve been hacking away at to automate clutch placements. It handles the rotation and timing so you don't have to manually flick your mouse every time you need to save yourself from falling.

# A quick heads-up
This code is definitely not perfect. It’s a work in progress, and there are probably edge cases I haven't accounted for yet. It works for my setup, but feel free to dive in and mess with it if you find something that needs fixing.

# Downloads
If you just want to use the mod and don't care about the source code, you can download the latest version directly from the Releases Page.

How to use it
Hold a block.

Jump off something.

Press X while in the air to trigger the clutch logic. (rebindable in the settings)

# TODO List
1. CPS Control: Add a command to toggle the CPS cap or set a specific limit.

2. Search Logic: Implement a better searching technique to find placement spots more reliably.

Building
If you want to build this yourself, just clone the repo and run the following command in your terminal:

Windows:
./gradlew build (same with linux)


The finished jar will be waiting for you in the build/libs/ folder.