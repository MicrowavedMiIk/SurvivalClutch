package com.example.examplemod;

import com.example.examplemod.handler.ClutchHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "survivalclutch", name = "Survival Clutch", version = "1.0")
public class SurvivalClutch {

    public static KeyBinding clutchKey;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        clutchKey = new KeyBinding("key.clutch", Keyboard.KEY_X, "SurvivalClutch");
        ClientRegistry.registerKeyBinding(clutchKey);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new ClutchHandler());
    }
}