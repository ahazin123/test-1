package ma.fst.tkhzn.sdsi.business;

import java.util.ArrayList;
import java.util.List;

import ma.fst.tkhzn.sdsi.entities.Ordinateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ma.fst.tkhzn.sdsi.entities.Fournisseur;
import ma.fst.tkhzn.sdsi.entities.Imprimante;
import ma.fst.tkhzn.sdsi.entities.Ressource;
import ma.fst.tkhzn.sdsi.repositories.FournisseurRepository;
import ma.fst.tkhzn.sdsi.repositories.ImprimanteRepository;
import ma.fst.tkhzn.sdsi.repositories.OrdinateurRepository;
import ma.fst.tkhzn.sdsi.repositories.RessourceRepository;
import ma.fst.tkhzn.sdsi.repositories.UtilisateurRepository;
import ma.fst.tkhzn.sdsi.requests.RessourceRequest;


@RestController
@RequestMapping(value = {"api/ressourceservice"})
@CrossOrigin
public class RessourceService {
	@Autowired
	RessourceRepository ressourcerepository;
	
	@Autowired
	FournisseurRepository fournisseurrepository;
	
	@Autowired
	UtilisateurRepository utilisateurrepository;
	
	@Autowired
	ImprimanteRepository  imprimanteRepository;
	
	@Autowired
	OrdinateurRepository ordinateurRepository;
	
	
	//ajouter ressource
  	@RequestMapping(path = "/getRessources", method = RequestMethod.GET)
  	public List<Fournisseur> addRessource() {		 
  		List<Fournisseur> fourniseur=fournisseurrepository.findAll();
  	    return fourniseur;
  	 }
	
	
    @RequestMapping(path = "/addRess", method = RequestMethod.POST)
	public void addRess(@RequestBody RessourceRequest resReq) {
		System.err.println(resReq);
		Long idmax = 0L;
		List<String> ids = ressourcerepository.findLastId();
		for(String id : ids) {
			long v = Long.parseLong(id.substring(1, id.length()));
			if(v > idmax) idmax = v;
		}
        if(resReq.isNouv()==true) {
			Fournisseur fournisseur = fournisseurrepository.findByLogin(resReq.getFournisseur().getLogin());
			fournisseur.setGerant(resReq.getFournisseur().getGerant());
			fournisseur.setLieu(resReq.getFournisseur().getLieu());
			fournisseur.setNomSocite(resReq.getFournisseur().getNomSocite());
            fournisseurrepository.save(fournisseur);
        }
		idmax = idmax + 1;
		if(resReq.getType().equals("imprimante")) {
			for(int i = 0; i < resReq.getQte(); i++) {
				Imprimante imp = new Imprimante(resReq.getImp());
				imp.setCode("I" + idmax);
				imp.setFournisseur(resReq.getFournisseur());
				imp.setLivrer(true);
				imprimanteRepository.save(imp);
				idmax = idmax + 1;
			}
		}
		else {
			for(int i = 0; i < resReq.getQte(); i++) {
				Ordinateur ord = new Ordinateur(resReq.getOrdi());
				ord.setCode("O" + idmax);
				ord.setFournisseur(resReq.getFournisseur());
				ord.setLivrer(true);
				ordinateurRepository.save(ord);
				idmax = idmax + 1;
			}
		}
	}
	
    @RequestMapping(path = "/add", method = RequestMethod.POST)
	public void add1(@RequestBody Ressource c) {
    	System.err.println("post");
    }
    @RequestMapping(path = "/add", method = RequestMethod.GET)
	public void add2() {
    	System.err.println("get");
    }
  //Modifier ressource (clique)
  	@RequestMapping(path = "/updateRessource", method = RequestMethod.GET)
  	public Ressource updateRessource(Long id) {		 
  		
  		Ordinateur ordinateur=ordinateurRepository.findById(id).get();
  		Imprimante imprimante=imprimanteRepository.findById(id).get();
  		if(ordinateur!=null)
  			return ordinateur;
  		
  	    return imprimante;
  	 }

  	
  	//modifier ressource (formulaire)
  	@RequestMapping(path = "/updateRess", method = RequestMethod.POST)
  	public void updateRess(@RequestBody RessourceRequest resReq) {
  		if(resReq.getType().equals("Imprimante")) {
			imprimanteRepository.save(resReq.getImp());
		}
		else {
			ordinateurRepository.save(resReq.getOrdi());
		}
  	}
  	
  	
	//supprimer ressource
	@RequestMapping(path = "/deleteRess", method = RequestMethod.GET)
	public void deleteRess(String id) {
//		  System.err.println(id);
		  ressourcerepository.deleteByCode(id);
	}
	
	
	
	//Affichage
	@RequestMapping(path = "/ListLiv", method = RequestMethod.GET)
	public List<Ressource> ListLiv() {
		List<Ressource> liste = new ArrayList<>();
		for(Ordinateur o: ordinateurRepository.findAll())
			if(o.isLivrer()) liste.add(o);
		for(Imprimante i: imprimanteRepository.findAll())
			if(i.isLivrer()) liste.add(i);
		return liste;		  
//		return ressourcerepository.findAllLiv();
	}
	
	@RequestMapping(path = "/ListDisp", method = RequestMethod.GET)
	public List<Ressource> ListDisp() {
		List<Ressource> liste = new ArrayList<>();
		for(Ordinateur o: ordinateurRepository.findAll())
			if(!o.isLivrer()) liste.add(o);
		for(Imprimante i: imprimanteRepository.findAll())
			if(!i.isLivrer()) liste.add(i);
		return liste;		  
	}	
	
}
