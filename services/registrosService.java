package services;

import models.Registro;

import java.io.RandomAccessFile;
import java.util.Random;

public class registrosService {

    public static void criaRegistro(Registro registro){
        try{
            indicesService indices = new indicesService();
            RandomAccessFile arq = new RandomAccessFile("dados//jogadores.db", "rw");

            int id = geraId();

            arq.seek(arq.length());
            indicesService.criaIndice(id, arq.getFilePointer());

            arq.writeInt(registro.tamanho);
            arq.writeChar(registro.lapide);
            arq.writeInt(id + 1);
            arq.write(registro.dados);

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static Registro lerRegistro(long endereco){
        Registro registro = new Registro();

        try{
            RandomAccessFile arq = new RandomAccessFile("dados//jogadores.db", "rw");
            arq.seek(endereco);

            registro.tamanho = arq.readInt();
            registro.lapide = arq.readChar();
            registro.id = arq.readInt();
            byte[] dados = new byte[registro.tamanho];
            arq.read(dados);
            registro.dados = dados;

            arq.close();

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return registro;
    }

    public static void atualizaRegistro(int id, byte[] dadosJogadorAtualizados){
        long endereco = indicesService.pegaEndereco(id);

        try{
            RandomAccessFile arq = new RandomAccessFile("dados//jogadores.db", "rw");
            arq.seek(endereco);

            int tamanho = arq.readInt();
            System.out.println("Tamando antigo: " + tamanho + " Tamanho novo: " + dadosJogadorAtualizados.length);
            if(dadosJogadorAtualizados.length > tamanho){
                excluiRegistro(endereco);
                Registro registro = new Registro(dadosJogadorAtualizados);

                arq.seek(arq.length());
                indicesService.atualizaEndereco(id, arq.length());

                arq.writeInt(registro.tamanho);
                arq.writeChar(registro.lapide);
                arq.writeInt(id);
                arq.write(registro.dados);
            }else{
                char lapide = arq.readChar();
                if(lapide == '*'){
                    System.out.println("Registro excluído");
                }

                int idRegistro = arq.readInt();
                if(id == idRegistro){
                    arq.write(dadosJogadorAtualizados);
                }
            }

            arq.close();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static void excluiRegistro(long endereco){
        try{
            RandomAccessFile arq = new RandomAccessFile("dados//jogadores.db", "rw");
            arq.seek(endereco);

            int tamanho = arq.readInt();
            arq.writeChar('*');

            arq.close();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static int geraId(){
        try {
            RandomAccessFile arq = new RandomAccessFile("dados//jogadores.db", "rw");

            arq.seek(0);
            int id = arq.readInt();
            id++;
            arq.seek(0);
            arq.writeInt(id);

            return id;
        }catch (Exception exception){
            exception.printStackTrace();
            return 0;
        }
    }

    public static int pegaIdAtual(){
        try {
            RandomAccessFile arq = new RandomAccessFile("dados//jogadores.db", "rw");

            arq.seek(0);
            int id = arq.readInt();

            return id;
        }catch (Exception exception){
            exception.printStackTrace();
            return 0;
        }
    }
}
