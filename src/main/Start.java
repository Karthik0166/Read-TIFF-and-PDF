package main;

import java.io.IOException;

public class Start {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Read read=new Read();
		read.readPdf();
		read.readTif();
	}

}
