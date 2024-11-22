package PartidaPack;

import java.util.Scanner;

public class Input {
    final static Scanner input=new Scanner(System.in);

    public static int integer(String mensaje){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
        }else{
            System.out.println(mensaje);
            input.next();
            numero= integer(mensaje);
        }

        return numero;
    }
    public static int integer(){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
        }else{
            System.out.println("El numero introducido no es valido, vuelve a intentarlo");
            input.next();
            numero= integer();
        }

        return numero;
    }
    public static int between(int a,int b, String mensaje){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
            if(!(numero>=a && numero<=b)){
                System.out.println(mensaje);
                numero=between(a,b,mensaje);
            }
        }else{
            System.out.println("Trata de introducir un numero valido");
            input.next();
            numero=between(a,b,mensaje);
        }

        return numero;
    }

    public static int between(int a,int b){
        int numero=0;

        if(input.hasNextInt()){
            numero=input.nextInt();
        }else{
            System.out.println("El numero introducido no es valido , vuelva a intentarlo");
            input.next();
            numero=between(a,b);
        }

        return numero;
    }
}
