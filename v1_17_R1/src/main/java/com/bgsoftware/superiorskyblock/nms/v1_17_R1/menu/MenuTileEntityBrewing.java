package com.bgsoftware.superiorskyblock.nms.v1_17_R1.menu;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.chat.ChatMessage;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.TileEntityBrewingStand;
import org.bukkit.inventory.InventoryHolder;

public class MenuTileEntityBrewing extends TileEntityBrewingStand {

    private final InventoryHolder holder;

    public MenuTileEntityBrewing(InventoryHolder holder, String title) {
        super(BlockPosition.b, Blocks.a.getBlockData());
        this.holder = holder;
        this.setCustomName(new ChatMessage(title));
    }

    @Override
    public InventoryHolder getOwner() {
        return holder;
    }

}
