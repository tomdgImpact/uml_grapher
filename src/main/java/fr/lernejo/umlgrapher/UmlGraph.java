package fr.lernejo.umlgrapher;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UmlGraph {
    private final Class[] name;
    public UmlGraph(final Class ... Nameclass){
        this.name = Nameclass;
    }
    private final Set<UmlType> types = new TreeSet<>(Comparator
        .<UmlType, String>comparing(t->t.name())
        .thenComparing(t->t.packageName()));

    public String as(GraphType typegraph) {
        List<Class> listenter = null;
        for(Class graphname: name) {
            if (typegraph == GraphType.Mermaid) {
                listenter = new InternalGraphRepresentation(graphname).giverelation();
                for (Class Tempo: listenter){
                    types.add(new UmlType(Tempo));}
            }
        }
        String relationclass = new UmlRelation(types).Relationformat();
        String returnvalue = new MermaidFormatter(types).Classformat();
        return returnvalue + relationclass;}
}
