package com.cyberday1.netherportalnomore;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.Set;

@EventBusSubscriber(modid = NetherPortalNoMore.MODID)
public final class NetherPortalEvents {
    private static final Set<Item> BLOCKED_LIGHTING_ITEMS = Set.of(Items.FLINT_AND_STEEL, Items.FIRE_CHARGE);

    private NetherPortalEvents() {
    }

    @SubscribeEvent
    public static void onPortalSpawn(BlockEvent.PortalSpawnEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide()) {
            return;
        }

        if (!BLOCKED_LIGHTING_ITEMS.contains(event.getItemStack().getItem())) {
            return;
        }

        BlockState clickedState = level.getBlockState(event.getPos());
        if (!clickedState.is(Blocks.OBSIDIAN)) {
            return;
        }

        BlockPos firePos = event.getPos().relative(event.getFace());
        BlockState fireState = level.getBlockState(firePos);
        if (!fireState.isAir()) {
            return;
        }

        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.FAIL);
    }
}
