package models;

import java.io.*;

public class Jogador {
    public int id;
    public String nome;
    public String nascimento;
    public String time;
    public String setor;
    public String posicao;
    public int altura;

    public Jogador(int id, String nome, String nascimento, String dados, int altura){
        String[] propriedades = dados.split("/");

        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.time = propriedades[0];
        this.setor = propriedades[1];
        this.posicao = propriedades[2];
        this.altura = altura;
    }

    public Jogador(){
        this.id = 0;
        this.nome = "";
        this.nascimento = "";
        this.time = "";
        this.setor = "";
        this.posicao = "";
        this.altura = 0;
    }

    public String toString(){
        return "\nID: " + id +
                "\nNome: " + nome +
                "\nNascimento: " + nascimento +
                "\nTime: " + time +
                "\nSetor: " + setor +
                "\nPosição: " + posicao +
                "\nAltura: " + altura;
    }

    public String getDados(){
        return time + "/" +
                setor + "/" +
                posicao;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(nome);
        dos.writeUTF(nascimento);
        dos.writeUTF(getDados());
        dos.writeInt(altura);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        id = dis.readInt();
        nome = dis.readUTF();
        nascimento = dis.readUTF();
        String[] propriedades = dis.readUTF().split("/");
        time = propriedades[0];
        setor = propriedades[1];
        posicao = propriedades[2];
        altura = dis.readInt();
    }
}
