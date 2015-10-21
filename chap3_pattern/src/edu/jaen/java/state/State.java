
package edu.jaen.java.state;
/**
 * State. Defines an interface for encapsulating the behavior associated with a 
 * particular state of the Context
 */
public interface State {
   
   public void open();
   
   public void close();
   
   public void save();
   
   public void edit(String text);
}
