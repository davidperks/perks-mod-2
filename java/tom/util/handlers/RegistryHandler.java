package tom.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tom.Main;
import tom.commands.CommandDimensionTeleport;
import tom.init.BiomeInit;
import tom.init.BlockInit;
import tom.init.DimensionInit;
import tom.init.EntityInit;
import tom.init.ItemInit;
import tom.init.SmeltingRecipesInit;
import tom.util.IHasModel;
import tom.world.gen.WorldGenCustomOres;
import tom.world.gen.WorldGenCustomStructures;
import tom.world.gen.WorldGenCustomTrees;
import tom.world.types.WorldTypeCustom;
import tom.world.types.WorldTypeRuby;
import tom.world.types.WorldTypeTitanium;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for (Item item: ItemInit.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for (Block block: BlockInit.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries()
	{
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCustomTrees(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
		
		BiomeInit.registerBiomes();
		DimensionInit.registerDimension();
		
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenderers();
	}
	
	public static void initRegistries()
	{
		SmeltingRecipesInit.registerRecipes();
		SoundHandler.registerSounds();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public static void postInitRegistries()
	{
		WorldType RUBY = new WorldTypeRuby();
		WorldType TITANIUM = new WorldTypeTitanium();
		WorldType CUSTOM = new WorldTypeCustom();
	}
	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandDimensionTeleport());
	}
	
}