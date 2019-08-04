package tom.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tom.objects.tileentity.TileEntityBlastFurnace;
import tom.util.Reference;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		//GameRegistry.registerTileEntity(TileEntityCopperChest.class, new ResourceLocation(Reference.MODID + ":copper_chest"));
		GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, new ResourceLocation(Reference.MODID + ":blast_furnace"));
		//GameRegistry.registerTileEntity(TileEntityGlowstoneGenerator.class, new ResourceLocation(Reference.MODID + ":glowstone_generator"));
		//GameRegistry.registerTileEntity(TileEntityElectricSinteringFurnace.class, new ResourceLocation(Reference.MODID + ":electric_sintering_furnace"));
		//GameRegistry.registerTileEntity(TileEntityEnergyStorage.class, new ResourceLocation(Reference.MODID + ":energy_storage"));
	}
}