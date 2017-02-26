package siwarClient;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.siwar.persistence.Utilisateur;
import tn.esprit.siwar.services.MessageServiceRemote;
import tn.esprit.siwar.services.UtilisateurServiceRemote;

public class Main {

	public static void main(String[] args) throws NamingException  {
		InitialContext ctx = new InitialContext(); 
		UtilisateurServiceRemote utlisateurservice =(UtilisateurServiceRemote)
		ctx.lookup("/siwar-ear/siwar-ejb/UtilisateurService!tn.esprit.siwar.services.UtilisateurServiceRemote");
		MessageServiceRemote mesageservice =(MessageServiceRemote)
				ctx.lookup("/siwar-ear/siwar-ejb/MessageService!tn.esprit.siwar.services.MessageServiceRemote");
		utlisateurservice.ajouterUtilisateur(new Utilisateur());
		
	
	}

}
