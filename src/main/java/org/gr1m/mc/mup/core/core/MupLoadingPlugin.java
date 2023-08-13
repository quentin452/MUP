package org.gr1m.mc.mup.core.core;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@IFMLLoadingPlugin.Name("MupCore")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE)
public class MupLoadingPlugin implements IFMLLoadingPlugin, IEarlyMixinLoader
{
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[0];
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {

    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

    @Override
    public List<String> getMixinConfigs()
    {
        return Arrays.asList(
                "mixins.mup.hud.json",
                "mixins.mup.mc1133.json",
                "mixins.mup.mc2399.json",
                "mixins.mup.mc63020.json",
                "mixins.mup.mc70850.json",
                "mixins.mup.mc5694.json",
                "mixins.mup.mc70850.json",
                "mixins.mup.mc100830.json",
                "mixins.mup.mc111978.json",
                "mixins.mup.mc118710.json",
                "mixins.mup.mc125157.json",
                "mixins.mup.mc4.json",
                "mixins.mup.mc9568.json",
                "mixins.mup.dac.json",
                "mixins.mup.mc2025.json",
                "mixins.mup.mc12211.json",
                "mixins.mup.mc14826.json",
                "mixins.mup.mc54026.json",
                "mixins.mup.mc64836.json",
                "mixins.mup.mc73051.json",
                "mixins.mup.mc80032.json",
                "mixins.mup.mc83039.json",
                "mixins.mup.mc88959.json",
                "mixins.mup.mc92916.json",
                "mixins.mup.mc98153.json",
                "mixins.mup.mc108469.json",
                "mixins.mup.mc109832.json",
                "mixins.mup.mc111444.json",
                "mixins.mup.mc119971.json",
                "mixins.mup.mc121196.json",
                "mixins.mup.mc123320.json",
                "mixins.mup.mc134989.json",
                "mixins.mup.mc161869.json",
                "mixins.mup.forgenetrace",
                "mixins.mup.newlight.json",
                "mixins.mup.profiler.json",
                "mixins.mup.rbp.json",
                "mixins.mup.rsturbo.json",
                "mixins.mup.netseqcheck.json",
                "mixins.mup.serverprop.json",
                "mixins.mup.vde.json",
                "mixins.mup.ete.json"
            );
    }
}