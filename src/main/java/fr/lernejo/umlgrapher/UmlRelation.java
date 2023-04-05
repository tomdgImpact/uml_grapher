package fr.lernejo.umlgrapher;

import java.util.List;

public class UmlRelation {
    private final String [] interfaceParente;
    public UmlRelation(GraphRepresentation graphRepresentation){
        this.interfaceParente = this.getInterfaceParentes(graphRepresentation);
    }

    public String[] getInterfaceParentes(GraphRepresentation graphRepresentation){
        String[] output = new String[graphRepresentation.getInterfaces().size()];
        if(graphRepresentation.getInterfaces() != null && graphRepresentation.getInterfaces().size() >0){
            List<Class<?>> result = graphRepresentation.getInterfaces();
            for (int i=0; i< graphRepresentation.getInterfaces().size(); i++)
                output[i] = result.get(i).getSimpleName();
        }
        return output;
    }

    public String[] getInterfaceParente() {
        return interfaceParente;
    }
}
