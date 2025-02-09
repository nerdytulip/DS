package com.company.Greedy;

import java.util.*;

public class HuffmanEncoding {
    class Node {
        int data;
        char ch;
        Node left, right;

        Node(int data, char ch) {
            this.data = data;
            this.ch = ch;
            left = null;
            right = null;
        }
    }

    class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.data == o2.data) return Character.compare(o1.ch, o2.ch);
            return Integer.compare(o1.data, o2.data);
        }
    }

    class Solution {
        public ArrayList<String> huffmanCodes(String S, int f[], int N) {
            PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

            // Step 1: Build priority queue
            for (int i = 0; i < N; i++) {
                pq.add(new Node(f[i], S.charAt(i)));
            }

            // Edge case: If there's only one character, return ["0"]
            if (N == 1) {
                ArrayList<String> ans = new ArrayList<>();
                ans.add("0");
                return ans;
            }

            // Step 2: Build Huffman Tree
            while (pq.size() > 1) {
                Node left = pq.poll();
                Node right = pq.poll();

                // Internal node does not store a character
                Node newNode = new Node(left.data + right.data, '\0');
                newNode.left = left;
                newNode.right = right;
                pq.add(newNode);
            }

            // Get the root
            Node root = pq.poll();
            Map<Character, String> huffmanMap = new HashMap<>();
            generateCodes(root, "", huffmanMap);

            // Step 3: Retrieve Huffman codes in the order of characters in S
            ArrayList<String> ans = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                ans.add(huffmanMap.get(S.charAt(i))); // Ensure order matches S
            }

            return ans;
        }

        private void generateCodes(Node root, String temp, Map<Character, String> huffmanMap) {
            if (root == null) return;

            // If it's a leaf node, store the code
            if (root.left == null && root.right == null) {
                huffmanMap.put(root.ch, temp);
                return;
            }

            // Recursively assign "0" to left and "1" to right
            generateCodes(root.left, temp + "0", huffmanMap);
            generateCodes(root.right, temp + "1", huffmanMap);
        }
    }

}
