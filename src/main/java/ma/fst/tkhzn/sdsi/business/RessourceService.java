package ma.fst.tkhzn.sdsi.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.fst.tkhzn.sdsi.entities.*;
import ma.fst.tkhzn.sdsi.repositories.*;
import ma.fst.tkhzn.sdsi.responses.ImprimanteR;
import ma.fst.tkhzn.sdsi.responses.OrdinateurR;
import ma.fst.tkhzn.sdsi.responses.UserRess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	@Autowired
	DemandeRepository demandeRepository;
	@Autowired
	Ordinateur_dRepository ordinateur_dRepository;
	@Autowired
	Imprimate_dRepository imprimate_dRepository;
	@Autowired
	DepartementRepository departementRepository;
	@Autowired
	Ressource_dRepository ressource_dRepository;

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
		Long maxgroupInt = 0L;
		String maxgroup = ressourcerepository.findmaxGroup();
		if(maxgroup == null) maxgroupInt = 1L;
		else maxgroupInt = Long.parseLong(maxgroup) + 1;
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
				imp.setGroupe(maxgroupInt);
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
				ord.setGroupe(maxgroupInt);
				ordinateurRepository.save(ord);
				idmax = idmax + 1;
			}
		}
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

	// Affichage
	@RequestMapping(path = "/ListLiv", method = RequestMethod.GET)
	public List<Livraison> ListLiv() {
		Map<Long, Ordinateur> ords = new HashMap<>();
		Map<Long, Imprimante> imps = new HashMap<>();
		Map<Long, List<Ressource_d>> ressources_d = new HashMap<>();
		Map<Long, Integer> qtelivrer = new HashMap<>();

		Demande id = demandeRepository.find();
		List<Ordinateur_d> ordi = ordinateur_dRepository.listerRess_d(id.getId());
		List<Imprimante_d> impr = imprimate_dRepository.listerRess_d(id.getId());

		List<Livraison> livs = new ArrayList<>();

		// Les imprimantes
		for (Imprimante i : imprimanteRepository.findAll()) {
			if (i.isLivrer()) {
				if(!qtelivrer.containsKey(i.getGroupe())) {
					qtelivrer.put(i.getGroupe(), 1);
				}
				else {
					qtelivrer.put(i.getGroupe(), qtelivrer.get(i.getGroupe()) + 1);
				}
				if (!imps.containsKey(i.getGroupe())) {
					boolean demander = false;
					for(Imprimante_d _i: impr) {
						if(impToString(_i).equalsIgnoreCase(impToString_(i).toLowerCase()))  {
							demander = true;
							break;
						}
					}
					if(demander) imps.put(i.getGroupe(), i);
				}
			}
		}
		for (Long key: imps.keySet()) {
			Imprimante i = imps.get(key);
			if(!ressources_d.containsKey(i.getGroupe())) {
				ressources_d.put(i.getGroupe(), new ArrayList<>());
			}
			for(Imprimante_d _i : impr) {
				if(impToString(_i).equalsIgnoreCase(impToString_(i).toLowerCase()))  {
					ressources_d.get(i.getGroupe()).add(_i);
				}
			}
		}
		for(Long key:imps.keySet()) {
			Livraison liv = new Livraison();
			Imprimante imp = imps.get(key);
			liv.setCode(key);
			liv.setNom(imp.getMarque() + " " + imp.getMarque() + " " + imp.getVitesse() + " " + imp.getResolution());
			liv.setType("Imprimante");
			Map<Long, List<Ressource_d>> ress_by_dep = new HashMap<>();
			for(Ressource_d r: ressources_d.get(key)) {
				Long iddep;
				if(r.getUser() == null) { iddep = r.getDep(); }
				else { iddep = r.getUser().getDepartement().getId(); }
				if(ress_by_dep.containsKey(iddep)) ress_by_dep.get(iddep).add(r);
				else {
					ress_by_dep.put(iddep, new ArrayList<>());
					ress_by_dep.get(iddep).add(r);
				}
			}

			ArrayList<Departement_> departements = new ArrayList<>();
			for(Long keydep: ress_by_dep.keySet()) {
				Departement d = departementRepository.findById(keydep).get();
				Departement_ departement = new Departement_(keydep, departementRepository.findById(keydep).get().getNomDep(), 0, null);
				List<Personnel> personnels = new ArrayList<>();
				for(Ressource_d r: ress_by_dep.get(keydep)) {
					if(r.getUser() == null) {
						departement.setQte(r.getQteD());
					}
					else {
						Personnel personnel = new Personnel();
						Utilisateur usr = r.getUser();
						personnel.setLogin(usr.getLogin());
						personnel.setNom(usr.getNom() + " " + usr.getPrenom());
						personnel.setQte(r.getQteD());
						personnels.add(personnel);
					}
				}

				departement.setPersonnels(personnels);
				departements.add(departement);
			}
			liv.setDepartements(departements);
			liv.setQte(qtelivrer.get(key));
			livs.add(liv);
		}

		// Les ordinateurs
		for (Ordinateur o : ordinateurRepository.findAll()) {
			if (o.isLivrer()) {
				if(!qtelivrer.containsKey(o.getGroupe())) {
					qtelivrer.put(o.getGroupe(), 1);
				}
				else {
					qtelivrer.put(o.getGroupe(), qtelivrer.get(o.getGroupe()) + 1);
				}
				if (!ords.containsKey(o.getGroupe())) {
					boolean demander = false;
					for(Ordinateur_d _o: ordi) {
						if(ordToString(_o).equalsIgnoreCase(ordToString_(o).toLowerCase()))  {
							demander = true;
							break;
						}
					}
					if(demander) ords.put(o.getGroupe(), o);
				}
			}
		}
		for (Long key: ords.keySet()) {
			Ordinateur o = ords.get(key);
			if(!ressources_d.containsKey(o.getGroupe())) {
				ressources_d.put(o.getGroupe(), new ArrayList<>());
			}
			for(Ordinateur_d _o : ordi) {
				if(ordToString(_o).equalsIgnoreCase(ordToString_(o).toLowerCase()))  {
					ressources_d.get(o.getGroupe()).add(_o);
				}
			}
		}
		for(Long key:ords.keySet()) {
			Livraison liv = new Livraison();
			Ordinateur ord = ords.get(key);
			liv.setCode(key);
			liv.setNom(ord.getMarque() + " " + ord.getRam() + " " + ord.getDd() + " " + ord.getCpu());
			liv.setType("ordinateur");
			Map<Long, List<Ressource_d>> ress_by_dep = new HashMap<>();
			for(Ressource_d r: ressources_d.get(key)) {
				System.out.println(r);
				Long iddep;
				if(r.getUser() == null) { iddep = r.getDep(); }
				else { iddep = r.getUser().getDepartement().getId(); }
				if(ress_by_dep.containsKey(iddep)) ress_by_dep.get(iddep).add(r);
				else {
					ress_by_dep.put(iddep, new ArrayList<>());
					ress_by_dep.get(iddep).add(r);
				}
			}

			ArrayList<Departement_> departements = new ArrayList<>();
			for(Long keydep: ress_by_dep.keySet()) {
				Departement d = departementRepository.findById(keydep).get();
				Departement_ departement = new Departement_(keydep, departementRepository.findById(keydep).get().getNomDep(), null, null);

				List<Personnel> personnels = new ArrayList<>();
				for(Ressource_d r: ress_by_dep.get(keydep)) {

					if(r.getUser() == null) {
						departement.setQte(r.getQteD());
					}
					else {
						Personnel personnel = new Personnel();
						Utilisateur usr = r.getUser();
						personnel.setLogin(usr.getLogin());
						personnel.setNom(usr.getNom() + " " + usr.getPrenom());
						personnel.setQte(r.getQteD());
						personnels.add(personnel);
					}
				}

				departement.setPersonnels(personnels);
				departements.add(departement);
			}
			liv.setDepartements(departements);
			liv.setQte(qtelivrer.get(key));
			livs.add(liv);
		}

		for(Livraison l: livs) {
			System.out.println(l);
		}
		return livs;
	}

	public String ordToString(Ordinateur_d ord){
		return ord.getMarque()+" "+ord.getCpu()+" "+ord.getRam()+" "+ord.getDisque_d()+" "+ord.getEcran();
	}
	public String impToString(Imprimante_d imp){
		return imp.getMarque()+" "+imp.getResolution()+" "+imp.getVitesse();
	}

	public String ordToString_(Ordinateur ord){
		return ord.getMarque()+" "+ord.getCpu()+" "+ord.getRam()+" "+ord.getDd()+" "+ord.getEcran();
	}
	public String impToString_(Imprimante imp){
		return imp.getMarque()+" "+imp.getResolution()+" "+imp.getVitesse();
	}

	// terminer
	@RequestMapping(path = "/listerDemandes" ,method = RequestMethod.GET)
	public List<UserRess> listerDemandes(){
		Map<String,Integer>  ords=new HashMap<>();
		Map<String,Integer>  imps=new HashMap<>();
		List<UserRess> userRess=new ArrayList<>();

		Demande d = demandeRepository.find();
		Ordinateur_d tmp=new Ordinateur_d();
		for(Ordinateur_d ord:ordinateur_dRepository.listerRess_d(d.getId())){
			if(ordToString(ord).equals(ordToString(tmp))){
				ords.put(ordToString(ord),ords.get(ordToString(ord))+ord.getQteD());
			}else {
				ords.put(ordToString(ord),ord.getQteD());
			}
			tmp=ord;
		}
		Imprimante_d t=new Imprimante_d();
		for(Imprimante_d imp:imprimate_dRepository.listerRess_d(d.getId())){
			if(impToString(imp).equals(impToString(t))){
				imps.put(impToString(imp),imps.get(impToString(imp))+imp.getQteD());
			}else {
				imps.put(impToString(imp),imp.getQteD());
			}
			t=imp;
		}

		Long i=0L;
		for(String key:ords.keySet()){
			int qt=ords.get(key);
			String[] carat=key.split(" ");
			Ordinateur_d ord=new Ordinateur_d(carat[1],Integer.parseInt(carat[3]),Float.parseFloat(carat[4]),carat[0],Integer.parseInt(carat[2]));
			OrdinateurR ordr=new OrdinateurR(ord);
			ordr.setQteD(qt);
			ordr.setCode(i);
			Departement Dep;
			if(ord.getDep() == null) {
				Dep = ord.getUser().getDepartement();
			}
			else {
				Dep = departementRepository.getDepartement(ord.getDep());
			}
			userRess.add(new UserRess(null, null, null, null,i,"ordinateur",ordr,null, Dep.getNomDep()));
			i = i + 1;
		}
		for(String key:imps.keySet()){
			int qt=imps.get(key);
			String[] carat=key.split(" ");
			Imprimante_d imp=new Imprimante_d(carat[0],Float.parseFloat(carat[1]),Float.parseFloat(carat[2]));
			ImprimanteR impr=new ImprimanteR(imp);
			impr.setQteD(qt);
			impr.setCode(i);
			Departement Dep;
			if(imp.getDep() == null) {
				Dep = imp.getUser().getDepartement();
			}
			else {
				Dep = departementRepository.getDepartement(imp.getDep());
			}
			userRess.add(new UserRess(null, null, null, null, i,"imprimante",null,impr, Dep.getNomDep()));
			i = i + 1;
		}

		for(UserRess r: userRess) System.out.println(r);
		return userRess;
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
