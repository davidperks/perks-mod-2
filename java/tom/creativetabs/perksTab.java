package tom.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import tom.init.ItemInit;

public class perksTab extends CreativeTabs
{

	public perksTab() 
	{
		super("perks_tab");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ItemInit.AXE_RUBY);
	}

}
