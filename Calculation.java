package day04;

public class Calculation {
    //Declaration
    private Float operand1;
    private Float operand2;
    private String operator;
    //Constructor
    public Calculation(Float operand1,Float operand2, String operator){
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }
    //Getter for result
    public String getResult(){
        if (operator.equals("+")){
            return Float.toString(operand1+operand2);
        } else if (operator.equals("-")){
            return Float.toString(operand1-operand2);
        } else if (operator.equals("*")){
            return Float.toString(operand1*operand2);
        } else if (operator.equals("/")){
            return Float.toString(operand1/operand2);
        } else {
            return "No such operator!";
        }
    }
}
