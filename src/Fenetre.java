import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color; 
import java.awt.GridLayout;
/** 
 * La classe Fenetre comprend toute l'interface graphique fenetr�e du solitaire.
 * Deux clics de souris forment une serie analysable par le programme pour savoir si le mouvement est autoris�.
 * @author Clement Roos
 */
public class Fenetre   
	{
	
	private Solitaire s;
	
/**
	 * Les 	coordonn�es du premier clic de souris, les coordonn�es sont instanci�es � -1 (valeur reconnaissable), accessible et modifiable dans tous le programme.
	 * @see Fenetre#getCoord_1()
	 * @see Fenetre#resetCoord()
	 */
	private Coordonnees coord_1=new Coordonnees (this, -1,-1,new JButton(""));
/**
	 * Les 	coordonn�es du second clic de souris, les coordonn�es sont instanci�es � -1(valeur reconnaissable), accessible et modifiable dans tous le programme.
	 * @see Fenetre#getCoord_2()
	 * @see Fenetre#resetCoord()
	 */
	private Coordonnees coord_2=new Coordonnees (this, -1, -1, new JButton(""));
/**
	 * La fenetre qui compose la partie graphique, elle peut etre ferm�e
	 * @see Fenetre#nouvellePartie()
	 */
	private JFrame win;
/**
	 * Accesseur des coordonn�es du premier clic.
	 * @return des Coordonn�es : la ligne, la colonne et le bouton associ�
	 */
	 public Coordonnees getCoord_1()
	 {
		return coord_1; 
	 }
/**
	  * Accesseur des coordonn�es du second clic.
	  * @return des Coordonn�es : la ligne, la colonne et le bouton associ�
	  */
	 public Coordonnees getCoord_2()
	 {
		return coord_2;  
	 }	
	 
/**
	  * Constructeur de la classe : cr�e une fenetre et la parametre
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
 		* Cr�ation et int�gration � la fenetre d'une barre de menu.
 		*/	    
	    BarreMenu barre = new BarreMenu(this);
	    win.setJMenuBar(BarreMenu.getbarre());
	    
		 
/**
		*La grille est instanci�e : une Coordonnees contient une lignes et une colonnes de 1 � 7 ainsi qu'un bouton
		*Sur chaque bouton est plac� une ecoute de clic de souris qui fera une action dans la classe @see Coordonnees#actionPerformed
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
		    			 * Case pleine : cr�ation d'un bouton contenant une image
		    			 */
		    			bouton = new JButton();
		    			image = new ImageIcon("images/pion.JPG");
		    			bouton.setIcon(image);
		    			}
		    		else
		    			{
/**
		    			 * Case vide  : Cr�ation d'un bouton vide
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
	  * Remet la serie de deux clic � defaut.
	  */
	 public void resetCoord()
			{
			coord_1= new Coordonnees(this, -1,-1,new JButton(""));
			coord_2= new Coordonnees(this, -1,-1,new JButton(""));		
			}
		
/**
	  * Met a jour le cadrillage avec de nouveaux boutons correspondant au tableau de bool�ens mis � jour
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
				 * Ajoute un bouton avec pour intitul� "O" dans les cases pleines et laisse vide l'intitul� des cases vides
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
		 * 2)Remet � defaut le tableau de bool�ens 
		 * 3)Cr�e un nouvel objet de fenetre 
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
