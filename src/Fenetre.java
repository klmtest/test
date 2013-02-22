import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color; 
import java.awt.GridLayout;
/** 
 * La classe Fenetre comprend toute l'interface graphique fenetrée du solitaire.
 * Deux clics de souris forment une serie analysable par le programme pour savoir si le mouvement est autorisé.
 * @author Clement Roos
 */
public class Fenetre   
	{
	
	private Solitaire s;
	
/**
	 * Les 	coordonnées du premier clic de souris, les coordonnées sont instanciées à -1 (valeur reconnaissable), accessible et modifiable dans tous le programme.
	 * @see Fenetre#getCoord_1()
	 * @see Fenetre#resetCoord()
	 */
	private Coordonnees coord_1=new Coordonnees (this, -1,-1,new JButton(""));
/**
	 * Les 	coordonnées du second clic de souris, les coordonnées sont instanciées à -1(valeur reconnaissable), accessible et modifiable dans tous le programme.
	 * @see Fenetre#getCoord_2()
	 * @see Fenetre#resetCoord()
	 */
	private Coordonnees coord_2=new Coordonnees (this, -1, -1, new JButton(""));
/**
	 * La fenetre qui compose la partie graphique, elle peut etre fermée
	 * @see Fenetre#nouvellePartie()
	 */
	private JFrame win;
/**
	 * Accesseur des coordonnées du premier clic.
	 * @return des Coordonnées : la ligne, la colonne et le bouton associé
	 */
	 public Coordonnees getCoord_1()
	 {
		return coord_1; 
	 }
/**
	  * Accesseur des coordonnées du second clic.
	  * @return des Coordonnées : la ligne, la colonne et le bouton associé
	  */
	 public Coordonnees getCoord_2()
	 {
		return coord_2;  
	 }	
	 
/**
	  * Constructeur de la classe : crée une fenetre et la parametre
	  */
	 public Fenetre(Solitaire s)
		{
		this.s = s;
		win = new JFrame();
		win.setVisible(true);
	    win.setTitle("Solitaire");
	    win.setSize(420, 420);
	    win.setLocationRelativeTo(null);
	    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    win.setResizable(false);
	    JPanel pan = new JPanel();
	    pan.setBackground(Color.WHITE);        
	    win.setContentPane(pan);     
	    boolean[][]grille= s.getGrille();
/**
	     * On place dans fenetre un cadrillage de 7 lignes sur 7 fenetres (longueur du tableau)
	     */
	    
	    pan.setLayout(new GridLayout(grille.length,grille.length));
/**
 		* Création et intégration à la fenetre d'une barre de menu.
 		*/	    
	    BarreMenu barre = new BarreMenu(this);
	    win.setJMenuBar(BarreMenu.getbarre());
	    
		 
/**
		*La grille est instanciée : une Coordonnees contient une lignes et une colonnes de 1 à 7 ainsi qu'un bouton
		*Sur chaque bouton est placé une ecoute de clic de souris qui fera une action dans la classe @see Coordonnees#actionPerformed
		*/	    
	    for (int i = 0; i < grille.length; i++ )
			{
		    	for (int j = 0 ; j < grille.length ; j++)
			    	{
		    		ImageIcon image;
		    		JButton bouton;
		    		if (grille[i][j]==true)
		    			{
		    			/**
		    			 * Case pleine : création d'un bouton contenant une image
		    			 */
		    			bouton = new JButton();
		    			image = new ImageIcon("images/pion.JPG");
		    			bouton.setIcon(image);
		    			}
		    		else
		    			{
/**
		    			 * Case vide  : Création d'un bouton vide
		    			 */
			    		bouton = new JButton("");
		    			}
		    			pan.add(bouton);
		    			win.setContentPane(pan);
				        Coordonnees coord = new Coordonnees (this, i,j,bouton);
				        coord.getbouton().addActionListener((coord));
			    	}	    
			}
		}
/**
	  * Remet la serie de deux clic à defaut.
	  */
	 public void resetCoord()
			{
			coord_1= new Coordonnees(this, -1,-1,new JButton(""));
			coord_2= new Coordonnees(this, -1,-1,new JButton(""));		
			}
		
/**
	  * Met a jour le cadrillage avec de nouveaux boutons correspondant au tableau de booléens mis à jour
	  */
	 public void grilleUpdate()
		{
			boolean[][]grille=s.getGrille();
			JPanel pann = new JPanel();
		    pann.setBackground(Color.WHITE);        
		    pann.setLayout(new GridLayout(grille.length,grille.length));
			for (int i = 0; i < grille.length; i++ )
				{
				/**
				 * Ajoute un bouton avec pour intitulé "O" dans les cases pleines et laisse vide l'intitulé des cases vides
				 */
			    	for (int j = 0 ; j < grille.length ; j++)
				    	{
			    		ImageIcon image;
			    		JButton bouton;
			    		if (grille[i][j]==true)
		    				{
			    			bouton = new JButton();
			    			image = new ImageIcon("images/pion.JPG");
			    			bouton.setIcon(image);
			    			}
			    		else
			    			{
				    		bouton = new JButton("");
			    			}
				    	pann.add(bouton);
				        win.setContentPane(pann);
				        win.setVisible(true);
				        Coordonnees coord = new Coordonnees(this, i,j,bouton);
				        coord.bouton.addActionListener(coord);
				    	}	    
			    }
		}
/**
	    * Ferme la fenetre du jeu
	    */
		public void fermerJeu()
			{
			System.exit(1);
			}
/**
		 * 1)Ferme la fenetre actuelle
		 * 2)Remet à defaut le tableau de booléens 
		 * 3)Crée un nouvel objet de fenetre 
		 */
		public void nouvellePartie()
			{
			win.dispose();
/**
			 * Toutes les cases sont rendues pleines
			 */
			for (int i=0; i<s.getGrille().length;i++)
				for (int j =0;j<s.getGrille().length;j++)
					s.setGrille(s.getGrille(), i, j, true);
/**
			 * ...sauf la case du milieu
			 */
			s.setGrille(s.getGrille(),3, 3, false);
			s.affichage_grille();
			Fenetre fenetreX = new Fenetre(s);
			}
 public Solitaire getS() {
	return s;
}
	}
