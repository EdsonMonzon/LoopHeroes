package AplicacionPack;

public class Sprite {
    //Al llamar con un get y un id me devuelva la bufferedImg, esto para cada tipo

    static String[] PantallasImg = new String[]
            {"Imagenes/imgFondos/PantallaInicio.jpg",
                    "Imagenes/imgFondos/PantallaModosJuego.jpg",
                    "Imagenes/imgFondos/PantallaPartidaCompetitiva.jpg",
                    "Imagenes/imgFondos/PantallaPartidaPersonalizada.jpg",
                    "Imagenes/imgFondos/PantallaCargarPartida.jpg",
                    "Imagenes/imgFondos/PantallaSeleccionHeroe.jpg",
                    "Imagenes/imgFondos/PantallaJuego.jpg",
            };
    static String[] Fichas = new String[]{
            "Imagenes/imgSprites/Jugador1.jpg",
            "Imagenes/imgSprites/Jugador2.jpg",
            "Imagenes/imgSprites/Jugador3.jpg",
            "Imagenes/imgSprites/Jugador4.jpg",
    };
    static String[] Casillas = new String[]{
            "Imagenes/imgSprites/Casilla.jpg",
            "Imagenes/imgSprites/Casilla1Revelada.jpg",
            "Imagenes/imgSprites/Casilla3Retrocede.jpg",
            "Imagenes/imgSprites/Casilla2Avanza.jpg",
            "Imagenes/imgSprites/Casilla4Again.jpg",
            "Imagenes/imgSprites/Casilla5Lost.jpg",
            "Imagenes/imgSprites/Casilla6Lanza1.jpg",
            "Imagenes/imgSprites/Casilla7Doble.jpg",
            "Imagenes/imgSprites/Casilla8RetrocedeTodo.jpg",
            "Imagenes/imgSprites/Casilla9NoReveal.jpg",
            "Imagenes/imgSprites/Casilla10Cura.png",
            "Imagenes/imgSprites/Casilla11Tormenta.png",
            "Imagenes/imgSprites/Casilla12Atacar.png",
            "Imagenes/imgSprites/Casilla13Moneda.png",
            "Imagenes/imgSprites/Casilla14Proteccion.png",
            "Imagenes/imgSprites/Casilla15Grieta.png",
    };
    static String[] Estaticos = new String[]{
            "Imagenes/imgSprites/FondoTablero.jpg",
            "Imagenes/imgSprites/BarraLateral.jpg",
            "Imagenes/imgSprites/FondoMenu.jpg",
            "Imagenes/imgSprites/MenuStats.png",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado1_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado2_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado3_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado4_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado5_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado6_Normal.jpg",
    };
    static String[] botonesNormales = new String[]
            {"Imagenes/imgBotones/imgBotonesNormales/BotonAtras_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonStart_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonSalir_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonPartidaCompetitiva_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonPartidaPersonalizada_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCargarPartida_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCargar1_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCargar2_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCargar3_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/Boton2Jugadores_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/Boton3Jugadores_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/Boton4Jugadores_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonTirar_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonPause_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonCerrar_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonTerminarPartida_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonGuardar_Normal.jpg",
                    "Imagenes/imgBotones/imgBotonesNormales/BotonSaltarTurno_Normal.png",
            };
    static String[] botonesHover = new String[]
            {"Imagenes/imgBotones/imgBotonesHover/BotonAtras_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonStart_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonSalir_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonPartidaCompetitiva_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonPartidaPersonalizada_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCargarPartida_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCargar1_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCargar2_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCargar3_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/Boton2Jugadores_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/Boton3Jugadores_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/Boton4Jugadores_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonTirar_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonPause_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonCerrar_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonTerminarPartida_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonGuardar_Hover.jpg",
                    "Imagenes/imgBotones/imgBotonesHover/BotonSaltarTurno_Hover.png",
            };
    static String[] botonesPress = new String[]
            {"Imagenes/imgBotones/imgBotonesPress/BotonAtras_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonStart_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonSalir_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonPartidaCompetitiva_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonPartidaPersonalizada_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCargarPartida_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCargar1_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCargar2_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCargar3_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/Boton2Jugadores_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/Boton3Jugadores_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/Boton4Jugadores_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonTirar_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonPause_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonCerrar_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonTerminarPartida_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonGuardar_Press.jpg",
                    "Imagenes/imgBotones/imgBotonesPress/BotonSaltarTurno_Press.png",
            };
    static String[] botonesHeroeNormales=new String[]{
            "Imagenes/imgHeroes/imgHeroesNormales/HeroeArquero_Normal.jpg",
            "Imagenes/imgHeroes/imgHeroesNormales/HeroeBanquero_Normal.jpg",
            "Imagenes/imgHeroes/imgHeroesNormales/HeroeCorredor_Normal.jpg",
            "Imagenes/imgHeroes/imgHeroesNormales/HeroePaladin_Normal.jpg",
    };
    static String[] botonesHeroeHover=new String[]{
            "Imagenes/imgHeroes/imgHeroesHover/HeroeArquero_Hover.jpg",
            "Imagenes/imgHeroes/imgHeroesHover/HeroeBanquero_Hover.jpg",
            "Imagenes/imgHeroes/imgHeroesHover/HeroeCorredor_Hover.jpg",
            "Imagenes/imgHeroes/imgHeroesHover/HeroePaladin_Hover.jpg",
    };
    static String[] botonesHeroePress=new String[]{
            "Imagenes/imgHeroes/imgHeroesPress/HeroeArquero_Press.jpg",
            "Imagenes/imgHeroes/imgHeroesPress/HeroeBanquero_Press.jpg",
            "Imagenes/imgHeroes/imgHeroesPress/HeroeCorredor_Press.jpg",
            "Imagenes/imgHeroes/imgHeroesPress/HeroePaladin_Press.jpg",
    };
    static String[] botonesDadoPress=new String[]{
            "Imagenes/imgBotones/imgBotonesPress/BotonDado1_Press.jpg",
            "Imagenes/imgBotones/imgBotonesPress/BotonDado2_Press.jpg",
            "Imagenes/imgBotones/imgBotonesPress/BotonDado3_Press.jpg",
            "Imagenes/imgBotones/imgBotonesPress/BotonDado4_Press.jpg",
            "Imagenes/imgBotones/imgBotonesPress/BotonDado5_Press.jpg",
            "Imagenes/imgBotones/imgBotonesPress/BotonDado6_Press.jpg",
            "Imagenes/imgBotones/imgBotonesPress/BotonDadoAtacar_Press.png",
            "Imagenes/imgBotones/imgBotonesPress/BotonDadoCurar_Press.png",
            "Imagenes/imgBotones/imgBotonesPress/BotonDadoMoneda_Press.png",

    };
    static String[] botonesDadoNormal=new String[]{
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado1_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado2_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado3_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado4_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado5_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDado6_Normal.jpg",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDadoAtacar_Normal.png",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDadoCurar_Normal.png",
            "Imagenes/imgBotones/imgBotonesNormales/BotonDadoMoneda_Normal.png",

    };static String[] botonesDadoHover=new String[]{
            "Imagenes/imgBotones/imgBotonesHover/BotonDado1_Hover.jpg",
            "Imagenes/imgBotones/imgBotonesHover/BotonDado2_Hover.jpg",
            "Imagenes/imgBotones/imgBotonesHover/BotonDado3_Hover.jpg",
            "Imagenes/imgBotones/imgBotonesHover/BotonDado4_Hover.jpg",
            "Imagenes/imgBotones/imgBotonesHover/BotonDado5_Hover.jpg",
            "Imagenes/imgBotones/imgBotonesHover/BotonDado6_Hover.jpg",
            "Imagenes/imgBotones/imgBotonesHover/BotonDadoAtacar_Hover.png",
            "Imagenes/imgBotones/imgBotonesHover/BotonDadoCurar_Hover.png",
            "Imagenes/imgBotones/imgBotonesHover/BotonDadoMoneda_Hover.png",

    };

    public static String getBotonesDadoNormal(int i) {
        return botonesDadoNormal[i-1];
    }
    public static String getBotonesDadoHover(int i) {
        return botonesDadoHover[i-1];
    }
    public static String getBotonesDadoPress(int i) {
        return botonesDadoPress[i-1];
    }

    public static String getBotonesHeroeNormales(int i){return botonesHeroeNormales[i-1];}

    public static String getBotonesHeroeHover(int i) {
        return botonesHeroeHover[i-1];
    }
    public static String getBotonesHeroePress(int i) {
        return botonesHeroePress[i-1];
    }

    public static String getPantallasImg(int i) {
        return PantallasImg[i - 1];
    }

    public static String getBotonesNormales(int i) {
        return botonesNormales[i - 1];
    }

    public static String getBotonesHover(int i) {
        return botonesHover[i - 1];
    }

    public static String getBotonesPress(int i) {
        return botonesPress[i - 1];
    }

    public static String getFichas(int i) {return Fichas[i];}
    public static String getCasillas(int i) {return Casillas[i];}
    public static String getEstaticos(int i) {return Estaticos[i - 1];}
}
