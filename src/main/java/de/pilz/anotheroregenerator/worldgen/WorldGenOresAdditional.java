package de.pilz.anotheroregenerator.worldgen;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.common.IWorldGenerator;
import de.pilz.anotheroregenerator.configuration.OreConfig;
import de.pilz.anotheroregenerator.configuration.OreConfigEntry;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenOresAdditional implements IWorldGenerator {

    private final OreConfig oreConfig;

    public WorldGenOresAdditional(OreConfig oreConfig) {
        this.oreConfig = oreConfig;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.getWorldInfo().getTerrainType() != WorldType.FLAT || world.getWorldInfo().getGeneratorOptions().contains("decoration")) {
            int x = chunkX * 16 + random.nextInt(16) + 8;
			int z = chunkZ * 16 + random.nextInt(16) + 8;

            if ((world.getHeightValue(x, z) > 0) && random.nextInt(3) == 1) {
                for (OreConfigEntry entry : oreConfig.getOres()) {
                    if (entry.generateAdditional && entry.allowInDimension(world.provider.dimensionId)) {
                        int y = entry.minAdditionalY + random.nextInt(entry.maxAdditionalY - entry.minAdditionalY);

                        for (int l = 0; l < entry.blocksPerVein; ++l)
                        {
                            int i1 = x + random.nextInt(8) - random.nextInt(8);
                            int j1 = y + random.nextInt(4) - random.nextInt(4);
                            int k1 = z + random.nextInt(8) - random.nextInt(8);
                
                            if (world.isAirBlock(i1, j1, k1) && (world.getBlock(i1, j1 +1, k1) != Blocks.iron_ore || world.getBlock(i1, j1 +1, k1) != Blocks.mob_spawner || world.getBlock(i1, j1 +1, k1) != Blocks.end_portal_frame) && world.getBlock(i1, j1 +1, k1).isSideSolid(world, i1, j1 +1, k1, ForgeDirection.getOrientation(0))) {
                                world.setBlock(i1, j1 + 1, k1, entry.getOreBlock(), entry.oreBlockMeta, 2); //1
                            }
                            else if (world.isAirBlock(i1, j1, k1) && (world.getBlock(i1, j1 -1, k1) != Blocks.iron_ore || world.getBlock(i1, j1 -1, k1) != Blocks.mob_spawner || world.getBlock(i1, j1 -1, k1) != Blocks.end_portal_frame) && world.getBlock(i1, j1 -1, k1).isSideSolid(world, i1, j1 -1, k1, ForgeDirection.getOrientation(0))) {
                                world.setBlock(i1, j1 - 1, k1, entry.getOreBlock(), entry.oreBlockMeta, 2); //7
                            }
                            else if (world.isAirBlock(i1, j1, k1) && (world.getBlock(i1, j1 , k1 +1) != Blocks.iron_ore || world.getBlock(i1, j1 , k1 +1) != Blocks.mob_spawner || world.getBlock(i1, j1 , k1 +1) != Blocks.end_portal_frame) && world.getBlock(i1, j1 , k1 +1).isSideSolid(world, i1, j1 , k1 +1, ForgeDirection.getOrientation(0))) {
                                world.setBlock(i1, j1, k1 + 1, entry.getOreBlock(), entry.oreBlockMeta, 2); //8
                            }
                            else if (world.isAirBlock(i1, j1, k1) && (world.getBlock(i1, j1 , k1 -1) != Blocks.iron_ore || world.getBlock(i1, j1 , k1 -1) != Blocks.mob_spawner || world.getBlock(i1, j1 , k1 -1) != Blocks.end_portal_frame) && world.getBlock(i1, j1 , k1 -1).isSideSolid(world, i1, j1 , k1 -1, ForgeDirection.getOrientation(0))) {
                                world.setBlock(i1, j1, k1 - 1, entry.getOreBlock(), entry.oreBlockMeta, 2); //9
                            }
                            else if (world.isAirBlock(i1, j1, k1) && (world.getBlock(i1 +1, j1 , k1) != Blocks.iron_ore || world.getBlock(i1 +1, j1 , k1) != Blocks.mob_spawner || world.getBlock(i1 +1, j1 , k1) != Blocks.end_portal_frame) && world.getBlock(i1+1, j1 , k1).isSideSolid(world, i1+1, j1 , k1, ForgeDirection.getOrientation(0))) {
                                world.setBlock(i1 + 1, j1, k1, entry.getOreBlock(), entry.oreBlockMeta, 2); //10
                            }
                            else if (world.isAirBlock(i1, j1, k1) && (world.getBlock(i1 -1, j1 , k1) != Blocks.iron_ore || world.getBlock(i1 -1, j1 , k1) != Blocks.mob_spawner || world.getBlock(i1 -1, j1 , k1) != Blocks.end_portal_frame) && world.getBlock(i1-1, j1 , k1).isSideSolid(world, i1-1, j1 , k1, ForgeDirection.getOrientation(0))) {
                                world.setBlock(i1 - 1, j1, k1, entry.getOreBlock(), entry.oreBlockMeta, 2); //11
                            }
                        }
                    }
                }
            }
        }
    }
}