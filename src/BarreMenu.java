import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * La classe BarreMenu contient ce qui est relatif à la barre de menu de la fenetre
 * Une barre de menu contient cette barre, un menu et deux options dans ce menu.
 */
public class BarreMenu 
{
/**
	 * @see BarreMenu#getbarre()
	 */
	private static JMenuBar barre;
	private JMenu option_1 ;
	private JMenuItem choix_1;
	private JMenuItem choix_2;
	private Fenetre f;
/**
	 *Constructeur de la classe BarreMenu. Il crée dans la fenetre une barre avec 1 menu "jeu",
	 * une liste deroulante avec 2 options : "nouveau jeu" et "quitter"
	 *Les deux choix contiennent des actions lorsqu'on clique dessus 
	 *@see Fenetre#fermerJeu()
	 *@see Fenetre#nouvellePartie()
	 */
	public BarreMenu(Fenetre g)
		{
		this.f=g;
		barre = new JMenuBar();
		option_1 = new JMenu("Jeu");
		choix_1 = new JMenuItem("Nouvelle partie");
		choix_2 = new JMenuItem("Quitter");
		barre.add(option_1);
		option_1.add(choix_1);
		option_1.add(choix_2);		
		choix_1.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  f.nouvellePartie();
		          }});
		choix_2.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  f.fermerJeu();
		          }});
		}
/**
	 * Accesseur, retourne la barre de menu
	 * @return la barre
	 */
	public static JMenuBar getbarre()
		{
		return barre;		
		}	
}
