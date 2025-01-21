package com.company.Stack;

import java.util.Stack;

public class StackBasics {
    //reverse a string using stack
    //https://www.geeksforgeeks.org/stack-set-3-reverse-string-using-stack/

    private String reverseStringUsingStack(String inputStr){
        Stack<Character> s = new Stack<>();

        for(int i=0;i<=inputStr.length();i++){
            s.push(inputStr.charAt(i));
        }

        StringBuilder ans = new StringBuilder();

        while (!s.empty()){
            char c = s.pop();
            ans.append(c);
        }

        return ans.toString();
    }

    private void solve(Stack<Character> st,int count, int size){
        if(count == size/2){
            st.pop();
            return;
        }

        char num = st.peek();
        st.pop();

        solve(st,count+1,size);

        st.push(num);
    }
    //delete middle element from stack
    //https://www.geeksforgeeks.org/stack-set-3-reverse-string-using-stack/
    private void deleteMiddleElement(Stack<Character> st){
        int n = st.size();
        int count = 0;
        solve(st,count,n);
    }

    //https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            //if opening bracket, stack push
            //if close bracket, stack top check and pop

            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            }else{
                //closing bracket
                if(!st.isEmpty()) {
                    char top = st.peek();

                    if(ch == ')' && top == '(' || ch == '}' && top == '{' || ch == ']' && top == '['){
                        st.pop();
                    }else{
                        return false;
                    }
                }
                else{
                    //if stack is empty , that means we found unbalanced
                    return false;
                }
            }
        }

        if (st.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //https://www.geeksforgeeks.org/program-to-insert-an-element-at-the-bottom-of-a-stack/

    static Stack<Integer> recur(Stack<Integer> S, int N)
    {
        // If stack is empty
        if (S.isEmpty())
            S.push(N);

        else {

            // Stores the top element
            int X = S.peek();

            // Pop the top element
            S.pop();

            // Recurse with remaining elements
            S = recur(S, N);

            // Push the previous
            // top element again
            S.push(X);
        }
        return S;
    }

    private void insertToBottom(Stack<Integer> S, int N) {

        // Recursively insert
        // N at the bottom of S
        S = recur(S, N);

        // Print the stack S
        while (S.size() > 0) {
            System.out.print(S.peek() + " ");
            S.pop();
        }
    }

    //https://www.geeksforgeeks.org/reverse-a-stack-using-recursion/
    static void insert_at_bottom(Stack<Character> st,char x)
    {

        if (st.isEmpty())
            st.push(x);

        else {

            // All items are held in Function
            // Call Stack until we reach end
            // of the stack. When the stack becomes
            // empty, the st.size() becomes 0, the
            // above if part is executed and
            // the item is inserted at the bottom
            char a = st.peek();
            st.pop();
            insert_at_bottom(st, x);

            // push allthe items held
            // in Function Call Stack
            // once the item is inserted
            // at the bottom
            st.push(a);
        }
    }

    // Below is the function that
    // reverses the given stack using
    // insert_at_bottom()
    private void reverse(Stack<Character> st)
    {
        if (st.size() > 0) {

            // Hold all items in Function
            // Call Stack until we
            // reach end of the stack
            char x = st.peek();
            st.pop();
            reverse(st);

            // Insert all the items held
            // in Function Call Stack
            // one by one from the bottom
            // to top. Every item is
            // inserted at the bottom
            insert_at_bottom(st,x);
        }
    }

    private void sortedInsert(Stack<Integer> stack, int num){
        if(stack.isEmpty() || (!stack.isEmpty() && stack.peek() < num)){
            stack.push(num);
        }

        int n = stack.peek();
        stack.pop();

        sortedInsert(stack,num);
        stack.push(n);
    }

    private void sortStack(Stack<Integer> stack){

        if(stack.isEmpty()){
            return ;
        }

        int num = stack.peek();
        stack.pop();

        sortStack(stack);

        sortedInsert(stack,num);
    }

    private boolean findRedundantBrackets(String s){
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if(c == '(' || c == '+' || c == '-' || c == '*' || c == '/'){
                stack.push(c);
            }else{
                if(c == ')'){
                    //check in between
                    boolean isRedundant = true;
                    while(stack.peek()!='('){
                        char top = stack.peek();
                        if(top == '+' || top == '-' || top == '*' || top == '/'){
                            isRedundant = false;
                        }
                        stack.pop();
                    }

                    if(isRedundant == true){
                        return true;
                    }
                    stack.pop(); // to remove '('
                }
            }
        }

        return false;
    }

    public int minAddToMakeValid(String s) {
        // total length of string having valid parenthesis : no of opening bracket == no of closing bracket
        // every close brace should have open brace before it
        // Invalid String : {{{{{{ , }}}}}}}}} , }}}}{{{{{{
        // so the total string is even
        //if string length is odd - return -1

        if(s.length()%2==1){
            return -1;
        }

        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            if(ch == '{'){
                stack.push(ch);
            }else{
                //ch is closed bracket
                if(!stack.isEmpty() && stack.peek() == '{'){
                    stack.pop();
                }
                else{
                    stack.push(ch);
                }
            }
        }

        //stack contains invalid chars
        int a = 0;
        int b = 0;

        while(!s.isEmpty()){
            if(stack.peek() == '{'){
                b++;
            }else{
                a++;
            }

            stack.pop();
        }

        int ans = (a+1)/2 + (b+1)/2;

        return ans;
    }

    private int[] nextSmallerElement(int[] arr){
        int n = arr.length;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int ans[] = new int[n];

        for(int i = n-1;i>=0;i--){
            int curr = arr[i];
            while (stack.peek()>= curr){
                stack.pop();
            }

            //ans is stack ka top
            ans[i] = stack.peek();
            stack.push(curr);
        }

        return ans;
    }
}
