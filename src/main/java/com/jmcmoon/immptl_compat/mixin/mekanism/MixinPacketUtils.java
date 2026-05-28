package com.jmcmoon.immptl_compat.mixin.mekanism;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import mekanism.common.network.PacketUtils;
import qouteall.imm_ptl.core.chunk_loading.ImmPtlChunkTracking;


@Mixin(value = PacketUtils.class, remap = false)
public abstract class MixinPacketUtils {

    @Inject(
            method = "hasPlayersTracking",
            at = @At("RETURN"),
            cancellable = true,
            require = 1
    )
    private static void immptl_compat$includeImmersivePortalsTracking(
            ServerLevel level,
            BlockPos pos,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (cir.getReturnValueZ()) {
            return;
        }

        int chunkX = SectionPos.blockToSectionCoord(pos.getX());
        int chunkZ = SectionPos.blockToSectionCoord(pos.getZ());

        for (ServerPlayer player : level.getServer().getPlayerList().getPlayers()) {
            try {
                if (ImmPtlChunkTracking.isPlayerWatchingChunk(
                        player,
                        level.dimension(),
                        chunkX,
                        chunkZ
                )) {
                    cir.setReturnValue(true);
                    return;
                }
            } catch (NullPointerException e) {
                return;
            }
        }
    }
}