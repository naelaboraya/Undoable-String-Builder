import java.util.Objects;
import java.util.Stack;

/**
 * This class describes a mutable and modifiable String , the length of the String can be changed throughout method calls.
 * The main purpose of this class is to give the ability to take a step backwards while doing operations on strings by calling a method.
 * This additional method called <strong>undo</strong> , implemented with the helping of <strong>Stack</strong> data structure.
 *<p>
 *For example, if {@code s} refers to a {@code UndoableStringBuilder} object
 * whose current contents are "{@code abcd}" , and the previous contents
 * were "{@code ab}" (before appending) , then the method call {@code s.undo()}
 * would cause the {@code UndoableStringBuilder} to contain "{@code ab}" (like how the String was before).
 * @see StringBuilder
 * @author Nael Aboraya , Marwan Hresh
 * @version 1
 */
public class UndoableStringBuilder {



    private StringBuilder sb;//Attribute of type StringBuilder that the operations will be done on it
    private Stack<UndoableStringBuilder> Undo_Stack;//Stack used for undo operation , after every method call , the new string will be added to the stack

    private boolean Is_String_Constructor_used = false;//boolean attribute for the object to check if it was constructed with the second constructor (String constructor)


    /**
     * Constructs a string builder with no characters in it.
     * <p>plus initializing the undo stack.
     */
    public UndoableStringBuilder(){
        this.sb = new StringBuilder();
        Undo_Stack = new Stack<>();
    }


    /**
     * Constructs a string builder with the content of the
     * specified String.
     * <p>plus initializing the undo stack and adding the current String to it.
     * <p><Strong>Note that {@code undo} method doesn't work with object that was constructed
     * with this constructor and has only the initial String given to it ({@code S}) ({@code undo} method
     * will keep the given String as is).
     * @param S  the given String.
     */
    public UndoableStringBuilder(String S){
        this.Is_String_Constructor_used = true;
        this.Undo_Stack = new Stack<>();//initializing the undo stack
        char[] first = String_to_chararray(S);//first state of string (S)
        UndoableStringBuilder firstundo = new UndoableStringBuilder(first);//converting to UndoableStringBuilder
        this.Undo_Stack.add(firstundo);//adding (UndoableStringBuilder) S to the undo stack
        this.sb = new StringBuilder();
        this.sb.append(S);

    }


    /* A private constructor , made to avoid mistakes when creating a new object using it in the public constructor
    (to add first new String to the stack)*/
    private UndoableStringBuilder(char[] chararray){
        this.sb = new StringBuilder(String.valueOf(chararray));
    }


    /*A private helping method , converts the given String to chararray
     used in the private constructor parameter */
    private char[] String_to_chararray(String s){
        int len = s.length();
        char[] ans = new char[len];
        ans = s.toCharArray();
        return ans;
    }

    /*A private helping method that will be used in the next methods.
    adds the current state of string of this object to the undo stack.*/
    private void Add_to_undo_stack(){
        UndoableStringBuilder sS = new UndoableStringBuilder(sb.toString());
        Undo_Stack.add(sS);
    }


    /**
     * Adds the specified string to the end of this string.
     *<p>The method also adds the new String to the undo stack.
     * @param S the given String.
     * @return a reference to this object.
     */
    public UndoableStringBuilder append(String S) {
       sb.append(S);
       Add_to_undo_stack();

       return this;
    }


    /**
     * Deletes the Substring starting from {@code start}
     * to {@code end-1} indices of this string, or to the end of the String if no such character exists
     * <p>The method also adds the new String to the undo stack.
     * @param start start index of the substring to delete (inclusive).
     * @param end end index of substring to delete (exclusive).
     * @return a reference to this object.
     * @throws StringIndexOutOfBoundsException if {@code start}
     * is negative, greater than {@code length()}, or
     * greater than {@code end}.
     */
    public UndoableStringBuilder delete(int start , int end) throws StringIndexOutOfBoundsException {
        sb.delete(start,end);
        Add_to_undo_stack();
        return this;
    }


    /**
     * Inserts the specified String into this String , at the indicated offset, moving up any
     * characters originally above that position and increasing the length
     * of this sequence by the length of the argument.
     * @param offset the offset.
     * @param str the given String.
     * @return a reference of this object.
     * @throws
    StringIndexOutOfBoundsException if the offset is invalid.
     */
    public UndoableStringBuilder insert(int offset , String str)throws StringIndexOutOfBoundsException{
        sb.insert(offset,str);
        Add_to_undo_stack();
        return this;
    }


    /**
     * Replaces the Substring starting from {@code start}
     * to {@code end-1} indices of this string with
     * the specified String.
     * <p>The method also adds the new String to the undo stack.
     * @param start start index of the substring to replace (inclusive).
     * @param end end index of the substring to replace (exclusive).
     * @param str the given String.
     * @return a reference to this object.
     * @throws StringIndexOutOfBoundsException if start is negative, greater than length(), or greater than end.
     * @throws NullPointerException if str is null.
     */
    public UndoableStringBuilder replace (int start , int end , String str)throws StringIndexOutOfBoundsException,NullPointerException{
        sb.replace(start,end,str);
        Add_to_undo_stack();
        return this;
    }


    /**
     * Reverses the String.
     * <p>The method also adds the new String to the undo stack.
     * @return a reference to this object.
     */
    public UndoableStringBuilder reverse(){
        sb.reverse();
        Add_to_undo_stack();
        return this;
    }

    /**
     * Makes this object's String back to its previous state.
     * <p>For example if S refers to a {@code UndoableStringBuilder} object
     * with the String currently "java" , if we append "2023" to it
     * the String will be "java2023" , by calling this method
     * (S.undo()) the String of the object S will return to "java"
     * (the last state it was before appending "2023").
     * <p>when calling this method with an empty String
     * the method returns an empty String.
     * <p>
     * This method calls {@code Undo} method that works with the {@code Undo_Stack} ,
     * it deletes the top of the stack (the previous state of the String) by calling
     * method {@code pop} , gives the new top of the stack to {@code sb}
     * (the {@code StringBuilder} attribute of this object). The {@code Undo}
     * method handle the exceptional cases where the stack is empty or of size 1.
     * <p><Strong>Note that when calling this method with an object that was constructed
     * with String constructor and has its first state given to it ({@code S} in String constructor),
     * the method will not return empty String , it will keep the String as is.
     * <p>This method is a void method (calls a helping method that
     * returns a reference to this object).
     */
    public void undo(){
        Undo();
    }

    private UndoableStringBuilder Undo(){
        if (Undo_Stack.size()==1&&Is_String_Constructor_used==true){//handling the case when there is one String in the stack and the object was constructed with the String constructor
            return this;
        }
        else if (Undo_Stack.size()==1&&Is_String_Constructor_used==false){//handling the case when there is one String in the stack
            Undo_Stack.pop();
            this.sb = new StringBuilder();
            return this;
        }
        else if (Undo_Stack.isEmpty()){//checking if the stack is empty
            this.sb.append("");
            return this;
        }
        //else
        Undo_Stack.pop();//removing the top of the stack
        UndoableStringBuilder ans = Undo_Stack.peek();//checking/seeing the top of the stack
        this.sb = new StringBuilder(ans.toString());//giving this object the string value of the top of the stack

        return this;
    }

    /**
     * This method prints  the {@code Undo_Stack} to show all previous states of this object's String.
     */
    public void printStack(){//mainly for testing
        System.out.println(this.Undo_Stack.toString());
    }

    /**
     * equals() is a method defined in the Object class thus the default implementation of the .
     * equals() method compares the object references or the memory location where the objects are stored in the heap.
     * Thus by default the .equals() method checks the object by using the “==” operator.
     * <p>This method overrides the equals() method that defined in Object class , it compares the string values
     * of two objects by equals() method in class String , if they are the same , the method returns true , otherwise,
     * returns false.
     * <p>The method checks if the specified object is null , if not , it checks
     * if the specified object's class is {@code String} or
     * {@code UndoableStringBuilder} , note that it will return false
     * if the specified object is neither {@code String} nor {@code UndoableStringBuilder} , even though  its
     * string content may actually be equal to this object string content.
     * <p>
     *<strong>This methode is mainly implemented for testing (checking if two strings are equal).</strong>
     * @param o the given object to compare this object with.
     * @return boolean value.
     */
    @Override
    public boolean equals(Object o) {//For testing
        if(o==null){
            if(this.sb.toString()==null) return true;
            return false;
        }
        else if(o.getClass()==String.class) {//checking if the specified object is of type String
            if (this.sb.toString().equals((String) o)){return true;}
            return false;}

        else if(o.getClass()== UndoableStringBuilder.class){//checking if the specified object is of type UndoableStringBuilder
            if (this.sb.toString().equals(o.toString())){return true;}
            return false;}

        return false;//else...(any other class the answer will be false)
    }

    @Override//with equals().
    public int hashCode() {
        return Objects.hash(sb);
    }

    @Override//overrides the toString() method in Object class , this method represents this object in String format.
    public String toString(){
        return sb.toString();
    }



}
