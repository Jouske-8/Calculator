package com.example.mycalculator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.StringBuilder;
import java.util.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class PostfixCalculator {

    // Function to convert infix expression to postfix

        // Function to convert infix expression to postfix (returns ArrayList of Strings)
    static public ArrayList<String> infixToPostfix(String expression) {
        ArrayList<String> result = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If the character is a digit, handle it as an operand
            if (Character.isDigit(c)) {
                StringBuilder operand = new StringBuilder();
                // Handle multi-digit numbers by checking consecutive digits
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    operand.append(expression.charAt(i));
                    i++;
                }
                i--; // Decrement i to avoid skipping the next character
                result.add(operand.toString());
            }
            // If the character is an operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.add(String.valueOf(stack.pop()));
                }
                stack.push(c);
            }
        }

        // Pop all the remaining operators from the stack
        while (!stack.isEmpty()) {
            result.add(String.valueOf(stack.pop()));
        }

        return result;
    }

    // Function to evaluate a postfix expression
    // Function to evaluate a postfix expression (ArrayList<String> input)
    static public int evaluatePostfix(ArrayList<String> postfix) {
        Stack<Integer> stack = new Stack<>();

        // Iterate through each element in the ArrayList
        for (String token : postfix) {
            // If the token is an operand, push it onto the stack
            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
            }
            // If the token is an operator, pop two operands from the stack and apply the operator
            else {
                int val2 = stack.pop();
                int val1 = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(val1 + val2);
                        break;
                    case "-":
                        stack.push(val1 - val2);
                        break;
                    case "*":
                        stack.push(val1 * val2);
                        break;
                    case "/":
                        if (val2 == 0) {
                            MainActivity.mathError = true;
                            return 0;  // Return 0 in case of division by zero
                        }
                        stack.push(val1 / val2);
                        break;
                }
            }
        }

        // The final result is the only element left in the stack
        return stack.pop();
    }

        // Helper function to check if a string is a numeric value
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


            // Helper function to determine precedence of operators
    static private int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    static public String addZeroAtStart(String temp){
        return "0" + temp;
    }
}

public class MainActivity extends AppCompatActivity {

    Stack<Integer> stack = new Stack<>();
    StringBuilder inputVal = new StringBuilder();
    TextView outputString;
    static boolean mathError = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        outputString = findViewById(R.id.textView2);
        Button deleteButton = findViewById(R.id.del);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This code will execute when the button is clicked normally
                opPop(v);
            }
        });

        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // This code will execute when the button is long-pressed
                allClear(v);
                return true;  // Return true to indicate that the event is consumed
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void allClear(View v){
        stack.clear();
        inputVal.setLength(0);
        outputString.setText(inputVal.toString());
    }
    public void add7(View v) {
        inputVal.append("7");
        outputString.setText(inputVal.toString());
    }
    public void add4(View v) {
        inputVal.append("4");
        outputString.setText(inputVal.toString());
    }
    public void add1(View v) {
        inputVal.append("1");
        outputString.setText(inputVal.toString());
    }
    public void add00(View v) {
        inputVal.append("00");
        outputString.setText(inputVal.toString());
    }
    public void opPercent(View v) {
        inputVal.append("/100");
        outputString.setText(inputVal.toString());
    }
    public void add8(View v) {
        inputVal.append("8");
        outputString.setText(inputVal.toString());
    }
    public void add5(View v) {
        inputVal.append("5");
        outputString.setText(inputVal.toString());
    }
    public void add2(View v) {
        inputVal.append("2");
        outputString.setText(inputVal.toString());
    }
    public void add0(View v) {
        inputVal.append("0");
        outputString.setText(inputVal.toString());
    }
    public void opPop(View v) {
        if(inputVal.length() == 0) return;

        inputVal.deleteCharAt(inputVal.length() - 1);
        outputString.setText(inputVal.toString());
    }
    public void add9(View v) {
        inputVal.append("9");
        outputString.setText(inputVal.toString());
    }
    public void add6(View v) {
        inputVal.append("6");
        outputString.setText(inputVal.toString());
    }
    public void add3(View v) {
        inputVal.append("3");
        outputString.setText(inputVal.toString());
    }
    public void addFloatingPoint(View v) {
        Toast.makeText(this, "No Operation Defined", Toast.LENGTH_SHORT).show();
    }
    public void opDivide(View v) {
        inputVal.append("/");
        outputString.setText(inputVal.toString());
    }
    public void opMultiply(View v) {
        inputVal.append("*");
        outputString.setText(inputVal.toString());
    }
    public void opSubtract(View v) {
        inputVal.append("-");
        outputString.setText(inputVal.toString());
    }
    public void opAddition(View v) {
        inputVal.append("+");
        outputString.setText(inputVal.toString());
    }
    public void opPrintValue(View v) {
        String temp = inputVal.toString();
        //Check if the the string start with minus
        if(temp.charAt(0) == '-'){
            temp = PostfixCalculator.addZeroAtStart(temp);
        }
        if(temp.charAt(0) == '+'){
            temp = PostfixCalculator.addZeroAtStart(temp);
        }
        if(temp.charAt(0) == '*' || temp.charAt(0) == '/'){
            allClear(v) ;
            outputString.setText("Syntax Error");
            return;
        }
        ArrayList<String> temp2 = PostfixCalculator.infixToPostfix(temp);
        int ans = PostfixCalculator.evaluatePostfix(temp2);
        if (mathError) {
            allClear(v);
            outputString.setText("Math Error");
            return;
        }
        inputVal = new StringBuilder(String.valueOf(ans));
        outputString.setText(inputVal.toString());
    }

}