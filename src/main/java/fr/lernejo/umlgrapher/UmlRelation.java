package fr.lernejo.umlgrapher;

import java.util.Set;

public class UmlRelation {
    private final Set<UmlType> namerelation;
    public UmlRelation(Set<UmlType> namerelation) {
        this.namerelation = namerelation;
    }
    public String Relationformat(){
        String relationformat = "";
        for(UmlType graphname: namerelation)
        {
            Class[] obtainInterface = graphname.classname().getInterfaces();
            for(Class relationame: obtainInterface)
                if (graphname.classname().getSuperclass() == null) {
                    relationformat += relationame.getSimpleName() + " <|-- " + graphname.classname().getSimpleName()+" : extends\n";
                }
                else {
                    relationformat += relationame.getSimpleName()+ " <|.. " + graphname.classname().getSimpleName()+" : implements\n";
                }
        }
        return relationformat;
    }
}
