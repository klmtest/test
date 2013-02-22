/**
 * solitaire est la classe contenant le main de mon programme, et tout le fonctionnement "mathematique" ainsi
 *  qu'une vue console
 * @author Clement Roos
 */

public class Solitaire 
{
/**
	 * Moche mais efficace : la grille de booléens de base : True pour les case remplies, False pour les cases vides.
	 * Ce tableau peut être accedé, modifié et affiché au cours de la partie.
	 * A faire en une fonction la prochaine fois ;-)
	 * @see Solitaire#getGrille()
	 * @see Solitaire#setGrille(boolean[][], int, int, boolean)
	 * @see Solitaire#affichage_grille()
	 */
	private boolean[][]grille 	= {{true,true,true,true,true,true,true},{true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true},{true,true,true,false,true,true,true},
			{true,true,true,true,true,true,true},{true,true,true,true,true,true,true},
			{true,true,true,true,true,true,true},};		
	 
/**
	 * Main du programme : crée le jeu en une fenetre et un mode graphique
	 * @param args
	 */
	public static void main(String[] args)
		{
/**
		 * L'interface graphique pouvant interragir en fenetre de la grille.
		 */
		Solitaire g = new Solitaire();
		Fenetre fenetre = new Fenetre(g);
/**
		 * Affichage de la grille en mode console.
		 */
		g.affichage_grille();
		}
/**
	 * 
	 * Getter classique
	 * @return la grille de booleens qui sert de base : False = case vide, True = case Pleine
	 */	
	 public boolean[][] getGrille()
		{
		return grille;
		}
	
/**
	 * Setter classique afin de modifier notre grille de booleens : prend en parametre des coordonnées et 
	 *     attribue un booleen a ces coordonnées dans le tableau.
	 * @param grille : Notre grille: un tableau de booleens de référence
	 * @param ligne : La ligne a laquelle il faut modifier la valeur
	 * @param colonne : La colonne a laquelle il faut modifier la valeur.
	 * @param bool : La valeur qu'il faut attribuer à la case dans la grille : au tableau de booleens.
	 */	
	 public void setGrille(boolean[][]grille,int ligne,int colonne,boolean bool)
		{
		grille[ligne][colonne]=bool;
		}
	
/**
	 * Affichage de la grille en mode console : le moteur d'abord, le chassis ensuite Mr Roos !
	 */	
	 public void affichage_grille()
		{
		/**
		 * Si la case est True dans la tableau de Booleens, il y aura un X dans la console, sinon un 0.
		 */
		System.out.println("");
		System.out.print("---------------");
			for (int i = 0; i < grille.length; i++ )
				{
				System.out.println();
				for (int j = 0 ; j < grille.length ; j++)
					if(grille[i][j]== false)
						System.out.print(" X ");
					else
						System.out.print(" 0 ");
				}
		}
	
/**
	 * Fonction qui teste si le déplacement est autorisé : il renvoie True si le déplacement 
	 *     est autorisé et False si ce n'est pas possible
	 *
	 * @param clic_1_ligne : La ligne de la première case sélectionnée par un clic souris
	 * @param clic_1_colonne : La colonne  de la première case sélectionnée par un clic souris
	 * @param clic_2_ligne : La ligne de la seconde case sélectionnée par un clic souris
	 * @param clic_2_colonne : La colonne de la seconde case sélectionnée par un clic souris
	 * @return : True si le déplacement est possible et False sinon.
	 */	
	 public boolean test_deplacement_autorise(int clic_1_ligne,int clic_1_colonne,int clic_2_ligne,int clic_2_colonne)
		{
/**
		 * Mise en place des coordonnées de la case sautée, ou du moins à au milieu des deux cases cliquées
		 */
		int ligne_Bouton_Milieu=(clic_1_ligne+clic_2_ligne)/2;
		int colonne_Bouton_Milieu=(clic_1_colonne+clic_2_colonne)/2;
/**
		 * Pour retourner True, 4 conditions doivent être respectées :
		 * -Si les cases de départ et d'arrivée sont sur la même ligne,  leur colonne doit être
		 *      espacée de 2. Idem pour la même colonne, les lignes doivent etre espacées de 2.
		 * -La case de départ doit etre pleine
		 * -La case de d'arrivée doit etre vide
		 * -La case du milieu doit etre pleine
		 */
		if (clic_1_ligne==clic_2_ligne)
			{
			if ((clic_1_colonne-clic_2_colonne==2 || clic_1_colonne-clic_2_colonne==-2) && grille[clic_1_ligne][clic_1_colonne]==true && grille[clic_2_ligne][clic_2_colonne]==false && grille[ligne_Bouton_Milieu][colonne_Bouton_Milieu]==true)
				return true;
			}	
		else if (clic_1_colonne==clic_2_colonne)
			{
				if ((clic_1_ligne-clic_2_ligne==2 || clic_1_ligne-clic_2_ligne==-2) && grille[clic_1_ligne][clic_1_colonne]==true && grille[clic_2_ligne][clic_2_colonne]==false && grille[ligne_Bouton_Milieu][colonne_Bouton_Milieu]==true)
					return true;
			}
/** 
		 *Si les cases ne remplissent pas les conditions, retourner False
		 */
				return false;		
		}
/**
	 * Fonction qui modifie le tableau de booleens en cas de déplacement permis :
	 * 1) True pour la case du second clic
	 * 2) False pour la case de départ
	 * 3) False pour celle du milieu
	 * 
	 * @see Solitaire#setGrille(boolean[][], int, int, boolean) : pour mettre à jour le tableau de booléens
	 * @see Solitaire#case_Milieu(int, int, int, int) : pour déterminer la case entre les deux selectionnées
	 * @param clic_1_ligne : idem que précédement
	 * @param clic_1_colonne : idem que précédement
	 * @param clic_2_ligne : idem que précédement
	 * @param clic_2_colonne : idem que précédement
	 */	
	 public void swap_et_vide(int clic_1_ligne,int clic_1_colonne,int clic_2_ligne,int clic_2_colonne)
		{
		boolean[][]grille = getGrille();
/**
		 * Mise à jour des cases de départ et d'arrivée : True pour celle d'arrivée, et False pour celle de départ
		 */
		setGrille(grille, clic_1_ligne, clic_1_colonne, false);
		setGrille(grille, clic_2_ligne, clic_2_colonne, true);
/**
		 * Determination en false de la case du milieu
		 */
		setGrille(grille,case_Milieu(clic_1_ligne, clic_1_colonne, clic_2_ligne, clic_2_colonne).ligne, case_Milieu(clic_1_ligne, clic_1_colonne, clic_2_ligne, clic_2_colonne).colonne, false);              
		}
/**
	 * Determination des coordonnées de la case du milieu 
	 * @param clic_1_ligne : idem
	 * @param clic_1_colonne : idem
	 * @param clic_2_ligne : idem
	 * @param clic_2_colonne : idem
	 * @return une Coordonnee de la case du milieu entre les deux clics
	 */	
	 public Coordonnees case_Milieu(int clic_1_ligne,int clic_1_colonne,int clic_2_ligne,int clic_2_colonne)
		{
		Coordonnees coord_milieu=new Coordonnees(clic_1_ligne,clic_1_colonne,clic_2_ligne,clic_2_colonne);	
		return coord_milieu;
		}
}
