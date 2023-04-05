package fr.lernejo.umlgrapher;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.util.*;

public class UmlGraph {
    private final List<Class<?>> umlClass;
    private final Set<GraphRepresentation> graph = new TreeSet<>(Comparator.<GraphRepresentation, String>comparing(t->t.name()).thenComparing(t->t.packageName()));
    public UmlGraph(List<Class<?>> umlClass){
        this.umlClass = umlClass ;
    }
    public final String as( GraphType graphType)
    {
        if (graphType.equals(GraphType.Mermaid)) {
            this.buildGraph(GraphType.Mermaid);
            return new MermaidFormat().format(this.graph.toArray(new GraphRepresentation[this.graph.size()]));
        }else
            return "Only Mermaid Type can be apply actually";
    }
    public void buildGraph(GraphType graphType ){
        GraphRepresentation graphRepresentation;
        UmlRelation umlRelation = null ;
        UmlType umlType = null ;
        List<Class<?>> classes = new ArrayList<>();
        for (int i=0; i< this.umlClass.size(); i++) {
            umlType = new UmlType(this.umlClass.get(i));
            graphRepresentation = new GraphRepresentation(this.umlClass.get(i), umlType.type());
            this.graph.add(graphRepresentation);
            List<Class<?>> interfaces = (List<Class<?>>) graphRepresentation.getAllInterfaces().stream().toList();
            buildInternalClass(interfaces);
            classes.add(this.umlClass.get(i));
            classes.addAll(interfaces);
        }

        buildChildGraph(classes);

    }
    public void buildChildGraph(List<Class<?>> classes)
    {
        if (classes !=null && classes.size() > 0) {
            List<Class<?>> newParentsClasses = null;
            for (int i = 0; i < classes.size(); i++) {
                List<Class<?>> childClasses = getAllChildClass(classes.get(i));
                for (int j = 0; j < childClasses.size(); j++) {
                    boolean present = false;
                    for (int k = 0; k < classes.size(); k++)
                        if (childClasses.get(j) == classes.get(k))
                            present = true;
                    if (present==false) {
                        UmlType umlType = new UmlType(childClasses.get(j));
                        GraphRepresentation graphRepresentation = new GraphRepresentation(childClasses.get(j), umlType.type());
                        this.graph.add(graphRepresentation);
                        newParentsClasses.addAll(Collections.singleton(childClasses.get(j)));
                    }
                }
            } buildChildGraph(newParentsClasses);
        }
    }
    public List<Class<?>> getAllChildClass(Class<?> umlClass){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .forPackage("")
            .forPackage("", umlClass.getClassLoader()) );
        Set<Class<?>> subTypes = reflections.get(
            Scanners.SubTypes
                .get(umlClass)
                .asClass(this.getClass().getClassLoader(), umlClass.getClassLoader())
        );
        return List.of(subTypes.toArray(new Class[subTypes.size()]));
    }
    private void buildInternalClass(List<Class<?>> classes)
    {
        GraphRepresentation graphRepresentationInterface;
        UmlType umlTypeInterface = null ;
        if(classes != null)
        {
            for(int j = 0; j < classes.size(); j++)
            {
                umlTypeInterface = new UmlType(classes.get(j));
                graphRepresentationInterface = new GraphRepresentation(classes.get(j), umlTypeInterface.type());
                this.graph.add(graphRepresentationInterface);
            }
        }
    }
    public Set<GraphRepresentation> getGraph(){
        return this.graph;
    }

}
