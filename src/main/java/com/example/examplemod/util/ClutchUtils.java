package com.example.examplemod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.*;

public class ClutchUtils {

    public static MovingObjectPosition findPlacementSpot(Minecraft mc) {
        // Downward ray
        Vec3 feet = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        Vec3 below = new Vec3(mc.thePlayer.posX, mc.thePlayer.posY - 4.0, mc.thePlayer.posZ);
        MovingObjectPosition ray = mc.theWorld.rayTraceBlocks(feet, below, false, true, false);

        if (ray != null && ray.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            BlockPos bp = ray.getBlockPos();
            return new MovingObjectPosition(
                    new Vec3(bp.getX() + 0.5, bp.getY() + 0.5, bp.getZ() + 0.5),
                    pickSide(mc, bp),
                    bp
            );
        }

        // Logic for search
        double px = mc.thePlayer.posX + mc.thePlayer.motionX * 2;
        double pz = mc.thePlayer.posZ + mc.thePlayer.motionZ * 2;

        BlockPos closest = null;
        double closestDist = Double.MAX_VALUE;

        for (int x = -4; x <= 4; x++) {
            for (int y = -3; y <= 1; y++) {
                for (int z = -4; z <= 4; z++) {
                    BlockPos pos = new BlockPos(px + x, mc.thePlayer.posY + y, pz + z);
                    if (mc.theWorld.isAirBlock(pos)) continue;
                    double dist = mc.thePlayer.getDistanceSq(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                    if (dist > 20.25) continue;
                    if (dist < closestDist) {
                        closestDist = dist;
                        closest = pos;
                    }
                }
            }
        }

        if (closest == null) return null;

        return new MovingObjectPosition(
                new Vec3(closest.getX() + 0.5, closest.getY() + 0.5, closest.getZ() + 0.5),
                pickSide(mc, closest),
                closest
        );
    }

    public static EnumFacing pickSide(Minecraft mc, BlockPos pos) {
        double dx = mc.thePlayer.posX - (pos.getX() + 0.5);
        double dz = mc.thePlayer.posZ - (pos.getZ() + 0.5);
        if (Math.abs(dx) > Math.abs(dz)) return dx > 0 ? EnumFacing.EAST : EnumFacing.WEST;
        return dz > 0 ? EnumFacing.SOUTH : EnumFacing.NORTH;
    }

    public static float[] lookAtBlock(Minecraft mc, BlockPos pos) {
        double dx = pos.getX() + 0.5 - mc.thePlayer.posX;
        double dy = pos.getY() + 0.5 - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double dz = pos.getZ() + 0.5 - mc.thePlayer.posZ;
        float yaw = (float) (Math.atan2(dz, dx) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float) -(Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * 180.0 / Math.PI);
        return new float[]{ yaw, pitch };
    }
}