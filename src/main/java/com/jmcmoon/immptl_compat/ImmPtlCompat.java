package com.jmcmoon.immptl_compat;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ImmPtlCompat.MODID)
public class ImmPtlCompat {

    public static final String MODID = "immptl_compat";

    public ImmPtlCompat(IEventBus modEventBus, ModContainer modContainer) {
    }
}
