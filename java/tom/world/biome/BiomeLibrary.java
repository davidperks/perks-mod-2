package tom.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeLibrary extends Biome {

	public BiomeLibrary() 
	{
		super(new BiomeProperties("Library").setBaseHeight(1.5f).setTemperature(20.0f));
	}

}
