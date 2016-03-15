package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1, 1);
        varasto = new Varasto(1, 2);
        varasto = new Varasto(-1, 2);
        varasto = new Varasto(-1, -1);
        varasto.toString();
    }

    @Test
    public void nollanLisaaminenEiTeeMitaan() {
        varasto.lisaaVarastoon(0);
        assertTrue(varasto.getSaldo() == 0);
    }

    @Test
    public void liikaaLisaaminenLaittaaTayteen() {
        varasto.lisaaVarastoon(15);
        assertTrue(varasto.getSaldo() == 10);
    }

    @Test
    public void lisaaminenToimiiKunEiYlitetäTilavuutta() {
        varasto.lisaaVarastoon(7);
        assertTrue(varasto.getSaldo() == 7);
    }
    
    @Test
    public void yhdellaLiikaaLisaaminenLaittaaTayteen() {
        varasto.lisaaVarastoon(11);
        assertTrue(varasto.getSaldo() == 10);
    }
    
    @Test
    public void taydenTilavuudenLisaaminenLaittaaTayteen() {
        varasto.lisaaVarastoon(10);
        assertTrue(varasto.getSaldo() == 10);
    }
    
    @Test
    public void pienemmallaKuinNollallaLisaaminenEiTeeMitaan() {
        varasto.lisaaVarastoon(-1);
        assertTrue(varasto.getSaldo() == 0);
    }
    
    @Test
    public void yhdellaLisaaminenLisaaSaldoaYhdella() {
        varasto.lisaaVarastoon(1);
        assertTrue(varasto.getSaldo() == 1);
    }
    
    @Test
    public void nollallaOttaminenEiTeeMitaan() {
        varasto.otaVarastosta(0);
        assertTrue(varasto.getSaldo() == 0);
    }
    
    @Test
    public void negatiivisellaLuvullaOttaminenEiMuutaSaldoa() {
        double annettu = varasto.otaVarastosta(-1);
        assertTrue(varasto.getSaldo() == 0);
        assertTrue(annettu == 0);
    }
    
    
    

    @Test
    public void yliSaldonOttaminenAntaaKaikenMahdollisen() {
        varasto.lisaaVarastoon(10);
        double annettu = varasto.otaVarastosta(15);
        assertTrue(varasto.getSaldo() == 0);
        assertTrue(annettu == 10);
    }
    
    @Test
    public void alleSaldonOttaminenAntaaOtetunMaaran() {
        varasto.lisaaVarastoon(10);
        double annettu = varasto.otaVarastosta(7);
        assertTrue(varasto.getSaldo() == 3);
        assertTrue(annettu == 7);
    }
    
    @Test
    public void kaikenOttaminenAntaaKaiken() {
        varasto.lisaaVarastoon(10);
        double annettu = varasto.otaVarastosta(10);
        assertTrue(varasto.getSaldo() == 0);
//        assertTrue(annettu == 10);
    }
    
}
