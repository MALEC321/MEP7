package ca.ulaval.glo4002.game.domain.turn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArticleTest {
    private Article article;
    private CalculateurTaxes calculateurTaxes;
    private FactureBuilder factureBuilder;
    private static final float PRIX_VALIDE = 30.24;
    private static final boolean TAXABLE = true;
    private static final boolean NON_TAXABLE = false;
    private static final int QUANTITE_VALIDE = 3;
    private static final float SOUS_TOTAL_VALIDE = 33;

    @BeforeEach
    public void setUp() {
        calculateurTaxes = mock(CalculateurTaxes.class);
        factureBuilder = mock(FactureBuilder.class)
    }

    @Test
    public void givenTaxableFacture_thenCallMethodAvecSousTotal(){
        article = new Article(calculateurTaxes, PRIX_VALIDE, NON_TAXABLE);

        article.facturerSur(QUANTITE_VALIDE, factureBuilder);
        verify(factureBuilder).avecSousTotal(SOUS_TOTAL_VALIDE);
    }

    @Test
    public void givenNonTaxableFacture_thenCallMethodAvecTaxes(){
        article = new Article(calculateurTaxes, PRIX_VALIDE, TAXABLE);

        article.avecTaxes(QUANTITE_VALIDE, factureBuilder);
        verify(factureBuilder).avecSousTotal(SOUS_TOTAL_VALIDE);
    }
}