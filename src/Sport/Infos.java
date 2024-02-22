package Sport;

import java.util.Date;
import java.util.Objects;

/**
 * Classe métier représentant les informations associées à une ville pour une course.
 * @author Séraphin Terlinck
 * @version 0.1
 */
public class Infos {

    /**
     * Identifiant unique des informations
     */
    private int idInfos;
    /**
     * Date de départ associée à la ville
     */
    private Date departDate;
    /**
     * Ville associée aux informations
     */
    private Ville ville;

    /**
     * Constructeur par défaut de la classe Infos
     */
    public Infos() {
    }

    /**
     * Constructeur paramètré de la classe Infos
     * @param idInfos Identifiant unique des informations
     * @param departDate Date de départ associée à la ville
     * @param ville Ville associée aux informations
     */
    public Infos(int idInfos, Date departDate, Ville ville) {
        this.idInfos = idInfos;
        this.departDate = departDate;
        this.ville = ville;
    }

    /**
     * Obtient l'identifiant unique des informations
     * @return l'identifiant unique des informations
     */
    public int getIdInfos() {
        return idInfos;
    }

    /**
     * Définit l'identifiant unique des informations
     * @param idInfos nouvel identifiant unique des informations
     */
    public void setIdInfos(int idInfos) {
        this.idInfos = idInfos;
    }

    /**
     * Obtient la date de départ associée à la ville
     * @return la date de départ associée à la ville
     */
    public Date getDepartDate() {
        return departDate;
    }

    /**
     * Définit la date de départ associée à la ville
     * @param departDate nouvelle date de départ associée à la ville
     */
    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    /**
     * Obtient la ville associée aux informations
     * @return la ville associée aux informations
     */
    public Ville getVille() {
        return ville;
    }

    /**
     * Définit la ville associée aux informations
     * @param ville nouvelle ville associée aux informations
     */
    public void setVille(Ville ville) {
        this.ville = ville;
    }

    /**
     * Redéfinition de la méthode equals pour comparer deux ensembles d'informations par leur identifiant unique
     * @param o l'objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return idInfos == infos.idInfos;
    }
}
