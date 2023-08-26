package org.satellite.command;

import org.satellite.command.postprocessor.PostProcessor;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSerializer {
    private PostProcessor postProcessor;

    public AbstractSerializer(PostProcessor postProcessor) {
        super();
        this.postProcessor = postProcessor;
    }

    public void generateFile(String fileName, Object command) {
        try {
            byte[] bytes = formatInformation(getPropertiesList(command));

            bytes = postProcessor.postProcess(bytes);
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("problems writing the file", e);
        }
    }


    protected abstract byte[] formatInformation(Map<String, Object> props);

    private Map<String, Object> getPropertiesList(Object command) throws Exception {
        Map<String, Object> props = new HashMap<>();
        Class<?> clazz = command.getClass();
        CommandType commandType = clazz.getAnnotation(CommandType.class);
        props.put("CommandType", commandType.value());

        for (Method m : clazz.getMethods()) {
            if (includeGetterInPropMap(m)) {
                String propName = m.getName().substring(3);
                Object propValue = m.invoke(command);
                props.put(propName, propValue);
            }
        }
        return props;

    }

    private static boolean includeGetterInPropMap(Method m) {
        return m.getName().startsWith("get")
                && m.getParameterCount() == 0
                && !(m.getName() == "getClass")
                && !(m.isAnnotationPresent(IgnoreInSerialization.class));
    }

}
