
class Passagem{
    private int id;
    private String destino;
    private String partida;
    private int numeroAssento;

    public Passagem(){
        id = 0;
        partida = "";
        destino = "";
        numeroAssento = 0;
    }

    //#region Gets
    public String getDestino() {return destino;}
    public int getId() {return id;}
    public int getNumeroAssento() {return numeroAssento;}
    public String getPartida() {return partida;}
    //#endregion

    //#region
    public void setDestino(String destino) {this.destino = destino;}
    public void setId(int id) {this.id = id;}
    public void setNumeroAssento(int numeroAssento) {this.numeroAssento = numeroAssento;}
    public void setPartida(String partida) {this.partida = partida;}
    //#endregion
}