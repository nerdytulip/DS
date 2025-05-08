package com.company.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {

    /**
     * https://leetcode.com/problems/simplify-path/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=qYlHrAKJfyA
     * */
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");

        for (String part : components) {
            if (part.equals("") || part.equals(".")) {
                continue; // skip empty and current dir
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop(); // go up one directory
                }
            } else {
                stack.push(part); // go deeper
            }
        }

        // Construct the simplified path
        StringBuilder simplified = new StringBuilder();
        while (!stack.isEmpty()) {
            simplified.insert(0, "/" + stack.pop());
        }

        return simplified.length() > 0 ? simplified.toString() : "/";
    }
}
