# Undoable-String-Builder
### Implementing string builder with undo operation in java.
<p>
This class describes a mutable and modifiable String , the length of the String can be changed throughout method calls.
The main purpose of this class is to give the ability to take a step backwards while doing operations on strings by calling a method.
This additional method called <strong>undo</strong> , implemented with the helping of <strong>Stack</strong> data structure.
 <p>
  For example, if  <strong>s</strong> refers to a **UndoableStringBuilder** object whose current contents are **"abcd"** , and the previous contents
  were **"ab"** (before appending) , then the method call  **s.undo()**
  would cause the **UndoableStringBuilder** to contain **"ab"** (like how the String was before).
   
   zobre**is**bold
