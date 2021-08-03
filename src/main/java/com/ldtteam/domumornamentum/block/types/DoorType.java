package com.ldtteam.domumornamentum.block.types;

import net.minecraft.util.StringRepresentable;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum DoorType implements StringRepresentable
{
    FULL("full"),
    PORT_MANTEAU("port_manteau"),
    VERTICALLY_STRIPED("vertically_striped"),
    WAFFLE("waffle");

    private final String serializationName;

    DoorType(final String serializationName) {this.serializationName = serializationName;}

    @Override
    public String getSerializedName()
    {
        return serializationName;
    }

    public String getTranslationKeySuffix() {
        return getSerializedName().replace("_", ".");
    }

    public String getDefaultEnglishTranslation() {
        final String[] parts = getSerializedName().split("_");
        return Arrays.stream(parts)
          .map(StringUtils::capitalize)
          .collect(Collectors.joining(" "));
    }
}
