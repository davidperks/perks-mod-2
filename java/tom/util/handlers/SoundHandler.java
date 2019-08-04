package tom.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tom.util.Reference;

public class SoundHandler 
{
	public static SoundEvent ENTITY_CENTAUR_AMBIENT, ENTITY_CENTAUR_HURT, ENTITY_CENTAUR_DEATH;
	public static SoundEvent ENTITY_BIRD_AMBIENT, ENTITY_BIRD_HURT, ENTITY_BIRD_DEATH, ENTITY_BIRD_FLAPPING;
	
	public static void registerSounds()
	{
		ENTITY_CENTAUR_AMBIENT = registerSound("entity.centaur.ambient");
		ENTITY_CENTAUR_HURT = registerSound("entity.centaur.hurt");
		ENTITY_CENTAUR_DEATH = registerSound("entity.centaur.death");
		ENTITY_BIRD_AMBIENT = registerSound("entity.bird.ambient");
		ENTITY_BIRD_HURT = registerSound("entity.bird.hurt");
		ENTITY_BIRD_DEATH = registerSound("entity.bird.death");
		ENTITY_BIRD_FLAPPING = registerSound("entity.bird.flapping");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Reference.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		
		return event; 
	}
}
