package com.perinze.democraft

import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback.Registry
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier

// For support join https://discord.gg/v6v4pMv

@Suppress("unused")
fun init() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    println("Hello Fabric world!")

    val customItem = CustomItem(FabricItemSettings().maxCount(16).group(ItemGroup.MISC))
    net.minecraft.util.registry.Registry.register(
        net.minecraft.util.registry.Registry.ITEM,
        Identifier("demo_namespace", "custom_item"),
        customItem
    )
    FuelRegistry.INSTANCE.add(customItem, 3000)

    //val uniqueBlock = UniqueBlock(FabricBlockSettings.of(Material.STONE).hardness((4.0f)))
    net.minecraft.util.registry.Registry.register(
        net.minecraft.util.registry.Registry.BLOCK,
        Identifier("demo_namespace", "unique_block"),
        UniqueBlock.uniqueBlock
    )
    net.minecraft.util.registry.Registry.register(
        net.minecraft.util.registry.Registry.ITEM,
        Identifier("demo_namespace", "unique_block"),
        BlockItem(UniqueBlock.uniqueBlock, FabricItemSettings())
    )
}

