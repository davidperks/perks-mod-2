package tom.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tom.util.handlers.EnumHandler;

public class SmeltingRecipesInit 
{
	public static void registerRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(BlockInit.ORE_OVERWORLD, 1, 0), new ItemStack(ItemInit.RUBY, 4), 10);
		GameRegistry.addSmelting(ItemInit.RUBY, new ItemStack(ItemInit.AXE_RUBY, 2), 10);
		GameRegistry.addSmelting(new ItemStack(BlockInit.ORE_OVERWORLD, 1, 1), new ItemStack(ItemInit.INGOT_TITANIUM, 2), 10);
	}
}
