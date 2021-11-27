package mod.schnappdragon.habitat.core.registry;

import com.mojang.serialization.Codec;
import mod.schnappdragon.habitat.core.Habitat;
import mod.schnappdragon.habitat.core.particles.*;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

public class HabitatParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Habitat.MODID);

    public static final RegistryObject<SimpleParticleType> FALLING_SLIME = register("falling_slime", false);
    public static final RegistryObject<SimpleParticleType> LANDING_SLIME = register("landing_slime", false);
    public static final RegistryObject<SimpleParticleType> FAIRY_RING_SPORE = register("fairy_ring_spore", false);
    public static final RegistryObject<ParticleType<FeatherParticleOptions>> FEATHER = register("feather", FeatherParticleOptions.DESERIALIZER, (codec) -> FeatherParticleOptions.CODEC);
    public static final RegistryObject<ParticleType<NoteParticleOptions>> NOTE = register("note", NoteParticleOptions.DESERIALIZER, (codec) -> NoteParticleOptions.CODEC);

    private static RegistryObject<SimpleParticleType> register(String name, Boolean alwaysShow) {
        return PARTICLE_TYPES.register(name, () -> new SimpleParticleType(alwaysShow));
    }

    private static <T extends ParticleOptions> RegistryObject<ParticleType<T>> register(String name, ParticleOptions.Deserializer<T> deserializer, final Function<ParticleType<T>, Codec<T>> codec) {
        return PARTICLE_TYPES.register(name, () -> new ParticleType<T>(false, deserializer) {
            public Codec<T> codec() {
                return codec.apply(this);
            }
        });
    }
}
