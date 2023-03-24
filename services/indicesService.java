package services;

import java.io.RandomAccessFile;
import java.util.Random;

public class indicesService {

    public static void criaIndice(int id, long endereco){
        try{
            RandomAccessFile arq = new RandomAccessFile("dados//jogadores.db", "rw");

            arq.seek(arq.length());

            arq.writeInt(id);
            arq.writeLong(endereco);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static long pegaEndereco(int id){
        int idLido = 0;
        long endereco = 0;

        try{
            RandomAccessFile arq = new RandomAccessFile("..//dados//indices.db", "rw");
            arq.seek(0);
            while(idLido != id){
                idLido = arq.readInt();
                endereco = arq.readLong();
            }

            return endereco;
        }catch (Exception exception){
            exception.printStackTrace();
            return endereco;
        }
    }

    public static void atualizaEndereco(int id, long novoEndereco){
        int idLido = 0;
        long endereco = 0;
        long enderecoDoIndiceAtual = 0;

        try{
            RandomAccessFile arq = new RandomAccessFile("..//dados//indices.db", "rw");
            arq.seek(0);
            while(idLido != id){
                enderecoDoIndiceAtual = arq.getFilePointer();
                idLido = arq.readInt();
                endereco = arq.readLong();
            }

            arq.seek(enderecoDoIndiceAtual);
            idLido = arq.readInt();
            if(idLido == id){
                arq.writeLong(novoEndereco);
            }else{
                System.out.println("Não foi possivel atualizar o endereço do registro no arquivo de indices.");
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
