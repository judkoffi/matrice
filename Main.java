public class Main {

	public static void main(String[] args) {

		/* test inverse */
		Matrice Z = new Matrice(new long[][] { { 2, -1, 0 }, { -1, 2, -1 }, {0, -1, 2} });
		System.out.println("Z= "+Z);
		Z.inverse();
		/* code prof */
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
