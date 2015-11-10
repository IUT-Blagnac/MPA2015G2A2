package main;

import controle.*;

public class Main {

	public static void main(String[] args) {
		casse.createJson(args[0]);
		new OPTIweb(args[0]);
	}

}
