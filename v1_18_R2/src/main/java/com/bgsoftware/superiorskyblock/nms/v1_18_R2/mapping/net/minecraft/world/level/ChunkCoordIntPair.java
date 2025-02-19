package com.bgsoftware.superiorskyblock.nms.v1_18_R2.mapping.net.minecraft.world.level;

import com.bgsoftware.superiorskyblock.nms.mapping.Remap;
import com.bgsoftware.superiorskyblock.nms.v1_18_R2.mapping.MappedObject;

public final class ChunkCoordIntPair extends MappedObject<net.minecraft.world.level.ChunkCoordIntPair> {

    public ChunkCoordIntPair(int chunkX, int chunkZ) {
        this(new net.minecraft.world.level.ChunkCoordIntPair(chunkX, chunkZ));
    }

    public ChunkCoordIntPair(net.minecraft.world.level.ChunkCoordIntPair handle) {
        super(handle);
    }

    @Remap(classPath = "net.minecraft.world.level.ChunkPos",
            name = "toLong",
            type = Remap.Type.METHOD,
            remappedName = "a")
    public long pair() {
        return handle.a();
    }

    @Remap(classPath = "net.minecraft.world.level.ChunkPos",
            name = "x",
            type = Remap.Type.FIELD,
            remappedName = "c")
    public int getX() {
        return handle.c;
    }

    @Remap(classPath = "net.minecraft.world.level.ChunkPos",
            name = "z",
            type = Remap.Type.FIELD,
            remappedName = "d")
    public int getZ() {
        return handle.d;
    }

}
