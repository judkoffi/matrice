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
	public void multiplyRow(int i, Rational a) {
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

	/*********************** Exercice 9 **********************/
	/**
	 * Vérifie si la ligne ligne de la matrice contient que des zéros
	 * @param ligne	ligne à vérifier
	 * @return	false s'il n'y a pas que des zéros et true sinon
	 */
	private boolean onlyZero(int ligne){
		for(int i=0; i<this.m; i++){
			if(this.coeff[ligne][i]!=Rational.ZERO)
				return false;	/* il y a un élément qui n'est pas 0 */
		}
		return true;
	}

	/**
	 * Divise une ligne d'une matrice par un Rational
	 * @param ligne ligne à diviser
	 * @param div	Rational 
	 * @return	nouvelle matrice
	 */
	private Matrice divideRow(int ligne, Rational div){
		Matrice m= this.clone();
		for(int j=0; j<this.m; j++){
			m.coeff[ligne][j]= m.coeff[ligne][j].divide(div);
		}
		return m;
	}

	/**
	 * Retourne la ligne qui contient l'élément maximum de la colonne c dans la matrice m
	 * @param ligne ligne à partir de laquelle on parcour la matrice
	 * @param colonne colonne à parcourir
	 * @return
	 */
	private int maxRow(int ligne, int colonne){
		int ligneMax=ligne;
		Rational max=Rational.ZERO;
		for(int i=ligne; i<this.n; i++){	// lignes
			if(max.less(this.coeff[i][colonne])){
				max= this.coeff[i][colonne];
				ligneMax= i;
			}
		}
		return ligneMax;
	}

	/**
	 * Soustrait la ligne toChange de la matrice this par 
	 * la ligne ligne qui est précédement multiplié par le Rational minus
	 * @param toChange indice de la ligne a cloner
	 * @param ligne	indice de la ligne dont on va soustraire les valeurs de la ligne a cloner
	 * @param ra Rational valeur que l'on va multiplié à la ligne d'indice ligne
	 */
	private Matrice minusRow(int toChange, int ligne, Rational ra){
		System.out.println("Multilpie la ligne "+ligne+" par "+ra);
		Matrice m= this.clone();
		Matrice m2= this.clone();
		m2.multiplyRow(ligne, ra);
		for(int j=0; j<this.m; j++){
			m.coeff[toChange][j]= m.coeff[toChange][j].minus(m2.coeff[ligne][j]);
		}
		return m;
	}


	/**
	 * Calcul de l'inverse de this
	 * 
	 * @return inverse de this : tableau n x n
	 */
	public Matrice inverse() {
		if(m != n) 
			throw new IllegalArgumentException("Dimensions incorrectes");
			
		Matrice clone = this.clone();	// copie de this
		Matrice id = this.identity();
			
		System.out.println("Matrice initial : "+clone);
		System.out.println("Matrice identité : "+id);
		
		int r=-1;	// indice de la ligne de pivot (commence à -1 car -1+1= 0= numéro de la première ligne de la matrice)
		Rational max;	// maximum d'une colonne
		int k;	// ligne qui contient le maximum
		for(int j=0; j<this.m; j++){	// pour toutes les colonnes
			k= clone.maxRow(r+1, j);	// récupère l'indice de la ligne qui contient le maximum de la colonne j
			max= clone.coeff[k][j];	// récupère le maximum de la colonne
			System.out.println("Le maximum de la colonne "+ j+ " est : "+max);
			r++;	// augmente d'un cran la future ligne pivot
			System.out.println("La future ligne de pivot est : "+r);
			clone= clone.divideRow(k,max);	// divise la ligne qui contient le maximum de la colonne j par le maximum
			id= id.divideRow(k, max); // effectue la même opération pour la matrice identité
			System.out.println("Après Division de la ligne "+ k+ " par "+max);
			System.out.println(id);
			
			if(k!=r){	// si la ligne pivot n'est pas la même que celle qui contient le maximum de la colonne j
			clone.swapRows(k, r);	// on inverse les lignes
			id.swapRows(k, r); // effectue la même opération pour la matrice identité
				System.out.println("Après échanges des lignes "+k+" et "+r);
				System.out.println(id);
			}
			
			for(int i=0; i<clone.n; i++){	// pour chaque lignes
				if(onlyZero(i))	// la ligne contient que des 0 
					throw new ArithmeticException("Division par zéro"); // matrice carré n x n pas d'inverse
				if(i!=r){	// si la ligne n'est pas la ligne pivot
				Rational coeff= clone.coeff[i][j];
					clone= clone.minusRow(i, r, coeff);	// on soustrait à la ligne parcouru la ligne pivot elle-même multiplié par coeff
					id= id.minusRow(i, r, coeff); // effectue la même opération pour la matrice identité
					System.out.println("Après soustraction de la ligne "+ i+" la ligne "+j);
					System.out.println(id);
				}
			}
		}

		System.out.println("La matrice identité a été rerouvé !");
		System.out.println(clone);
		System.out.println("La matrice inverse a été rerouvé !");
		System.out.println(id);
		
		return id;
	}

	@Override
	public String toString() {
		return Arrays.deepToString(coeff);
	}

}

