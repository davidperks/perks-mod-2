package tom.commands;

import java.awt.TextComponent;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import tom.commands.util.Teleport;
import tom.util.Reference;

public class CommandDimensionTeleport extends CommandBase
{
	public static final List<String> aliases = Lists.newArrayList(Reference.MODID, "tp", "tpdim", "tpdimension", "teleport");

	@Override
	public String getName() 
	{
		return "tpdimension";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "tpdimension <ID>";
	}
	
	@Override
	public List<String> getAliases() 
	{
		return aliases;
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) 
	{
		return true;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if (args.length < 1)
		{
			return;
		}
		
		String s = args[0];
		
		int dimensionID = 0;
		
		try
		{
			dimensionID = Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			sender.sendMessage(new TextComponentString("failed to teleport"));
		}
		
		if (sender instanceof EntityPlayer)
		{
			Teleport.teleportToDimension((EntityPlayer)sender, dimensionID, ((EntityPlayer) sender).posX, ((EntityPlayer) sender).posY, ((EntityPlayer) sender).posZ);
		}
	}

}
