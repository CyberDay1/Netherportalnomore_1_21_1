package net.gigabit101.noportals;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(NoPortals.MODID)
public class NoPortals
{
    public static final String MODID = "noportals";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NoPortals(FMLJavaModLoadingContext context)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
