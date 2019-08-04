package tom.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tom.world.biome.BiomeLibrary;
import tom.world.biome.BiomeRuby;
import tom.world.biome.BiomeTitanium;


public class BiomeInit 
{
	public static final Biome RUBY = new BiomeRuby();
	public static final Biome TITANIUM = new BiomeTitanium();
	public static final Biome LIBRARY_DIMENSION = new BiomeLibrary();
	
	public static void registerBiomes()
	{
		initBiome(RUBY, "Ruby", BiomeType.WARM, Type.DRY, Type.HILLS);
		initBiome(TITANIUM, "Titanium", BiomeType.ICY, Type.DRY, Type.HILLS);
		initBiome(LIBRARY_DIMENSION, "Library", BiomeType.WARM, Type.SPOOKY, Type.DRY, Type.DENSE);
	}
	
	public static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type... types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		
		return biome;
	}
	
}
