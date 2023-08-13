package org.gr1m.mc.mup.core.core;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.common.Loader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;

public class MupMixinLoader implements ILateMixinLoader
{
    @Override
    public List<String> getMixinConfigs()
    {
        return Lists.newArrayList(
                "mixins.mup.modcompat.core.json",
                "mixins.mup.mc70850-optifine.json",
                "mixins.mup.modcompat.rcnewlight.json",
                "mixins.mup.modcompat.redstoneplusplus.v12d.json",
                "mixins.mup.modcompat.redstoneplusplus.v13b2.json",
                "mixins.mup.modcompat.rsppas.json"
                "mixins.mup.modcompat.vanillafoamfix.json",
                "mixins.mup.mc109832-quark.json"
        );
    }
}