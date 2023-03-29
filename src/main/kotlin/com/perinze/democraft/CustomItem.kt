package com.perinze.democraft

import net.minecraft.client.item.TooltipContext
import net.minecraft.client.network.AbstractClientPlayerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class CustomItem(settings: Settings) : Item(settings) {

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        user.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1F, 1F)
        return TypedActionResult.success(user.getStackInHand(hand))
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        tooltip.add(TranslatableText("item.demo_namespace.custom_item.tooltip??").formatted(Formatting.RED))
    }
}