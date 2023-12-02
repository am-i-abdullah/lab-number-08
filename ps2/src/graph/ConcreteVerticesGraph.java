package graph;

import java.util.*;

public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    public ConcreteVerticesGraph() {
    }

    private void checkRep() {
        for (Vertex vertex : vertices) {
            assert vertex != null;
        }
    }

    @Override
    public boolean add(String vertex) {
        for (Vertex _vertex : vertices) {
            if (_vertex.getLabel().equals(vertex)) return false;
        }
        vertices.add(new Vertex(vertex));
        checkRep();
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        int oldWeight = 0;

        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(source)) {
                for (Vertex vertex2 : vertices) {
                    if (vertex2.getLabel().equals(target)) {
                        oldWeight = vertex.connect(target, weight);
                        checkRep();
                        return oldWeight;
                    }
                }
                vertices.add(new Vertex(target));
                oldWeight = vertex.connect(target, weight);
                checkRep();
                return oldWeight;
            }
        }

        Vertex vertex = new Vertex(source);
        vertices.add(vertex);
        for (Vertex vertex2 : vertices) {
            if (vertex2.getLabel().equals(target)) {
                oldWeight = vertex.connect(target, weight);
                checkRep();
                return oldWeight;
            }
        }
        vertices.add(new Vertex(target));
        oldWeight = vertex.connect(target, weight);
        checkRep();
        return oldWeight;
    }

    @Override
    public boolean remove(String vertex) {
        Vertex deletee = null;
        for (Vertex _vertex : vertices) {
            if (_vertex.getLabel().equals(vertex)) {
                deletee = _vertex;
                continue;
            }

            for (String label : _vertex.getMaps().keySet()) {
                if (label.equals(vertex)) _vertex.disconnect(label);
            }

        }
        checkRep();

        return deletee != null && vertices.remove(deletee);
    }

    @Override
    public Set<String> vertices() {
        Set<String> labels = new HashSet<>();
        for (Vertex vertex : vertices) {
            labels.add(vertex.getLabel());
        }
        return labels;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(target)) continue;
            for (String label : vertex.getMaps().keySet()) {
                if (label.equals(target)) sources.put(vertex.getLabel(), vertex.getWeightOfConnection(target));
            }
        }
        return sources;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targets = new HashMap<>();
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(source)) {
                targets.putAll(vertex.getMaps());
            }
        }
        return targets;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Vertex vertex : vertices) {
            result.append(vertex.toString());
        }

        return result.toString();
    }
}

class Vertex {

    private static final char[] SYMBOLS = {'@', '!', '#', '%', '^', '&', '*'};

    private final String label;
    private final Map<String, Integer> maps = new HashMap<>();

    public Vertex(String label) {
        this.label = label;
        checkRep();
    }

    public void checkRep() {
        char firstChar = this.label.charAt(0);
        boolean validSymbol = Arrays.binarySearch(SYMBOLS, firstChar) >= 0;
        assert validSymbol : "Vertex label must start with one of the allowed symbols";

        for (Integer weight : maps.values()) {
            assert weight > 0 : "Map weights must be greater than 0";
        }
    }

    public int connect(String label, Integer weight) {
        int oldWeight = 0;

        if (maps.containsKey(label)) {
            oldWeight = maps.get(label);
            maps.put(label, weight);
        } else {
            maps.put(label, weight);
        }

        checkRep();
        return oldWeight;
    }

    public boolean disconnect(String label) {
        if (maps.containsKey(label)) {
            maps.remove(label);
            checkRep();
            return true;
        }
        return false;
    }

    public String getLabel() {
        return label;
    }

    public Map<String, Integer> getMaps() {
        return new HashMap<>(maps);
    }

    public Integer getWeightOfConnection(String label) {
        return maps.get(label);
    }

    @Override
    public String toString() {
        StringBuilder connectionMap = new StringBuilder();
        connectionMap.append(getLabel()).append("\t->");

        Map<String, Integer> sortedMap = new TreeMap<>(maps);

        for (String label : sortedMap.keySet()) {
            connectionMap.append(label).append(", ");
        }

        connectionMap.append("\n");

        return connectionMap.toString();
    }
}
