package edu.jaen.java.state;

class DocumentTest {
	public static void main(String args[]) {
		Document docu = new Document();
		docu.open();
		docu.edit("hi");
		docu.save();
		docu.save();

		System.out.println();
		docu.open();
		docu.edit("my name is kyong ae");

		System.out.println();
		docu.open();
		docu.close();

		System.out.println();
		docu.open();
		docu.edit("haha");
		docu.close();
	}

}
