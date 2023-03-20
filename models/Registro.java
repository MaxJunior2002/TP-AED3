package models;
import services.indicesService;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;

public class Registro {
    public byte[] dados;
    public char lapide;
    public int id;
    public int tamanho;
    RandomAccessFile arq;
    public Registro(byte[] vetorBytes){
        dados = new byte[vetorBytes.length];
        dados = vetorBytes;
        lapide = ' ';
        tamanho = dados.length;
    }

    public Registro(){
        lapide = ' ';
        tamanho = 0;
        id = 0;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(tamanho);
        dos.writeChar(lapide);
        dos.write(dados);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        tamanho = dis.readInt();
        lapide = dis.readChar();
        dis.read(dados);
    }
}
