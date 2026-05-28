package com.jmcmoon.immptl_compat.mixin.ae2;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import qouteall.q_misc_util.dimension.DimIntIdMap;
import qouteall.q_misc_util.dimension.DimensionIntId;

@Mixin(DimensionIntId.class)
public class MixinDimensionIntId {
    @Unique
    private static final ResourceKey<Level> AE2_SPATIAL_STORAGE = ResourceKey.create(
            Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath("ae2", "spatial_storage")
    );

    @Inject(method = "fillInVanillaDimIds", at = @At("TAIL"))
    private static void immptl_compat$appendSpatialStorageDimension(DimIntIdMap rec, CallbackInfo ci) {
        if (!rec.containsDimId(AE2_SPATIAL_STORAGE)) {
            rec.add(AE2_SPATIAL_STORAGE, rec.getNextIntegerId());
        }
    }
}
