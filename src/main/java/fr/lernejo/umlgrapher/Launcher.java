package fr.lernejo.umlgrapher;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "Mermaid Graph", mixinStandardHelpOptions = true, version = "Graph 1.0",
    description = "Trying to build mermaid graph")

public class Launcher implements Callable<Integer> {
    @CommandLine.Option(names = {"-c", "--classes"}, required = true, description = "Precise the classe where to build graph")
    private final List<Class<?>> className = new ArrayList<>();

    @CommandLine.Option(names = {"-g", "--graph-type"}, description = "Precise graph type to build, default is Mermaid",
        defaultValue = "Mermaid")
    private final GraphType type = GraphType.Mermaid;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        UmlGraph umlGraph = new UmlGraph(className);
        System.out.println(umlGraph.as(type));
        return 0;
    }
}
