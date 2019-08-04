package tom.world.dimension.library;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import tom.init.BiomeInit;
import tom.init.DimensionInit;
import tom.world.biome.BiomeLibrary;
import tom.world.dimension.library.chunks.ChunkGeneratorLibrary;

public class GreatLibrary extends WorldProvider
{
	public GreatLibrary()
	{
		this.biomeProvider = new BiomeProviderSingle(BiomeInit.LIBRARY_DIMENSION);
	}

	@Override
	public DimensionType getDimensionType() 
	{
		return DimensionInit.LIBRARY;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() 
	{
		return new ChunkGeneratorLibrary(world, true, world.getSeed());
	}

	@Override
	public boolean canRespawnHere() 
	{
		return false;
	}
	
	@Override
	public boolean isSurfaceWorld() 
	{
		return false;
	}
}
