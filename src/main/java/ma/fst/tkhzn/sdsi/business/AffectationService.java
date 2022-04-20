package ma.fst.tkhzn.sdsi.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import ma.fst.tkhzn.sdsi.entities.*;
import ma.fst.tkhzn.sdsi.repositories.*;
import ma.fst.tkhzn.sdsi.responses.AffectationList;
import ma.fst.tkhzn.sdsi.responses.AffectationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ma.fst.tkhzn.sdsi.requests.AffectationRequest;
import ma.fst.tkhzn.sdsi.requests.ListAff;
import ma.fst.tkhzn.sdsi.requests.RessourceRequest;
import ma.fst.tkhzn.sdsi.requests.codes;

@RestController
@RequestMapping(value = {"api/affectationservice"})
@CrossOrigin
public class AffectationService {

	@Autowired
	RessourceRepository ressourcerepository;

	@Autowired
	FournisseurRepository fournisseurrepository;

	@Autowired
	UtilisateurRepository utilisateurrepository;

	@Autowired
	AffectationRepository affectationRepository;

	@Autowired
	DepartementRepository departementRepository;

	List affecterAuxDEP_PERS = new ArrayList();

	@RequestMapping(path = "/ListAffectations", method = RequestMethod.GET)
	public List<AffectationList> ListAffectations() {
		List<AffectationList> affec = new ArrayList<>();
		List<Ressource_personnels> affectations= affectationRepository.findAll();
		List<Ressource_personnels> listes = new ArrayList<>();
		listes.add(affectations.get(0));
		System.out.println(listes.size());
		for(Ressource_personnels aff:affectations){
			for(int i=0;i< listes.size();i++){
				if(!aff.getCode_r().equals(listes.get(i).getCode_r())){
					listes.add(aff);
				}
			}
		}
		for(Ressource_personnels a : listes){
			if(a.getDepartement() == null){
				AffectationList affectationList = new AffectationList(a.getCode_r(),a.getLogin_p(),a.getDate_affectation());
				affec.add(affectationList);
			}
			else{
				Departement dep = departementRepository.getDepartement(a.getDepartement());
				String nomm=dep.getNomDep();
				AffectationList affectationList = new AffectationList(a.getCode_r(),nomm,a.getDate_affectation());
				affec.add(affectationList);
			}
		}
		return affec;
	}


	@RequestMapping(path = "/listaffectations", method = RequestMethod.GET)
	public ListAff addAffectation() {
		List<Utilisateur> pers_dep=utilisateurrepository.findAll();
		List<Departement> departement=departementRepository.findAll();
		return new ListAff(departement, pers_dep);
	}


//	@RequestMapping(path = "/addAffe", method = RequestMethod.POST)
//	public void addAffe(AffectationRequest aff) {
//		String ressource=aff.getRessource();
//		Ressource ress = ressourcerepository.findRess(ressource);
//		String pers_dep=aff.getPers_dep();
//		if(aff.getDep() != null){
//			for(Utilisateur u : utilisateurrepository.findBydepartement(departementRepository.findById(aff.getDep()).get())){
//				Ressource_personnels r = new Ressource_personnels(ressource,u.getLogin(),new Date(),aff.getDep());
//					 affectationRepository.save(r);
//
//				ress.setEstAffecter(true);
//				ressourcerepository.save(ress);
//			}
//		}
//		else{
//			System.out.println(ressource);
//			if(ressource!=null) {
//				Ressource_personnels r = new Ressource_personnels(ressource, pers_dep, new Date(), aff.getDep());
//				affectationRepository.save(r);
//			}
//			ress.setEstAffecter(true);
//			ressourcerepository.save(ress);
//		}
//	}

	@RequestMapping(path = "/addAffe", method = RequestMethod.POST)
	public void addAffe(@RequestBody String code) {
		System.out.println(code);
//		Ressource r = ressourcerepository.findById(code);
		Ressource r = ressourcerepository.findRess(code);
		System.err.println(r);
		r.setLivrer(false);
		ressourcerepository.save(r);
	}

	@PostMapping("/api/foos")
	public String getFoos(@RequestBody int id) { 
		System.out.println(id * 2);
	    return "ID: " + id;
	}
//	@PostMapping("/api/foos")
//	public String getFoos(@RequestBody codes id) { 
//		System.out.println(id.getCode());
//	    return "ID: " + id;
//	}
	
  //Modifier Affectation (clique)
  	@RequestMapping(path = "/updateAffectation", method = RequestMethod.GET)
  	public Ressource_personnels updateAffectation(String code, String login) {
		AffectationResponse affectation = new AffectationResponse();
		Ressource_personnels aff=affectationRepository.getAffectation(login,code);
//		List<Utilisateur> pers_dep=utilisateurrepository.findAll();
//		List<Departement> departement=departementRepository.findAll();
		affectation.setAffectation(aff);
//		affectation.setPersonnels(pers_dep);
//		affectation.setDepartements(departement);
  	    return aff;
  	 }


  	//modifier Affectation (formulaire)
  	@SuppressWarnings("null")
	@RequestMapping(path = "/updateAffe", method = RequestMethod.POST)
  	public void updateAffe(AffectationRequest aff) {
		String ressource=aff.getRessource();
		Ressource ress = ressourcerepository.findRess(ressource);
		System.out.println(ressource);
		String pers_dep=aff.getPers_dep();
		if(aff.getDep()!=null){
			for(Utilisateur u : utilisateurrepository.findBydepartement(departementRepository.findById(aff.getDep()).get())){
				affectationRepository.save(new Ressource_personnels(ressource,u.getLogin(),new Date(),aff.getDep()));
				ress.setEstAffecter(true);
				ressourcerepository.save(ress);
			}
		}
		else{
			affectationRepository.save(new Ressource_personnels(ressource,pers_dep,new Date(),null));
			ress.setEstAffecter(true);
			ressourcerepository.save(ress);
		}
  	}



	//supprimer Affectation
		@RequestMapping(path = "/deleteAffec", method = RequestMethod.GET)
		public void deleteAffec(AffectationRequest aff) {
			String ressource=aff.getRessource();
			Ressource ress = ressourcerepository.findRess(ressource);
			String pers_dep=aff.getPers_dep();
			if(aff.getDep()!=null){
				for(Utilisateur u : utilisateurrepository.findBydepartement(departementRepository.findById(aff.getDep()).get())){
					affectationRepository.deleteAff(ressource,u.getLogin());
					ress.setEstAffecter(false);
					ressourcerepository.save(ress);
				}
			}
			else{
				affectationRepository.deleteAff(ressource,pers_dep);
				ress.setEstAffecter(false);
				ressourcerepository.save(ress);
			}
		}
}
