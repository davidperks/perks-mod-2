package tom.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler 
{
	public static enum EnumType implements IStringSerializable
	{
		RUBY(0, "ruby"),
		TITANIUM(1, "titanium");
		
		private static final EnumType[] META_LOOKUP = new EnumType[values().length];
		private final int meta;
		private final String name, unLocaizedName;
		
		private EnumType(int meta, String name)
		{
			this(meta, name, name);
		}
		
		private EnumType(int meta, String name, String unLocaizedName)
		{
			this.meta = meta;
			this.name = name;
			this.unLocaizedName = unLocaizedName;
		}

		public int getMeta() {
			return this.meta;
		}

		public String getName() {
			return this.name;
		}

		public String getUnLocaizedName() {
			return this.unLocaizedName;
		}
		
		public String toString()
		{
			return this.name;
		}
		
		public static EnumType byMetaData(int meta)
		{
			return META_LOOKUP[meta];
		}
		
		static 
		{
			for (EnumType enumtype: values())
			{
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}
	}
}
