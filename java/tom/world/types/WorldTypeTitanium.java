package tom.world.types;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import tom.init.BiomeInit;

public class WorldTypeTitanium extends WorldType
{

	public WorldTypeTitanium() 
	{
		super("titanium");
	}

	@Override
	public BiomeProvider getBiomeProvider(World world) 
	{
		return new BiomeProviderSingle(BiomeInit.TITANIUM);
	}
}
