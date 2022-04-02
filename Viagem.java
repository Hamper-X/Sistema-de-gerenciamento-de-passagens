import java.sql.Array;
import java.util.ArrayList;

class Viagem {
    private String localPartida;
    private String localChegada;
    ArrayList<Passagem> passagensDisponiveis = new ArrayList<Passagem>();
    private char[][] lugaresDisponiveis = new char[4][12]; 

    public Viagem(){
        localChegada = "";
        localPartida = "";
    }

    public String getLocalChegada() { return localChegada; }
    public String getLocalPartida() { return localPartida; }
    public char[][] getLugaresDisponiveis() { return lugaresDisponiveis; }
    public ArrayList<Passagem> getPassagensDisponiveis() {return passagensDisponiveis; }

    public void setLocalChegada(String localChegada) { this.localChegada = localChegada; }
    public void setLocalPartida(String localPartida) { this.localPartida = localPartida; }
    public void setLugaresDisponiveis(char[][] lugaresDisponiveis) { this.lugaresDisponiveis = lugaresDisponiveis; }
    public void setPassagensDisponiveis(ArrayList<Passagem> passagensDisponiveis) { this.passagensDisponiveis = passagensDisponiveis; }   
    
    // Colocar letra na frente de cada linha 
    public void mostrarLugares(){

        for(int i = 0; i< 4; i++){
            for(int j=0; j<12; j++){
                System.out.println(" [ "+lugaresDisponiveis[i][j]+" ] ");
            }
        }
    }
}



