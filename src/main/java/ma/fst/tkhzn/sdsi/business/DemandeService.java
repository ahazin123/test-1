package ma.fst.tkhzn.sdsi.business;

import ma.fst.tkhzn.sdsi.entities.*;
import ma.fst.tkhzn.sdsi.repositories.*;
import ma.fst.tkhzn.sdsi.requests.DemandeRequest;
import ma.fst.tkhzn.sdsi.requests.Validation;
import ma.fst.tkhzn.sdsi.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController @CrossOrigin
@RequestMapping(value = {"api/demandeservice"})
public class DemandeService {
    @Autowired
    AppelOffreRep appelOffreRep;
    @Autowired
    EnseignantRepository enseignantrepository;
    @Autowired
    Ressource_dRepository ressource_dRepository;
    @Autowired
    Imprimate_dRepository imprimate_dRepository;
    @Autowired
    Ordinateur_dRepository ordinateur_dRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    DemandeRepository demandeRepository;
    @Autowired
    DepartementRepository departementRepository;

    //chef au nom du depart terminer
    @RequestMapping(path = "/ajouterDemande", method = RequestMethod.POST)
    public void ajouterDemande(@RequestBody DemandeRequest demande,Principal login){
        Utilisateur user = utilisateurRepository.findByLogin(login.getName());
        Demande d = demandeRepository.getId();
        if(d == null) {
            demandeRepository.save(new Demande(null, 0, 0, new Date()));
            d = demandeRepository.getId();
        }
        if (demande.getType().equals("imprimante")){
            Imprimante_d imp = demande.getImprimante_d();
            imp.setDep(user.getDepartement().getId());
            imp.setId_demande(d.getId());
            imprimate_dRepository.save(imp);
        }
        else {
            Ordinateur_d ord = demande.getOrdinateur_d();
            ord.setDep(user.getDepartement().getId());
            ord.setId_demande(d.getId());
            ordinateur_dRepository.save(ord);
        }
    }

    //personnel terminer
    @RequestMapping(path = "/addDemande", method = RequestMethod.POST)
    public void addDemande(@RequestBody DemandeRequest demande,Principal login){
        Utilisateur user = utilisateurRepository.findByLogin(login.getName());
        Demande d = demandeRepository.getId();
        if(d == null) {
            demandeRepository.save(new Demande(null, 0, null, new Date()));
            d = demandeRepository.getId();
        }
        if (demande.getType().equals("imprimante")){
            Imprimante_d imp = demande.getImprimante_d();
            imp.setUser(user);
            imp.setId_demande(d.getId());
            imprimate_dRepository.save(imp);
        }
        else {
            Ordinateur_d ord = demande.getOrdinateur_d();
            ord.setUser(user);
            ord.setId_demande(d.getId());
            ordinateur_dRepository.save(ord);
        }
    }

    //demande chef
    @RequestMapping(path = "/envoiDemande", method = RequestMethod.POST)
    public void envoiDemande(@RequestBody List<Validation> validations){
        demandeRepository.save(new Demande());
        for(Validation validation: validations) {
            if (validation.getCheck()) {
                Ressource_d r = ressource_dRepository.findById(validation.getCode()).get();
                r.setChecked(1);
                ressource_dRepository.save(r);
            }
        }
        for(Validation v: validations) System.out.println(v);
    }

    // Terminer
    @RequestMapping(path = "/supprimeDemande", method = RequestMethod.POST)
    public void deleteDemande(@RequestBody String code){
        Imprimante_d imp = imprimate_dRepository.findByCode(Long.parseLong(code));
        if(imp == null) {
            Ordinateur_d ord = ordinateur_dRepository.findByCode(Long.parseLong(code));
            ordinateur_dRepository.delete(ord);
        }
        else imprimate_dRepository.delete(imp);

    }

    @RequestMapping(path = "/listerMesDemandes", method = RequestMethod.GET)
    public List<UserRess> listerMesDemandes(Principal login){
        List<UserRess> userRess=new ArrayList<>();
        Utilisateur user = utilisateurRepository.findByLogin(login.getName());
        for (Imprimante_d imp:imprimate_dRepository.findRess_d(user.getLogin())){
            if(imp.getChecked() == 0) {
                ImprimanteR impr=new ImprimanteR(imp);
                userRess.add(new UserRess("personnel", null, imp.getUser().getNom() + " " + imp.getUser().getPrenom(), imp.getUser().getLogin(),imp.getCode(),"imprimante",null, impr, null));
            }
        }
        for(Ordinateur_d ord:ordinateur_dRepository.findOrd_d(user.getLogin())){
            if(ord.getChecked() == 0) {
                OrdinateurR ordr=new OrdinateurR(ord);
                userRess.add(new UserRess("personnel", null, ord.getUser().getNom() + " " + ord.getUser().getPrenom(), ord.getUser().getLogin(),ord.getCode(),"ordinateur",ordr,null, null));
            }
        }
        return userRess;
    }

    //les b eng chez le chef
    @RequestMapping(path = "/listerBesoins", method = RequestMethod.GET)
    public List<UserRess> listerBesoins(Principal login){
        List<UserRess> userRess=new ArrayList<>();
        Utilisateur user=utilisateurRepository.findByLogin(login.getName());
        List<Utilisateur> users=utilisateurRepository.findBydepartement(user.getDepartement());
        for(Utilisateur usr:users){
            for (Imprimante_d imp:imprimate_dRepository.findRess_d(usr.getLogin())){
                if(imp.getChecked() == 0) {
                    ImprimanteR impr = new ImprimanteR(imp);
                    userRess.add(new UserRess("personnel", null, imp.getUser().getNom() + " " + imp.getUser().getPrenom(), imp.getUser().getLogin(), imp.getCode(), "imprimante", null, impr, null));
                }
            }
            for(Ordinateur_d ord:ordinateur_dRepository.findOrd_d(usr.getLogin())){
                if(ord.getChecked() == 0) {
                    OrdinateurR ordr = new OrdinateurR(ord);
                    userRess.add(new UserRess("personnel", null, ord.getUser().getNom() + " " + ord.getUser().getPrenom(), ord.getUser().getLogin(), ord.getCode(), "ordinateur", ordr, null, null));
                }
            }
        }

        Departement dep = user.getDepartement();
        for (Imprimante_d imp:imprimate_dRepository.findRess_dep(dep.getId())){
            if(imp.getChecked() == 0) {
                ImprimanteR impr=new ImprimanteR(imp);
                userRess.add(new UserRess("departement", dep.getId(), dep.getNomDep(), null, imp.getCode(),"imprimante",null, impr, null));
            }
        }
        for(Ordinateur_d ord:ordinateur_dRepository.findRess_dep(dep.getId())){
            if(ord.getChecked() == 0) {
                OrdinateurR ordr=new OrdinateurR(ord);
                userRess.add(new UserRess("departement", dep.getId(), dep.getNomDep(), null, ord.getCode(),"ordinateur", ordr,null, null));
            }
        }
//        for(UserRess r: userRess) System.out.println(r);
        return userRess;
    }

    public String ordToString(Ordinateur_d ord){
        return ord.getMarque()+" "+ord.getCpu()+" "+ord.getRam()+" "+ord.getDisque_d()+" "+ord.getEcran();
    }
    public String impToString(Imprimante_d imp) {
        return imp.getMarque()+" "+imp.getResolution()+" "+imp.getVitesse();
    }

    // terminer
    @RequestMapping(path = "/listerDemandes" ,method = RequestMethod.GET)
    public List<UserRess> listerDemandes(){
        Map<String,Integer>  ords=new HashMap<>();
        Map<String,Integer>  imps=new HashMap<>();
        List<UserRess> userRess=new ArrayList<>();

        Demande d = demandeRepository.find();
        if(d==null) System.out.println("null");
        else {
            Ordinateur_d tmp=new Ordinateur_d();
            for(Ordinateur_d ord:ordinateur_dRepository.listerRess_d(d.getId())){
                Long dep;
                if(ord.getDep() == null) dep = ord.getUser().getDepartement().getId();
                else dep = ord.getDep();
                if(ordToString(ord).equals(ordToString(tmp))){
                    ords.put(ordToString(ord) + " " + dep,ords.get(ordToString(ord) + " " + dep)+ord.getQteD());
                }else {
                    ords.put(ordToString(ord) + " " + dep,ord.getQteD());
                }
                tmp=ord;
            }
            Imprimante_d t=new Imprimante_d();
            for(Imprimante_d imp:imprimate_dRepository.listerRess_d(d.getId())){
                Long dep;
                if(imp.getDep() == null) dep = imp.getUser().getDepartement().getId();
                else dep = imp.getDep();
                if(impToString(imp).equals(impToString(t))){
                    imps.put(impToString(imp) + " " + dep, imps.get(impToString(imp) + " " + dep)+imp.getQteD());
                }else {
                    imps.put(impToString(imp) + " " + dep, imp.getQteD());
                }
                t=imp;
            }

            Long i=0L;
            for(String key:ords.keySet()){
                System.out.println(key);
                int qt=ords.get(key);
                String[] carat=key.split(" ");
                Ordinateur_d ord=new Ordinateur_d(carat[1],Integer.parseInt(carat[3]),Float.parseFloat(carat[4]),carat[0],Integer.parseInt(carat[2]));
                OrdinateurR ordr=new OrdinateurR(ord);
                ordr.setQteD(qt);
                ordr.setCode(i);
                Departement Dep = departementRepository.getDepartement(Long.parseLong(carat[5]));
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
                Departement Dep = departementRepository.getDepartement(Long.parseLong(carat[3]));
                userRess.add(new UserRess(null, null, null, null, i,"imprimante",null,impr, Dep.getNomDep()));
                i = i + 1;
            }
        }


        for(UserRess r: userRess) System.out.println(r);
        return userRess;
    }

    // en cours valide=1 appel d'offre courante valide=2 livrée
    @RequestMapping(path = "/addAppel", method = RequestMethod.GET)
    public void addAppel(){
        appelOffreRep.save(new AppelOffre());
        int id_Appel= appelOffreRep.getId();
        List<Demande> ds=demandeRepository.findAll();
        for (Demande d:ds){
            demandeRepository.setIdAppel(id_Appel,d.getId());
        }
    }
}

