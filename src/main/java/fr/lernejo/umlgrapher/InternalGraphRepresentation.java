package fr.lernejo.umlgrapher;

import java.util.LinkedList;
import java.util.List;

public class InternalGraphRepresentation {
    private final Class classinternal;

    public InternalGraphRepresentation(Class classinternal) {
        this.classinternal = classinternal;
    }
    public List<Class> searchlist(List<Class> search, Class ... unknowclass){
        for(Class research: unknowclass){
            if(!search.contains(research)){
                search.add(research);
                search = searchlist(search,research.getInterfaces());}
        }
        return search;}
    public List<Class> giverelation(){
        List<Class> list = new LinkedList<>();
        list= searchlist(list,this.classinternal);
        return list;
    }

}
