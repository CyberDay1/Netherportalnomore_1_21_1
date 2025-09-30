package com.cyberday1.netherportalnomore;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = NetherPortalNoMore.MODID)
public final class PortalBlocker {

    private PortalBlocker() {}

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

        ItemStack stack = event.getItemStack();
        if (!isPortalLightingItem(stack)) {
            return;
        }

        BlockPos clickedPos = event.getPos();
        BlockState clickedState = level.getBlockState(clickedPos);
        if (!clickedState.is(Blocks.OBSIDIAN)) {
            return;
        }

        BlockPos firePos = clickedPos.relative(event.getFace());
        if (!level.getBlockState(firePos).isAir()) {
            return;
        }

        event.setCancellationResult(InteractionResult.FAIL);
        event.setCanceled(true);
    }

    private static boolean isPortalLightingItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        if (stack.is(ItemTags.CREEPER_IGNITERS) || stack.is(Tags.Items.TOOLS_IGNITER)) {
            return true;
        }

        Item item = stack.getItem();
        return item instanceof FlintAndSteelItem || item instanceof FireChargeItem;
    }
}
