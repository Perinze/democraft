package com.perinze.democraft

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class UniqueBlock(settings: Settings) : Block(settings) {
    companion object {
        val charged = BooleanProperty.of("charged")
        val uniqueBlock = UniqueBlock(FabricBlockSettings.copyOf(Blocks.STONE))
    }

    init {
        defaultState = defaultState.with(charged, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(charged)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos?,
        player: PlayerEntity,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f)
        world.setBlockState(pos, state.with(charged, true))
        return ActionResult.SUCCESS
    }

    override fun onSteppedOn(world: World, pos: BlockPos?, state: BlockState, entity: Entity?) {
        if (world.getBlockState(pos).get(charged)) {
            val lightningEntity = EntityType.LIGHTNING_BOLT.create(world)
            lightningEntity?.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos))
            world.spawnEntity(lightningEntity)
        }
        world.setBlockState(pos, state.with(charged, false))
        super.onSteppedOn(world, pos, state, entity)
    }
}