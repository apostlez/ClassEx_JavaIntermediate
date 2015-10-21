   // SimpleSAX.java
   package edu.jaen.java.xml.basic;
   import javax.xml.parsers.*;
   import org.xml.sax.helpers.*;
   import org.xml.sax.*;
   
   public class SimpleSAX {

      public static void main(String args[]){
         SAXParser parser = null;
         try {
               // Create XML (non-validating) parser
	       SAXParserFactory factory = SAXParserFactory.newInstance();
	       parser = factory.newSAXParser();

              // Create event handler
              DefaultHandler handler = new SimpleSAXHandler();
  
              // Call parsing method
              parser.parse(args[0], handler);
  
          } catch(SAXParseException spe) {
              System.out.println(spe.getLineNumber() + " line");
              System.out.println(spe.getMessage());
              System.exit(0);
          } catch(SAXException se) {
              System.out.println(se.getMessage());
              System.exit(0);
          } catch(Exception e) {
              System.out.println(e.getMessage());
              e.printStackTrace();
              System.exit(0);
          }
      }
  }
  
  // Event handler
  class SimpleSAXHandler extends DefaultHandler {
      Locator loc;
      /* -- DocumentHandler method -- */
      public void setDocumentLocator(Locator loc) {
          this.loc = loc;
      }
      public void startDocument() {
          System.out.println("XML Document START.");
      }
      public void endDocument() {
          System.out.println("XML Document END.");
      }
      public void startElement(String namespaceURI, String name, 
          String qName, Attributes atts) {
          System.out.println("Start element is : " + qName);
      }
      public void endElement(String namespaceURI, String name, String qName)  {
          System.out.println("End element is : " + qName + "\n");
      }
      public void characters(char[] ch, int start, int length) {
          System.out.print("   Char is : " + String.valueOf(ch,start,length));
          System.out.println("   Char start: " + start + " length : " + length);
      }
  
      /* -- ErrorHandler Method -- */
      public void warning(SAXParseException e) {
          System.out.println("-- warning()");
      }
      public void error(SAXParseException e) {
          System.out.println("-- error()");
      }
      public void fatalError(SAXParseException e) {
          System.out.println("-- fatalError()");
      }
  }

