public class Main {

	public static void main(String[] args) {

		/* Exercice 11 */

		Matrice I = new Matrice(new Rational[][] { { new Rational(2),  new Rational(3,2),  new Rational(1)}, 
													{ new Rational(1),  new Rational(3,2),  new Rational(2)},
													{ new Rational(9,4),  new Rational(2),  new Rational(9,4)}});

		Matrice J = new Matrice(new Rational[][] { { new Rational(60)}, 
		{ new Rational(90)},
		{ new Rational(110)}});


		System.out.println("I = "+I);
	    System.out.println("J= "+J);
		I= I.inverse();	// On calcul l'inverse de I
		Matrice K=  I.times(J);	// On multiplie I par J
		System.out.println("K= "+K);	// Affiche le résultat de l'exercice

		/* code initial */
		Matrice A = new Matrice(new long[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 13 } });
		Matrice B = A.times(A.transpose());
		Matrice C = B.inverse();
		System.out.println(C);
		Matrice D = A.transpose().times(A);
		try {
			Matrice E = D.inverse();
			System.out.println(E);
		} catch (ArithmeticException e) {
			System.out.println("D n'a pas d'inverse");
		}
	}

}
