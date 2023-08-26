package org.satellite.command;

import org.satellite.command.postprocessor.PostProcessor;

import java.util.Map;

public class PropertiesSerializer extends AbstractSerializer {

    public PropertiesSerializer(PostProcessor postProcessor) {
        super(postProcessor);
    }

    @Override
    protected byte[] formatInformation(Map<String, Object> props) {
        StringBuilder propFileBuilder = new StringBuilder();
        for (String prop : props.keySet()) {
            propFileBuilder.append(prop + "=" + props.get(prop) + "\n");
        }
        byte[] bytes = propFileBuilder.toString().getBytes();
        return bytes;
    }
}
