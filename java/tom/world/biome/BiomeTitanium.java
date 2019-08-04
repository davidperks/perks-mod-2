package tom.world.biome;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tom.init.BiomeInit;
import tom.init.BlockInit;
import tom.init.BlockOres;
import tom.objects.entity.EntityEagle;
import tom.objects.entity.EntityHawk;
import tom.util.handlers.EnumHandler;
import tom.world.gen.generators.WorldGenRubyTree;
import tom.world.gen.generators.WorldGenTitaniumTree;

public class BiomeTitanium extends Biome 
{
	public static final WorldGenAbstractTree TREE = new WorldGenTitaniumTree();

	public BiomeTitanium() 
	{
		super(new BiomeProperties("titanium").setBaseHeight(1.0f).setTemperature(0.0f));
		
		topBlock = BlockInit.DIRT_TITANIUM.getDefaultState();
		fillerBlock = BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.TITANIUM);
		
		this.decorator.treesPerChunk = 2;
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 5, 1, 5));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEagle.class, 5, 1, 5));
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieVillager.class,5,1,5));
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) 
	{
		return TREE;
	}

}
