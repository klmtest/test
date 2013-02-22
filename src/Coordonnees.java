import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * La classe coordonée sert de relai entre l'interface 
 * graphique faite de boutons et le tableau de booléens de référence
 * 
 * Chaque Coordonnee contient une numero de ligne et de colonne, ainsi qu'un bouton
 * @author Clement Roos
 */
public class Coordonnees  implements ActionListener
{
		JButton bouton;	
		int ligne;
		int colonne;
		Fenetre f = null;
/**
		 * Constructeur de la classe : une coordonnée classique est constituée par:
		 * 1) Un numero de ligne
		 * 2) Un numero de colonne
		 * 3) Un bouton
		 */
		 public Coordonnees (Fenetre f, int abs, int ord,JButton bout)
			{
			 this.f = f;
			 this.ligne=abs;
			 this.colonne=ord;
			 this.bouton=bout;	
			}
/**
		 * Constructeur pour le pion du milieu qui est sauté pendant le jeu 
		 * Il ne contient pas de bouton car il est susceptible d'être sur des
		 *    coordonnées invalides (ex: 1,5 / 2 si l'utilisateur a cliqué 1/1 puis 2/3 ) même si 
		 *    le cast en int le remttra sur un entier au final
		 * 
		 */
		 public Coordonnees(int ligne_1,int colonne_1,int ligne_2,int colonne_2)
			{
			ligne = (ligne_1+ligne_2)/2;
			colonne=(colonne_1+colonne_2)/2;
			}	
/**
		 * Accesseur de bouton
		 * @return un bouton associé à une coordonnée
		 */
		 public JButton getbouton()
			{
			return this.bouton;
			}
/**
		 * actionPerformed contient les actions à réaliser lorsque qu'on clique sur un bouton. 
		 * Il est lié au code coord.bouton.addActionListener(coord) @see Fenetre#grilleUpdate() de la classe Fenetre. 
		 * =>Si les coordonnées sont celles par défaut, les coordonnées actives devienent
		 * 	 la première case dont la bordure devient rouge.
		 * =>Si les coordonnées ne sont pas par défaut, le programme teste si le déplacement est autorisé. 
		 * 		=>Si le déplacement est autorisé:
		 * 				1)le tableau de booleen est mis à jour
		 * 				2)Les clics de souris sont remis à defaut
		 * 				3)L'interface graphique est mise à jour
		 * 				4)L'affiche en mode console est mis à jour
		 * 		=>Si le déplacement n'est pas autorisé:
		 * 				1)La premiere case perd sa bordure
		 * 				2)Les clics de souris sont remis à défaut
		 * 
		 * @see Solitaire#test_deplacement_autorise(int, int, int, int)
		 * @see Solitaire#swap_et_vide(int, int, int, int)
		 * @see Fenetre#resetCoord()
		 * @see Fenetre#grilleUpdate()
		 * @see Solitaire#affichage_grille()
		 */
		 public void actionPerformed(ActionEvent e)
			{
			Coordonnees coord_1 = f.getCoord_1();
			Coordonnees coord_2 = f.getCoord_2();
			if (coord_1.ligne == -1)
				{
				coord_1.ligne=this.ligne;
				coord_1.colonne=this.colonne;
				coord_1.bouton=this.bouton;
				coord_1.bouton.setBorder(BorderFactory.createLineBorder(Color.red,2));
				}
/**
			 * Si une premiere case a deja été cliquée...
			 */
			else
				{
				coord_2.ligne=this.ligne;
				coord_2.colonne=this.colonne;
				coord_2.bouton=this.bouton;
				Solitaire s = f.getS();
/**				
				 * Si le déplacement est autorisé...
				 */
				if (s.test_deplacement_autorise(coord_1.ligne,coord_1.colonne,coord_2.ligne,coord_2.colonne))
					{
					s.swap_et_vide(coord_1.ligne,coord_1.colonne,coord_2.ligne,coord_2.colonne);
					f.resetCoord();
					f.grilleUpdate();
					s.affichage_grille();
					}
/**
				 * Si le déplacement n'est pas autorisé...
				 */
				coord_1.bouton.setBorder(null);
				f.resetCoord();
				}
			}		
}
