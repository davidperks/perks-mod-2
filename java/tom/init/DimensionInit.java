package tom.init;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import tom.world.dimension.library.GreatLibrary;

public class DimensionInit 
{
	public static final DimensionType LIBRARY = DimensionType.register("Library", "_library", 2, GreatLibrary.class, false);
	
	public static void registerDimension()
	{
		DimensionManager.registerDimension(2, LIBRARY);
	}
	
}
