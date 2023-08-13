package org.gr1m.mc.mup.core.core;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class MupContainer extends DummyModContainer
{
    public MupContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "mup";
        meta.name = "Mup Core";
        meta.description = "Core functionality of Mup";
        meta.version = "1.12.2-1.4.9";
        meta.authorList.add("iamacat");
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        bus.register(this);
        return true;
    }
}