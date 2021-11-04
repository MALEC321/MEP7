package ca.ulaval.glo4002.game.domain.turn;

public class Article {

    private float prix;
    private boolean taxable;
    private CalculateurTaxes calculateurTaxes;

    public Article(CalculateurTaxes calculateurTaxes, float prix, boolean taxable) {
        this.calculateurTaxes = calculateurTaxes;
        this.prix = prix;
        this.taxable = taxable;
    }

    public void facturerSur(int quantite, FactureBuilder factureBuilder) {
        float sousTotal = prix * quantite;
        factureBuilder.avecSousTotal(sousTotal);

        if(taxable) {
            float taxes = calculateurTaxes.calculer(sousTotal);
            factureBuilder.avecTaxes(taxes);
        }
    }
}
//public interface FactureBuilder { /* ... */ }
//public interface CalculateurTaxes { /* ... */ }