package fr.lernejo.umlgrapher;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

public class GraphRepresentation {
    private final Class<?> parentClass;
    private final List<Class<?>> interfaces;
    private final String name;
    private final String packageName;
    private final String type;
    private final UmlRelation relation;
    private final List<Field> fields;
    private final List<Method> methods;

    public GraphRepresentation(Class<?> aClass, String type) {
        this.name = aClass.getSimpleName();
        this.packageName = aClass.getPackageName();
        this.parentClass = aClass.getSuperclass();
        this.interfaces = List.of(aClass.getInterfaces());
        this.type = type;
        this.relation = new UmlRelation(this);
        this.fields = List.of(aClass.getDeclaredFields());
        this.methods = List.of(aClass.getDeclaredMethods());
    }

    public String name() {
        return this.name;
    }

    public String packageName() {
        return this.packageName;
    }

    public String getType() {
        return this.type;
    }

    public Collection<? extends Class<?>> getAllInterfaces() {
        List<Class<?>> interfaces = null;
        if (this.getInterfaces() == null || this.getInterfaces().size() == 0)
            return this.getInterfaces();
        else {
            for (int i = 0; i < this.getInterfaces().size(); i++)
                interfaces.addAll(new GraphRepresentation(this.getInterfaces().get(i),
                    new UmlType(this.getInterfaces().get(0)).type()).getAllInterfaces());
            return interfaces;
        }
    }

    public List<Class<?>> getInterfaces() {
        return this.interfaces;
    }

    public Class<?> getSuperClass() {
        return this.parentClass;
    }

    public UmlRelation getRelation() {
        return relation;
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public List<Method> getMethods() {
        return methods;
    }
}
