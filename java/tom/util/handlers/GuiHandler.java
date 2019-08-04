package tom.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tom.objects.blocks.guis.ContainerBlastFurnace;
import tom.objects.blocks.guis.GuiBlastFurnace;
import tom.objects.tileentity.TileEntityBlastFurnace;
import tom.util.Reference;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_BLAST_FURNACE) return new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		//if(ID == Reference.GUI_COPPER_CHEST) return new ContainerCopperChest(player.inventory, (TileEntityCopperChest)world.getTileEntity(new BlockPos(x,y,z)), player);
		//if(ID == Reference.GUI_GLOWSTONE_GENERATOR) return new ContainerGlowstoneGenerator(player.inventory, (TileEntityGlowstoneGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		//if(ID == Reference.GUI_ELECTRIC_SINTERING_FURNACE) return new ContainerElectricSinteringFurnace(player.inventory, (TileEntityElectricSinteringFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		//if(ID == Reference.GUI_ENERGY_STORAGE) return new ContainerEnergyStorage(player.inventory, (TileEntityEnergyStorage)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == Reference.GUI_BLAST_FURNACE) return new GuiBlastFurnace(player.inventory, (TileEntityBlastFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		//if(ID == Reference.GUI_COPPER_CHEST) return new GuiCopperChest(player.inventory, (TileEntityCopperChest)world.getTileEntity(new BlockPos(x,y,z)), player);
		//if(ID == Reference.GUI_GLOWSTONE_GENERATOR) return new GuiGlowstoneGenerator(player.inventory, (TileEntityGlowstoneGenerator)world.getTileEntity(new BlockPos(x,y,z)));
		//if(ID == Reference.GUI_ELECTRIC_SINTERING_FURNACE) return new GuiElectricSinteringFurnace(player.inventory, (TileEntityElectricSinteringFurnace)world.getTileEntity(new BlockPos(x,y,z)));
		//if(ID == Reference.GUI_ENERGY_STORAGE) return new GuiEnergyStorage(player.inventory, (TileEntityEnergyStorage)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}
}
