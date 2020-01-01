package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        //完成一个中缀转后缀表达式
        //1+（（2+3）*4）-5
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(parseSuffixExpressionList);
//        String suffixExpression = "30 4 + 5 * 6 -";
//
//        //1. 用arrayList存储
//        //2. 将arrayList传递给一个方法，遍历配合栈计算
//        List<String> list = getListString(suffixExpression);
//        System.out.println("逆波兰表达式为： " + list);
//        int res =calculate(list);
//        System.out.println("计算结果为：" + res);

    }

    public static List<String> toInfixExpressionList(String s) {

        //定义一个List 存放中缀表达式
        List<String> ls = new ArrayList<>();
        int i = 0;  //用于遍历的指针变量
        String str;   //对多位数的并接
        char c;         //遍历存储

        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {

        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        for (String item : ls) {
            //如果是一个数，则加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符并压入s2，直到遇到左括号为止
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();     //将（弹出消除
            } else {
                //当s1栈顶优先级小于等于当前运算符
                //缺少比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;

    }

    public static List<String> getListString(String suffixExpression) {

        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成逆波兰表达式计算
    public static int calculate(List<String> ls) {

        //创建一个栈
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }

        //最后留在stack中的就是运算结果
        return Integer.parseInt(stack.pop());
    }


}

//编写Operation可以返回运算符对应优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在的运算符");
                break;
        }
        return result;
    }
}
