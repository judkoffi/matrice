import java.util.Arrays;
import java.util.Optional;

public class Matrice {

	private final int n; /* nombre de lignes */
	private final int m; /* nombre de colonnes */
	private final Rational[][] coeff; /* liste des coefficients */

	/**
	 * Création d'une matrice
	 * 
	 * @param coeff coefficients de la matrice
	 */
	public Matrice(Rational[][] coeff) {
		n = coeff.length;
		m = coeff[0].length;
		this.coeff = coeff;
	}

	/**
	 * Création d'une matrice
	 * 
	 * @param coeff coefficients de la matrice, donnés comme long
	 */
	public Matrice(long[][] coeff) {
		n = coeff.length;
		m = coeff[0].length;
		this.coeff = new Rational[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				this.coeff[i][j] = new Rational(coeff[i][j]);
			}
		}
	}

	/**
	 * Calcul de la somme matricielle this + M (si les dimensions de this et M
	 * l'autorisent)
	 * 
	 * @param M matrice à ajouter : tableau n x m
	 * @return somme this + M : tableau n x m
	 */
	public Matrice plus(Matrice M) {
		if (this.n != M.n || this.m != M.m) {
			throw new IllegalArgumentException("Dimensions incorrectes");
		}

		Rational[][] sum = new Rational[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sum[i][j] = this.coeff[i][j].plus(M.coeff[i][j]);
			}
		}
		return new Matrice(sum);
	}

	/**
	 * Calcul du produit matriciel this M (si les dimensions de this et M
	 * l'autorisent)
	 * 
	 * @param M matrice à multiplier : tableau m x p
	 * @return produit this M : tableau n x p
	 */
	public Matrice times(Matrice M) {
		if (m != M.n) {
			throw new IllegalArgumentException("Dimensions incorrectes");
		}
		int p = M.m;

		Rational[][] prod = new Rational[n][p];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				prod[i][j] = Rational.ZERO;
				for (int k = 0; k < p; k++) {
					prod[i][j] = prod[i][j].plus(this.coeff[i][k].times(M.coeff[k][j]));
				}
			}
		}
		return new Matrice(prod);
	}

	/**
	 * Calcul de la transposée de this
	 * 
	 * @return transposée de this : tableau m x n
	 */
	public Matrice transpose() {
		Rational[][] trans = new Rational[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				trans[j][i] = this.coeff[i][j];
			}
		}
		return new Matrice(trans);
	}

	/**
	 * Échange les lignes i et j de la matrice
	 * 
	 * @param i première ligne à échanger
	 * @param j deuxième ligne à échanger
	 */
	public void swapRows(int i, int j) {
		if (i>n || j>n || i<0 || j<0) 
			throw new IllegalArgumentException("La matrice ne possède pas ces lignes");

		for (int l = 0; l < m; l++) {
			Rational tmp = this.coeff[j][l];
			this.coeff[j][l] = this.coeff[i][l];
			this.coeff[i][l] = tmp;
		}
	}

	/**
	 * Ajoute a fois la ligne i de this à sa ligne j
	 * 
	 * @param i ligne à ajouter (multiplée par a)
	 * @param j ligne à laquelle on ajoute a fois la ligne j
	 * @param a scalaire par lequel on multiplie la ligne i quand on l'ajoute
	 */
	private void transvection(int i, int j, Rational a) {
		if (i>n || j>n || i<0 || j<0) 
			throw new IllegalArgumentException("La matrice ne possède pas ces lignes");
		for (int c = 0; c <m; c++) {	// parcours les colonnes
			this.coeff[j][c]= this.coeff[j][c].plus(this.coeff[i][c].times(a));
		}
	}

	/**
	 * Mutiplie par a la ligne i de this
	 * 
	 * @param i ligne à multiplier par a
	 * @param a scalaire par lequel on multiplie la ligne i
	 */
	private void multiplyRow(int i, Rational a) {
		for (int l = 0; l < m; l++) {
			this.coeff[i][l] = this.coeff[i][l].times(a);
		}
	}

	/**
	 * Calcul de la matrice identité de mêmes dimensions que this (si les dimensions
	 * de this l'autorisent)
	 * 
	 * @return matrice identité : tableau n x n
	 */
	public Matrice identity() {
		if (m != n) {
			throw new IllegalArgumentException("Dimensions incorrectes");
		}
		Rational[][] id = new Rational[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i == j){
					id[i][j] = Rational.ONE;
				}else{
					id[i][j] = Rational.ZERO;
				}
			}
		}
		return new Matrice(id);
	}

	/**
	 * Calcul d'une copie de this
	 * 
	 * @return copie de this : tableau n x m
	 */
	public Matrice clone() {
		Rational[][] clone = new Rational[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				clone[i][j] = coeff[i][j];
			}
		}
		return new Matrice(clone);
	}

	/**
	 * Calcul de l'inverse de this
	 * 
	 * @return inverse de this : tableau n x n
	 */
	public Matrice inverse() {
		if (m != n) {
			throw new IllegalArgumentException("Dimensions incorrectes");
		}
		Matrice clone = clone();
		Matrice id = identity();
		/** Remplir ici le code manquant */
		/** On suggère très fortement d'utiliser l'algorithme du pivot de Gauss */
		return id;
	}

	@Override
	public String toString() {
		return Arrays.deepToString(coeff);
	}

}
