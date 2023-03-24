import models.Jogador;
import models.Registro;
import services.registrosService;
import services.indicesService;

import java.io.BufferedReader;
import java.io.RandomAccessFile;
import java.io.FileReader;

public class Main {

    public static void inicializacao() throws Exception{
        try{
            RandomAccessFile arq = new RandomAccessFile("dados//indices.db", "rw");
       
            // vamos excluir todo o conteúdo do arquivo
            arq.setLength(0);
            arq.close();    
        }catch(Exception exception){
            exception.printStackTrace();
        }

        try{
            RandomAccessFile arquivo = new RandomAccessFile("dados//jogadores.db", "rw");
       
            // vamos excluir todo o conteúdo do arquivo
            arquivo.setLength(0);
            arquivo.writeInt(0);
            arquivo.close();    
        }catch(Exception exception){
            exception.printStackTrace();
        }

        System.out.println("Inicialização realizada com sucesso!");
    }  
    
    public static void cargaDosDados(){
        String csvArquivo = "dados//jogadores.csv";
        String linha = "";
        
        try{
            BufferedReader conteudoCSV = new BufferedReader(new FileReader(csvArquivo));
            while((linha = conteudoCSV.readLine()) != null){
                String[] jogadorDados = linha.split(";");

                int id = Integer.parseInt(jogadorDados[0]);
                String nome = jogadorDados[1];
                String nascimento = jogadorDados[2];
                String dados = jogadorDados[3];
                int altura = Integer.parseInt(jogadorDados[4]);

                Jogador jogador = new Jogador(id, nome, nascimento, dados, altura);
                Registro registro = new Registro(jogador.toByteArray());   
                registrosService.criaRegistro(registro);                     
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

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
            /*Registro registro1 = new Registro();
            long endereco = indicesService.pegaEndereco(1);
            registro1 = registrosService.lerRegistro(endereco);

            jogador1.fromByteArray(registro1.dados);

            System.out.println(jogador1.toString());
            System.out.println(jogador.toString());*/

            //Reset do indice de ID
            /*try{
                RandomAccessFile arq = new RandomAccessFile("src/dados/jogadores.db", "rw");
                arq.seek(0);

                arq.writeInt(0);

            }catch (Exception exception){
                exception.printStackTrace();
            }*/

            inicializacao();
            cargaDosDados();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}