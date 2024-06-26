package de.pilz.anotheroregenerator.configuration.oreconfig;

import net.minecraft.block.Block;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.common.registry.GameRegistry;

public class OreConfigEntry {

    public String name;
    public boolean enabled;
    public boolean isAdditional;

    public String oreModName;
    public String oreBlockName;
    public int oreBlockMeta = 0;

    public String deepslateOreModName;
    public String deepslateOreBlockName;
    public int deepslateOreBlockMeta = 0;

    public String sourceModName = "minecraft";
    public String sourceBlockName = "stone";

    public int intensity; // Vines per chunk (blocks per chunk for additional)
    public int minVeinSize;
    public int maxVeinSize;
    public int minY;
    public int maxY;

    public int[] dimensionIdsBlacklist = new int[0];
    public boolean dimensionIdsBlacklistAsWhitelist;

    public boolean allowInDimension(int dimensionId) {
        return ArrayUtils.contains(dimensionIdsBlacklist, dimensionId) == dimensionIdsBlacklistAsWhitelist;
    }

    public Block getSourceBlock() {
        return GameRegistry.findBlock(sourceModName, sourceBlockName);
    }

    public Block getOreBlock() {
        return GameRegistry.findBlock(oreModName, oreBlockName);
    }

    public Block getDeepslateOreBlock() {
        return GameRegistry.findBlock(deepslateOreModName, deepslateOreBlockName);
    }
}
