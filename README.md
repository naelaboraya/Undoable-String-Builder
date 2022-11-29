# Undoable-String-Builder
### Implementing string builder like the string builder in java with undo operation.
<p>
This class describes a mutable and modifiable String , the length of the String can be changed throughout method calls.
  The main purpose of this class is to give the ability to take a step backwards while doing operations on strings by calling a method.
  This additional method called <strong>undo</strong> , implemented with the helping of <strong>Stack</strong> data structure.
 <p>
 *For example, if {@code s} refers to a {@code UndoableStringBuilder} object
 * whose current contents are "{@code abcd}" , and the previous contents
 * were "{@code ab}" (before appending) , then the method call {@code s.undo()}
 * would cause the {@code UndoableStringBuilder} to contain "{@code ab}" (like how the String was before).
