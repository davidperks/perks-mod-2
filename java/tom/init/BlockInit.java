package tom.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tom.Main;
import tom.objects.blocks.BlockBase;
import tom.objects.blocks.BlockBlueBox;
import tom.objects.blocks.BlockDirtBase;
import tom.objects.blocks.BlockDoorBase;
import tom.objects.blocks.BlockLeavesBase;
import tom.objects.blocks.BlockLogsBase;
import tom.objects.blocks.BlockPeaPlant;
import tom.objects.blocks.BlockPlanks;
import tom.objects.blocks.BlockSaplingsBase;
import tom.objects.blocks.BlockTeleporter;
import tom.objects.machines.BlockBlastFurnace;

public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block BLOCK_RUBY = new BlockBase("block_ruby", Material.IRON);
	
	public static final Block ORE_END = new BlockOres("ore_end", "end");
	
	public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
	
	public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");
	
	public static final Block PLANKS_RUBY = new BlockBase("planks_ruby", Material.WOOD);
	
	public static final Block LOG_RUBY = new BlockLogsBase("log_ruby");
	
	public static final Block LOG_TITANIUM = new BlockLogsBase("log_titanium");
	
	public static final Block PLANKS_TTITANIUM = new BlockBase("planks_titanium", Material.WOOD);
	
	public static final Block LEAVES_RUBY = new BlockLeavesBase("leaves_ruby");
	
	public static final Block LEAVES_TITANIUM = new BlockLeavesBase("leaves_titanium");
	
	public static final Block SAPLING_RUBY = new BlockSaplingsBase("sapling_ruby");
	
	public static final Block SAPLING_TITANIUM = new BlockSaplingsBase("sapling_titanium");
	
	public static final Block DIRT_RUBY = new BlockDirtBase("dirt_ruby", Material.GROUND);
	
	public static final Block DIRT_TITANIUM = new BlockDirtBase("dirt_titanium", Material.GROUND);
	
	public static final Block DOOR_RUBY = new BlockDoorBase("door_ruby", Material.ANVIL);
	
	public static final Block BLUEBOX = new BlockBlueBox("blue_box");
	
	public static final Block BLAST_FURNACE = new BlockBlastFurnace("blast_furnace");
	
	public static final Block PEA_PLANT = new BlockPeaPlant("pea_plant");
	
	public static final Block TELEPORTER = new BlockTeleporter("teleporter");
	
}
