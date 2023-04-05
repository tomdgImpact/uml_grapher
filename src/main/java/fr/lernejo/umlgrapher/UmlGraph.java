package fr.lernejo.umlgrapher;

import org.apache.maven.surefire.shade.org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class UmlGraph {
    private final Class<?>[] umlClass;
    private final Set<GraphRepresentation> graph = new TreeSet<>(Comparator.<GraphRepresentation, String>comparing(t->t.name()).thenComparing(t->t.packageName()));
    public UmlGraph(Class<?> []umlClass){
        this.umlClass = umlClass ;
    }
    public void buildGraph(GraphType graphType ){
        GraphRepresentation graphRepresentation;
        UmlRelation umlRelation = null ;
        UmlType umlType = null ;
        Class<?>[] classes = null;
        for (int i=0; i< this.umlClass.length; i++) {
            umlType = new UmlType(this.umlClass[i]);
            graphRepresentation = new GraphRepresentation(this.umlClass[i], umlType.type());
            this.graph.add(graphRepresentation);
            Class<?> [] interfaces = graphRepresentation.getAllInterfaces() ;
            buildInternalClass(interfaces);
            classes = ArrayUtils.addAll(classes,this.umlClass[i]);
            classes = ArrayUtils.addAll(classes,interfaces);
        }

        buildChildGraph(classes);

    }
    public void buildChildGraph(Class<?> [] classes)
    {
        if (classes !=null && classes.length>0) {
            Class<?> [] newParentsClasses = null;
            for (int i = 0; i < classes.length; i++) {
                Class<?>[] childClasses = getAllChildClass(classes[i]);
                for (int j = 0; j < childClasses.length; j++) {
                    boolean present = false;
                    for (int k = 0; k < classes.length; k++)
                        if (childClasses[j] == classes[k])
                            present = true;
                    if (present==false) {
                        UmlType umlType = new UmlType(childClasses[j]);
                        GraphRepresentation graphRepresentation = new GraphRepresentation(childClasses[j], umlType.type());
                        this.graph.add(graphRepresentation);
                        newParentsClasses = ArrayUtils.addAll(newParentsClasses,childClasses[j]);
                    }
                }
            } buildChildGraph(newParentsClasses);
        }
    }
    public Class<?>[] getAllChildClass(Class<?> umlClass){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .forPackage("")
            .forPackage("", umlClass.getClassLoader()) );
        Set<Class<?>> subTypes = reflections.get(
            Scanners.SubTypes
                .get(umlClass)
                .asClass(this.getClass().getClassLoader(), umlClass.getClassLoader())
        );
        return subTypes.toArray(new Class[subTypes.size()]);
    }
    private void buildInternalClass(Class<?>[] classes)
    {
        GraphRepresentation graphRepresentationInterface;
        UmlType umlTypeInterface = null ;
        if(classes != null)
        {
            for(int j = 0; j < classes.length; j++)
            {
                umlTypeInterface = new UmlType(classes[j]);
                graphRepresentationInterface = new GraphRepresentation(classes[j], umlTypeInterface.type());
                this.graph.add(graphRepresentationInterface);
            }
        }
    }
    public Set<GraphRepresentation> getGraph(){
        return this.graph;
    }
    public final String as( GraphType graphType)
    {
        if (graphType.equals(GraphType.Mermaid)) {
            this.buildGraph(GraphType.Mermaid);
            return new MermaidFormat().format(this.graph.toArray(new GraphRepresentation[this.graph.size()]));
        }else
            return "Only Mermaid Type can be apply actually";
    }
}
