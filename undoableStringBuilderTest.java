import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is only for testng the public methods of class {@code UndoableStringBuilder}.
 * <p>In the test cases, the method {@code equals} that was implemented in
 * {@code UndoableStringBuilder} class is used to check/compare results.
 * <p>Note that {@code assertEquals} method works with {@code equals} method that was overridden
 * in {@code UndoableStringBuilder} class.
 * @see UndoableStringBuilder
 * @author Nael Aboraya , Marwan Hresh
 */
class undoableStringBuilderTest {
    //some objects to do tests on them
     UndoableStringBuilder ssb = new UndoableStringBuilder();
     UndoableStringBuilder ssb1 = new UndoableStringBuilder("");
     UndoableStringBuilder usb = new UndoableStringBuilder();//the undoable string builder example in the assignment
     UndoableStringBuilder nl = new UndoableStringBuilder();


    @Test
    public void Quick_UndoableStringBuilder_Demo(){
        ssb.undo();
        assertEquals("",ssb.toString());
        assertTrue(ssb.equals(""));
        assertEquals(ssb,ssb1);
        nl.append(null);
        assertEquals("null",nl.toString());
        UndoableStringBuilder SB = new UndoableStringBuilder();
        SB.append("Hello There").
                replace(7,11,"World").
                delete(5,7);
        assertEquals( ("HelloWorld"),SB.toString());
        assertTrue("HelloWorld".equals(SB.toString()));
        assertTrue(SB.equals("HelloWorld"));
        SB.reverse();
        assertEquals(("dlroWolleH"),SB.toString());
    }

    @Test
     void test_Exceptions(){
        UndoableStringBuilder test = new UndoableStringBuilder();
        test.append("thisistest");
        //testing delete method , should throw StringIndexOutOfBoundsException if the input is not suitable
        assertThrows(StringIndexOutOfBoundsException.class , ()->{
            test.delete(-4,1);//start is negative
        });
        assertThrows(StringIndexOutOfBoundsException.class , ()->{
            test.delete(10,4);//start is greater than length
        });
        assertThrows(StringIndexOutOfBoundsException.class,()->{
            test.delete(2,1);//start is greater than end
        });
        //testing replace method , should throw StringIndexOutOfBoundsException if the input is not suitable
        assertThrows(StringIndexOutOfBoundsException.class , ()->{
            test.replace(-4,1,"testreplace");//start is negative
        });
        assertThrows(StringIndexOutOfBoundsException.class , ()->{
            test.replace(10,4,"testreplace");//start is greater than length
        });
        assertThrows(StringIndexOutOfBoundsException.class,()->{
            test.replace(2,1,"testreplace");//start is greater than end

        });
        //testing the insert method , should throw StringIndexOutOfBoundsException if the input is not suitable
        assertThrows(StringIndexOutOfBoundsException.class , ()->{
            test.insert(-4,"testinsert");//offset is invalid
        });
        assertThrows(StringIndexOutOfBoundsException.class , ()->{
            test.insert(50,"testinsert");//offset is invalid
        });
        //testing replace method with null parameter , should throw NullPointerException
        assertThrows(NullPointerException.class,()->{
           test.replace(1,4,null);
        });
    }
    @Test
     void append() {
        ssb.append("abc");
        ssb1.append("abc");
        String s = "abc";
        assertTrue(ssb.equals("abc"));
        assertTrue(ssb1.equals(s));
        assertTrue(ssb.equals(ssb1));
        ssb.append(ssb1.toString());
        UndoableStringBuilder ssb2 = new UndoableStringBuilder("abcabc");
        assertEquals(ssb2,ssb);//abcabc is the two strings combined
        assertEquals("abc",ssb1.toString());
        ssb1.append(null);//works like StringBuilder , adds the character 'n','u','l','l'
        assertEquals("abcnull",ssb1.toString());// should be abcnull
        assertEquals(ssb1,"abcnull");
        ssb2.append("aa");
        String test = ssb2.toString();
        assertEquals(ssb2,test);
        usb.append("to be or not to be");
        assertTrue(usb.equals("to be or not to be"));

    }


    @Test
    void delete() {
        UndoableStringBuilder SB = new UndoableStringBuilder("abcdefg");
        SB.delete(2,4);
        assertEquals(("abefg") , SB.toString());
        SB.delete(2,3);
        assertEquals("abfg", SB.toString());

    }

    @Test
    void insert() {
        UndoableStringBuilder SB = new UndoableStringBuilder("000000");
        SB.insert(3,"4567");
        assertTrue(SB.toString().equals("0004567000"));
    }

    @Test
     void replace() {
        //example from the assignment test (main)
        usb.append("to be or not to be");
        usb.replace(3, 5, "eat") ;
        assertFalse(!usb.equals("to eat or not to be"));
        usb.replace(17, 19, "eat");
        assertTrue(usb.equals("to eat or not to eat"));
        assertEquals("to eat or not to eat",usb.toString());
    }

    @Test
    void reverse() {
        UndoableStringBuilder SB = new UndoableStringBuilder("Nael");
        SB.reverse();
        assertEquals("leaN",SB.toString());
        SB.reverse();
        assertEquals("Nael",SB.toString());

        //example from the assignment test (main)
        usb.append("to be or not to be") ;
        usb.replace(3, 5, "eat") ;
        usb.replace(17, 19, "eat") ;
        usb.reverse();
        assertTrue(usb.equals("tae ot ton ro tae ot"));

    }

    @Test
    void undo() {
        //example from the assignment test (main)
        usb.append("to be or not to be") ;//"to be or not to be"
        usb.replace(3, 5, "eat") ;//"to eat or not to be"
        usb.replace(17, 19, "eat") ;//"to eat or not to eat"
        usb.reverse();//"tae ot ton ro tae ot"

        usb.undo();//first undo , the String should be back to previous state
        assertTrue(usb.equals("to eat or not to eat"));//should be : "to eat or not to eat"

        usb.undo();//second undo
        assertEquals("to eat or not to be",usb.toString());//should be "to eat or not to be"

        usb.undo();//third undo
        assertEquals("to be or not to be",usb.toString());//should be "to be or not to be"

        usb.undo();//another undo
        assertEquals("",usb.toString());//should be empty string

        usb.undo();//another undo
        assertNotNull(usb);//shouldn't be null , just empty string
        assertEquals("",usb.toString());//should be empty string

        usb.append("aaaa");
        usb.undo();
        assertFalse((usb.equals(null)));
        assertTrue(usb.equals(""));

        UndoableStringBuilder example = new UndoableStringBuilder();
        example.append("seven");
        example.append("five");
        example.undo();
        example.undo();
        example.append("green");
        example.delete(2,3);
        example.insert(1,"aaaaaa");
        example.replace(1,4,"bbbb");

        int i =0;
        String output = new String();
        while (i<6){
            example.undo();
            output+=example+"\n";
            i++;
        }
        assertEquals("gaaaaaaren\n" +
                "gren\n" +
                "green\n" +
                "\n" +
                "\n" +
                "\n",output);

        //Testing undo() method on String that was constructed with String constructor
        UndoableStringBuilder sb = new UndoableStringBuilder("String");
        assertEquals("String",sb.toString());//should be back to first state ("String") , and not ""
        sb.reverse();
        assertTrue(sb.equals("gnirtS"));
        sb.undo();
        assertEquals("String",sb.toString());
        sb.undo();
        assertEquals("String",sb.toString());//should be back to first state ("String") , and not ""

    }
}