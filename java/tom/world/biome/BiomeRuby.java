package tom.world.biome;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import tom.init.BlockInit;
import tom.init.BlockOres;
import tom.objects.entity.EntityHawk;
import tom.util.handlers.EnumHandler;
import tom.world.gen.generators.WorldGenRubyTree;

public class BiomeRuby extends Biome 
{
	public static final WorldGenAbstractTree TREE = new WorldGenRubyTree();

	public BiomeRuby() 
	{
		super(new BiomeProperties("ruby").setBaseHeight(1.5f).setTemperature(20.0f));
		
		topBlock = BlockInit.DIRT_RUBY.getDefaultState();
		fillerBlock = BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.RUBY);
		
		this.decorator.treesPerChunk = 2;
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 5, 1, 5));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityHawk.class, 5, 1, 5));
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityZombieVillager.class,5,1,5));
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) 
	{
		return TREE;
	}
}
