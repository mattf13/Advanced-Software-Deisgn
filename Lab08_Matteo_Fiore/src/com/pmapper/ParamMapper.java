package com.pmapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParamMapper {
    public <E> E map(String[] args, Class<E> paramClass) {
        ParamMapperContainer paramMapperContainer = readMetadata(paramClass);
        E paramInstance = (E) readParameters(args, paramMapperContainer);

        return paramInstance;
    }

    private ParamMapperContainer readMetadata(
            Class<?> paramClass)  {
        ParamMapperContainer pmc = new ParamMapperContainer();

        pmc.setTheClass(paramClass);

        pmc.setClassName(paramClass.getName());

        boolean allFieldsAreMandatory = isAnnotationMandatory(paramClass);

        HashMap<String, String> fieldNamesParameter = new HashMap<String, String>();
        HashMap<String, String> fieldTypeParameter = new HashMap<String, String>();
        ArrayList<String> allowedTypes = new ArrayList<>();
        ArrayList<String> mandatoryElement = new ArrayList<>();
        for(Field field : paramClass.getDeclaredFields()){
            Class type = field.getType();
            String name = field.getName();


            Annotation[] annotations = field.getDeclaredAnnotations();
            boolean nextFieldNeedsToBeMandatory = false;
            if (annotations != null)
            for (Annotation a : annotations) {
                Class<? extends Annotation> types = a.annotationType();
                String annotation = types.toString().substring(types.toString().lastIndexOf(".") + 1);

                if(annotation.equals("IsParameterPresent")){
                    if (!allowedTypes.contains("boolean")) {
                        allowedTypes.add("boolean");
                    }
                    if (!allowedTypes.contains("class java.lang.Boolean")) {
                        allowedTypes.add("class java.lang.Boolean");
                    }
                }
                if(annotation.equals("TextValue")){
                    if (!allowedTypes.contains("class java.lang.String")) {
                        allowedTypes.add("class java.lang.String");
                    }
                }

                for (Method method : types.getDeclaredMethods()) {
                    Object value = null;
                    try {
                        value = method.invoke(a, (Object[])null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    String key = annotation+"-"+ value;
                    fieldNamesParameter.put(key,name);
                    fieldTypeParameter.put(key,type.toString());
                    if(nextFieldNeedsToBeMandatory){
                        if(annotation.equals("TextValue")){
                        mandatoryElement.add("-"+value);
                        nextFieldNeedsToBeMandatory=false;}
                    }
                    if(allFieldsAreMandatory){
                        if(!mandatoryElement.contains("-"+value)){
                        mandatoryElement.add("-"+value);
                        }
                    }
                }
                if(annotation.equals("Mandatory")){
                    nextFieldNeedsToBeMandatory = true;
                }

            }
        }
        HashMap<String, Method> fieldSettersParameter = new HashMap<String, Method>();
        for(Method m : paramClass.getDeclaredMethods()){
            if(m.getName().startsWith("set"))
                for(Map.Entry<String, String> entry : fieldNamesParameter.entrySet()){
                    if(m.getName().endsWith(entry.getValue())){
                        fieldSettersParameter.put(entry.getKey(),m);
                    }


                }

        }

        pmc.setAllowedTypes(allowedTypes);
        pmc.setFieldNamesWithAnnotationParameter(fieldNamesParameter);
        pmc.setSetterForFieldsWithAnnotationParameters(fieldSettersParameter);
        pmc.setFieldTypeWithAnnotationParameter(fieldTypeParameter);
        pmc.setMandatoryFields(mandatoryElement);
        return pmc;
    }

    private static boolean isAnnotationMandatory(Class<?> paramClass) {
        Annotation[] classAnnotations = paramClass.getDeclaredAnnotations();
        boolean allFieldsAreMandatory = false;
        if(classAnnotations != null){
            for (Annotation a : classAnnotations) {
                Class<? extends Annotation> types = a.annotationType();
                String annotation = types.toString().substring(types.toString().lastIndexOf(".") + 1);

                if(annotation.equals("Mandatory")){
                    allFieldsAreMandatory = true;
                }

            }


        }
        return allFieldsAreMandatory;
    }

    private Object readParameters(String[] args,
                                  ParamMapperContainer paramMapperContainer){

        Object obj = null;
        try {

            Constructor<?> constructor = paramMapperContainer.getTheClass().getConstructor();
            obj = paramMapperContainer.getTheClass().getConstructor().newInstance();


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean typesCorrect = true;
        for(String type : paramMapperContainer.getFieldTypeWithAnnotationParameter().values()) {
            if (paramMapperContainer.getAllowedTypes().contains(type)) {
            }
            else{
                typesCorrect = false;
                break;
            }
        }
        if(typesCorrect) {
            try {
                for (Method setter : paramMapperContainer.getSetterForFieldsWithAnnotationParameters().values()) {
                    String setMethodParameter = setter.toString().substring(setter.toString().indexOf("(") + 1, setter.toString().indexOf(")"));
                    if (setMethodParameter.equals("boolean")
                            || setMethodParameter.equals("java.lang.Boolean")) {
                        setter.invoke(obj, false);
                    } else if (setMethodParameter.equals("java.lang.String")) {
                        setter.invoke(obj, (Object) null);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            String currentUsedParameter = "";
            String composite = "";
            int currentCompositeValues = 0;

            if (args.length==0 || args[0].startsWith("-")){
                if(!paramMapperContainer.getMandatoryFields().isEmpty()) {
                    for (String mandatory : paramMapperContainer.getMandatoryFields()) {
                        if (!Arrays.asList(args).contains(mandatory)) {
                            throw new ParameterReadingException();
                        }
                    }
                }
                ArrayList<String> mandatoryElementsWithValues = new ArrayList<>();
                for (String s : args) {
                    if (s.startsWith("-")) {
                        currentUsedParameter = s;
                        currentCompositeValues = 0;
                        String enteredParameter = "IsParameterPresent" + s;
                        String basicEmptyValueSetterMethodKey = "TextValue" + s;
                        composite = "";
                        if (paramMapperContainer.getSetterForFieldsWithAnnotationParameters().keySet().contains(enteredParameter)) {
                            try {
                                paramMapperContainer.getSetterForFieldsWithAnnotationParameters().get(enteredParameter).invoke(obj, true);
                                if(paramMapperContainer.getSetterForFieldsWithAnnotationParameters().keySet().contains(basicEmptyValueSetterMethodKey)){
                                    paramMapperContainer.getSetterForFieldsWithAnnotationParameters().get(basicEmptyValueSetterMethodKey).invoke(obj, "");
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        if (currentCompositeValues == 0) {
                            composite += s;
                        } else {
                            composite += " " + s;
                        }
                        String methodKeyToSet = "TextValue" + currentUsedParameter;
                        if (paramMapperContainer.getSetterForFieldsWithAnnotationParameters().keySet().contains(methodKeyToSet)) {
                            try {
                                paramMapperContainer.getSetterForFieldsWithAnnotationParameters().get(methodKeyToSet).invoke(obj, composite);
                                currentCompositeValues++;
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                        if(!paramMapperContainer.getMandatoryFields().isEmpty()) {
                            for (String mandatory : paramMapperContainer.getMandatoryFields()) {
                                if (mandatory.equals(currentUsedParameter)) {
                                    mandatoryElementsWithValues.add(mandatory);
                                }
                            }
                        }

                    }
                }
                if(!paramMapperContainer.getMandatoryFields().isEmpty()) {
                        if (paramMapperContainer.getMandatoryFields().equals(mandatoryElementsWithValues)) {
                            //All mandatory fields have a value set
                        }
                        else {
                            paramMapperContainer.getMandatoryFields().removeAll(mandatoryElementsWithValues);
                            ArrayList<String> notSetUpFieldsNames = new ArrayList<>();
                            for (String s: paramMapperContainer.getMandatoryFields()) {
                                notSetUpFieldsNames.add(paramMapperContainer.getFieldNamesWithAnnotationParameter().get("TextValue"+s));
                            }
                            throw new ParameterReadingException();
                        }

                }

            }
            else {
                throw new ParameterReadingException();
            }

        }
        else {
            throw new ParameterReadingException();
        }
        return obj;
    }
}

