# Undoable-String-Builder
### Implementing string builder with undo operation in java.
<p>
This repository has a class that describes a mutable and modifiable String , the length of the String can be changed throughout method calls.
The main purpose of this class is to give the ability to take a step backwards while doing operations on strings by calling a method.
This additional is method called <strong>undo</strong> , implemented with the helping of <strong>Stack</strong> data structure.
 <p>
  For example, if  <strong>s</strong> refers to a <strong>UndoableStringBuilder</strong> object whose current contents are <strong>"abcd"</strong> , and the previous contents
  were <strong>"ab"</strong> (before appending) , then the method call  <strong>s.undo()</strong> 
  would cause the <strong>UndoableStringBuilder</strong> to contain <strong>"ab"</strong>  (like how the String was before).
  
 
   
  
