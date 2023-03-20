import models.Jogador;
import models.Registro;
import services.registrosService;
import services.indicesService;

import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args){

        int id = 0;
        registrosService registrosService = new registrosService();

        Jogador jogador = new Jogador(1, "Max", "04/04/2002", "Grupo do FUT/Meio Campo/MC", 178);
        Jogador jogador1 = new Jogador();

        try {
            //Cria e lê primeiro registro
            /*Registro registro = new Registro(jogador.toByteArray());
            registrosService.criaRegistro(registro);

            Registro registro1 = new Registro();
            long endereco = indicesService.pegaEndereco(1);
            registro1 = registrosService.lerRegistro(endereco);

            jogador1.fromByteArray(registro1.dados);

            System.out.println(jogador1.toString());
            System.out.println(jogador.toString());*/
            //Atualiza registro de Id1
            //registrosService.atualizaRegistro(1, jogador.toByteArray());

            //Ler registro de id 1
            Registro registro1 = new Registro();
            long endereco = indicesService.pegaEndereco(1);
            registro1 = registrosService.lerRegistro(endereco);

            jogador1.fromByteArray(registro1.dados);

            System.out.println(jogador1.toString());
            System.out.println(jogador.toString());

            //Reset do indice de ID
            /*try{
                RandomAccessFile arq = new RandomAccessFile("src/dados/jogadores.db", "rw");
                arq.seek(0);

                arq.writeInt(0);

            }catch (Exception exception){
                exception.printStackTrace();
            }*/
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}