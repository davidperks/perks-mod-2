package tom.world.types;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import tom.init.BiomeInit;
import tom.world.types.layer.GenLayerCustom;

public class WorldTypeCustom extends WorldType
{

	public WorldTypeCustom() 
	{
		super("custom");
	}

	@Override
	public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, ChunkGeneratorSettings chunkSettings) 
	{
		return new GenLayerCustom(worldSeed, parentLayer, this, chunkSettings);
	}
	
}
