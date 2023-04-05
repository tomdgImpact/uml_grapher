package fr.lernejo.umlgrapher;

import java.lang.reflect.Modifier;
import java.util.Set;

public class MermaidFormatter {
    private final Set<UmlType> namegraph;

    public MermaidFormatter(Set<UmlType> namegraph) {
        this.namegraph = namegraph;
    }
    public String Classformat(){
        String insert = "classDiagram\n";
        for(UmlType typegraph:namegraph) {
            insert += "class " + typegraph.name();
            if (Modifier.isInterface(typegraph.classname().getModifiers())) {
                insert += " {\n    <<interface>>\n}";
            }
            insert+="\n";
        }

        return insert;}
}
