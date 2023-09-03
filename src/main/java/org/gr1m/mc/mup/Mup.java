package org.gr1m.mc.mup;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gr1m.mc.mup.bugfix.mc100830.network.MC100830PacketHandler;
import org.gr1m.mc.mup.bugfix.mc111978.network.MC111978PacketHandler;
import org.gr1m.mc.mup.bugfix.mc4.network.MC4PacketHandler;
import org.gr1m.mc.mup.bugfix.mc5694.network.MC5694PacketHandler;
import org.gr1m.mc.mup.config.cli.ConfigCommand;
import org.gr1m.mc.mup.config.network.ConfigPacketHandler;
import org.gr1m.mc.mup.config.MupConfig;
import org.gr1m.mc.mup.core.MupCoreConfig;
import org.gr1m.mc.mup.tweaks.hud.Hud;
import org.gr1m.mc.mup.tweaks.profiler.ProfilerCommand;

import java.io.File;

@Mod(modid = Mup.MODID,
     name = Mup.NAME,
     version = Mup.VERSION,
     dependencies = Mup.DEPENDENCIES,
     acceptedMinecraftVersions = "1.12.2",
     certificateFingerprint = Mup.FINGERPRINT,
     guiFactory = "org.gr1m.mc.mup.config.gui.MupGuiFactory")
public class Mup
{
    public static final String MODID = "mup";
    public static final String NAME = "EigenCraft Unofficial Patch";
    public static final String VERSION = "@VERSION@";
    public static final String FINGERPRINT = "@FINGERPRINT@";

    public static final String DEPENDENCIES = "required-after:mixinbooter@[8.0,)";

    public static Logger logger = LogManager.getLogger();
    public final static MupCommand cli = new MupCommand();

    public Mup()
    {
    }
    public static final Logger log = LogManager.getLogger();

    public final static MupConfig config = new MupConfig();
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("EigenCraft Unofficial Patch Loading");

        config.init(new File(Launch.minecraftHome, "config/mup.cfg"));
        config.load();
        MupConfig.config.save();
        Mup.config.sync();
        if (config.mc4.isLoaded()) MC4PacketHandler.registerMessagesAndEvents();
        if (config.mc5694.isLoaded()) MC5694PacketHandler.registerMessagesAndEvents();
        if (config.mc100830.isLoaded()) MC100830PacketHandler.registerMessagesAndEvents();
        if (config.mc111978.isLoaded()) MC111978PacketHandler.registerMessagesAndEvents();
        if (config.hud.isLoaded()) Hud.Init();

        ConfigPacketHandler.registerMessagesAndEvents();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(cli);
        
        MupCommand.registerCommand("config", new ConfigCommand());
        
        if (config.profiler.isLoaded()) MupCommand.registerCommand("tickhealth", new ProfilerCommand());
    }
}

// TODO: WorldServer.playerCheckLight