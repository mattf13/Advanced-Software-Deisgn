package com.pmapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class ParamMapperContainer {
    private Class<?> theClass;

    private String ClassName;
    
    private HashMap<String, String> fieldNamesWithAnnotationParameter = new HashMap<String, String>();
    private HashMap<String, Method> setterForFieldsWithAnnotationParameters = new HashMap<String, Method>();
    private HashMap<String, String> FieldTypeWithAnnotationParameter = new HashMap<String, String>();
    private ArrayList<String> allowedTypes = new ArrayList<>();
    private ArrayList<String> mandatoryFields = new ArrayList<>();

    public ArrayList<String> getMandatoryFields() {
        return mandatoryFields;
    }

    public void setMandatoryFields(ArrayList<String> mandatoryFields) {
        this.mandatoryFields = mandatoryFields;
    }


    public ArrayList<String> getAllowedTypes() {
        return allowedTypes;
    }

    public void setAllowedTypes(ArrayList<String> allowedTypes) {
        this.allowedTypes = allowedTypes;
    }


    public HashMap<String, String> getFieldTypeWithAnnotationParameter() {
        return FieldTypeWithAnnotationParameter;
    }

    public void setFieldTypeWithAnnotationParameter(HashMap<String, String> fieldTypeWithAnnotationParameter) {
        FieldTypeWithAnnotationParameter = fieldTypeWithAnnotationParameter;
    }

    public HashMap<String, String> getFieldNamesWithAnnotationParameter() {
        return fieldNamesWithAnnotationParameter;
    }

    public void setFieldNamesWithAnnotationParameter(HashMap<String, String> fieldNamesWithAnnotationParameter) {
        this.fieldNamesWithAnnotationParameter = fieldNamesWithAnnotationParameter;
    }

    public HashMap<String, Method> getSetterForFieldsWithAnnotationParameters() {
        return setterForFieldsWithAnnotationParameters;
    }

    public void setSetterForFieldsWithAnnotationParameters(HashMap<String, Method> setterForFieldsWithAnnotationParameters) {
        this.setterForFieldsWithAnnotationParameters = setterForFieldsWithAnnotationParameters;
    }

    public Class<?> getTheClass() {
        return theClass;
    }

    public void setTheClass(Class<?> theClass) {
        this.theClass = theClass;
    }


    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }



}
