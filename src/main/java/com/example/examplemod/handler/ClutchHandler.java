package com.example.examplemod.handler;

import com.example.examplemod.SurvivalClutch;
import com.example.examplemod.util.ClutchUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClutchHandler {

    private final Minecraft mc = Minecraft.getMinecraft();
    private long lastClick = 0;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (mc.thePlayer == null || mc.theWorld == null) return;
        if (!SurvivalClutch.clutchKey.isKeyDown()) return;
        if (mc.thePlayer.onGround) return;
        if (System.currentTimeMillis() - lastClick < 50) return;
        if (mc.thePlayer.getHeldItem() == null) return;
        if (!(mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock)) return;

        MovingObjectPosition target = ClutchUtils.findPlacementSpot(mc);
        if (target == null) return;

        // Apply rotation
        float[] rot = ClutchUtils.lookAtBlock(mc, target.getBlockPos());
        mc.thePlayer.rotationYaw = rot[0];
        mc.thePlayer.rotationPitch = rot[1];

        mc.playerController.onPlayerRightClick(
                mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem(),
                target.getBlockPos(), target.sideHit, target.hitVec
        );

        mc.thePlayer.swingItem();
        lastClick = System.currentTimeMillis();
    }
}