public class Main {

	public static void main(String[] args) {

		Matrice A = new Matrice(new long[][] { { 2, -1, 0 }, { -1, 2, -1 }, { 0, -1, 2 } });
		System.out.println("A= "+A);
		A.inverse();
		/*
		Rational t= new Rational(2);
		/* test transvection =>OK
		A.transvection(0, 1, t);
		System.out.println("A= "+A);
		*
		A.multiplyRow(0, t);
		System.out.println("A= "+A);
		Matrice B = A.times(A.transpose());
		System.out.println(C);
		Matrice D = A.transpose().times(A);
		try {
			Matrice E = D.inverse();
			System.out.println(E);
		} catch (ArithmeticException e) {
			System.out.println("D n'a pas d'inverse");
		}

		/*
		 * Matrice A = new Matrice(new long[][] { { 1, 2, 3},{ 4 ,5, 6}, { 7, 8 , 9}});
		 * Matrice B = new Matrice(new long[][] { { 5, 6 }, { 7, 8 } });
		 */
	}

}
