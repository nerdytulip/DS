package com.company.Graph;

import java.util.HashMap;

public class CloneGraph {
    /**
     *https://leetcode.com/problems/clone-graph/description/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=Nki9V1tD5_I
     * */
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        HashMap<Node,Node> map = new HashMap<>();
        return CloneUtil(node,map);
    }

    private Node CloneUtil(Node node, HashMap<Node, Node> map) {
        Node newNode = new Node(node.val);
        map.put(node, newNode);

        for(Node neighbour : node.neighbors){
            if(!map.containsKey(neighbour)){
                newNode.neighbors.add(CloneUtil(neighbour,map));
            } else{
                newNode.neighbors.add(map.get(neighbour));
            }
        }

        return newNode;
    }
}
