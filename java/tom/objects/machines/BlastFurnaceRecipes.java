package tom.objects.machines;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tom.init.BlockInit;

public class BlastFurnaceRecipes 
{	
	private static final BlastFurnaceRecipes INSTANCE = new BlastFurnaceRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> blastingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public static BlastFurnaceRecipes getInstance()
	{
		return INSTANCE;
	}

	private BlastFurnaceRecipes() 
	{
		addBlastRecipe(new ItemStack(Blocks.DIRT), new ItemStack(Blocks.DIRT), new ItemStack(BlockInit.BLOCK_RUBY), 5.0F);
		addBlastRecipe(new ItemStack(Items.BEETROOT), new ItemStack(Blocks.CHORUS_PLANT), new ItemStack(BlockInit.SAPLING_TITANIUM), 5.0F);
	}

	public void addBlastRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) 
	{
		if(getBlastResult(input1, input2) != ItemStack.EMPTY) return;
		this.blastingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}

	public ItemStack getBlastResult(ItemStack input1, ItemStack input2) 
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.blastingList.columnMap().entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) 
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) 
					{
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.blastingList;
	}

	public float getSinteringExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}