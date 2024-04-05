package de.pilz.anotheroregenerator;

import java.io.File;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.pilz.anotheroregenerator.configuration.OreConfig;
import de.pilz.anotheroregenerator.worldgen.WorldGenOres;
import de.pilz.anotheroregenerator.worldgen.WorldGenOresAdditional;

public class AnotherOreGeneratorProxy {

    private OreConfig oreConfig;
    private WorldGenOres worldGenOres;
    private WorldGenOresAdditional worldGenOredAdditional;

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        AnotherOreGenerator.LOG.info("I am " + AnotherOreGenerator.MODNAME + " at version " + Tags.VERSION);
        oreConfig = OreConfig.loadConfig(new File(event.getModConfigurationDirectory(), AnotherOreGenerator.MODID).getPath());
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        worldGenOres = new WorldGenOres(oreConfig);
        worldGenOredAdditional = new WorldGenOresAdditional(oreConfig);
        GameRegistry.registerWorldGenerator(worldGenOres, 0);
        GameRegistry.registerWorldGenerator(worldGenOredAdditional, 0);
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {}

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}