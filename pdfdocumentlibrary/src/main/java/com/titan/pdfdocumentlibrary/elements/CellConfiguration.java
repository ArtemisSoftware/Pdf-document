package com.titan.pdfdocumentlibrary.elements;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.titan.pdfdocumentlibrary.utils.Constants;

public class CellConfiguration {

    /**
     * <b>row span</b> > <i>número de linhas de uma tabela que uma célula deve abranger</i>
     */
    private int rowspan;


    /**
     * <b>col span</b> > <i>número de colunas de uma tabela que uma célula deve abranger</i>
     */
    private int  colSpan;

    private int  rotacao, borda;
    private int alinhamentoVertical, alinhamentoHorizontal;
    private float altura, alinhamentoTopo, alinhamentoEsquerda, alinhamentoInferior, alinhamentoDireita;


    private BaseColor cor;


    private PdfPCellEvent evento;


    private boolean sobrePosicaoCor;


    public CellConfiguration(){

        this.rowspan = Constants.NO_DATA;
        this.colSpan = Constants.NO_DATA;
        this.rotacao = Constants.NO_DATA;

        this.alinhamentoVertical = Constants.NO_DATA;
        this.alinhamentoHorizontal = Constants.NO_DATA;
        this.cor = BaseColor.WHITE;

        this.altura = Constants.NO_DATA;
        this.alinhamentoTopo = Constants.NO_DATA;
        this.alinhamentoEsquerda = Constants.NO_DATA;
        this.alinhamentoInferior = Constants.NO_DATA;
        this.alinhamentoDireita = Constants.NO_DATA;

        this.borda = Constants.NO_DATA;
        this.evento = null;

        this.sobrePosicaoCor = true;
    }



    /**
     * Metodo que permite adicionar um evento à celula. <br><br>
     * @param evento um evento
     */
    public void adicionar_Evento(PdfPCellEvent evento){
        this.evento = evento;
    }



    /**
     * Metodo que permite obter o evento da celula
     * @return um evento
     */
    public PdfPCellEvent obter_Evento() {
        return evento;
    }




    /**
     * Metodo que permite adicionar uma valor de grossura da borda. <br><br>
     * @param borda a altura
     */
    public void adicionar_Borda(int borda){
        this.borda = borda;
    }




    /**
     * Metodo que permite obter a grossura da borda
     * @return a grossura da borda
     */
    public int obter_Borda() {
        return borda;
    }


    /**
     * Metodo que permite adicionar um valor para a altura. <br><br>
     * @param altura a altura
     */
    public void adicionar_Altura(float altura){
        this.altura = altura;
    }


    /**
     * Metodo que permite obter a altura
     * @return a altura
     */
    public float obter_Altura() {
        return altura;
    }



    /**
     * Metodo que permite adicionar um valor para a cor de fundo. <br><br>
     * @param cor a cor de fundo
     */
    public void adicionar_CorFundo(BaseColor cor){
        this.cor = cor;
    }

    /**
     * Metodo que permite adicionar um valor para a cor de fundo. <br><br>
     * @param cor a cor de fundo
     * @param sobrePosicaoCor quando true irá sobrepor qualquer cor já existente, quando false apenas sobrepoe a cor (<b> branca</b>)
     */
    public void adicionar_CorFundo(BaseColor cor, boolean sobrePosicaoCor){
        this.cor = cor;
        this.sobrePosicaoCor = sobrePosicaoCor;
    }





    /**
     * Metodo que permite obter a cor de fundo
     * @return a cor de fundo
     */
    public BaseColor obter_CorFundo(){
        return cor;
    }


    /**
     * Metodo que indica se pode haver sobreposicao de cores
     * @return true caso haja ou false caso contrario
     */
    public boolean obter_SobrePosicaoCor(){
        return sobrePosicaoCor;
    }


    /**
     * Metodo que permite adicionar o alinhamento
     * @param alinhamentoHorizontal o alinhamento horizontal na celula
     * @param alinhamentoVertical o alinhamento vertical na celula
     */
    public void adicionar_Alinhamento(int alinhamentoHorizontal, int alinhamentoVertical){
        this.alinhamentoHorizontal = alinhamentoHorizontal;
        this.alinhamentoVertical = alinhamentoVertical;
    }


    /**
     * Metodo que permite adicionar um valor para o alinhamento horizontal. <br><br>
     * @param alinhamentoHorizontal o alinhamento horizontal na celula
     */
    public void adicionar_AlinhamentoHorizontal(int alinhamentoHorizontal){
        this.alinhamentoHorizontal = alinhamentoHorizontal;
    }



    /**
     * Metodo que permite obter o alinhamento horizontal
     * @return o valor do alinhamento horizontal
     */
    public int obter_AlinhamentoHorizontal(){
        return alinhamentoHorizontal;
    }




    /**
     * Metodo que permite adicionar um valor para o alinhamento vertical. <br><br>
     * @param alinhamentoVertical o alinhamento vertical na celula
     */
    public void adicionar_AlinhamentoVertical(int alinhamentoVertical){
        this.alinhamentoVertical = alinhamentoVertical;
    }



    /**
     * Metodo que permite obter o alinhamento vertical
     * @return o valor do alinhamento vertical
     */
    public int obter_AlinhamentoVertical(){
        return alinhamentoVertical;
    }



    /**
     * Metodo que permite adicionar um valor para a rotacao. <br><br>
     * @param rotacao o valor a fixar no <i>Sentido anti-horario</i>
     */
    public void adicionar_Rotacao(int rotacao){
        this.rotacao = rotacao;
    }



    /**
     * Metodo que permite obter o valor da rotacao
     * @return o valor da rotacao
     */
    public int obter_Rotacao(){
        return rotacao;
    }



    /**
     * Metodo que permite adicionar um valor para o row span<br><br>
     * @param rowspan o valor a fixar
     */
    public void adicionar_RowSpan(int rowspan){
        this.rowspan = rowspan;
    }



    /**
     * Metodo que permite obter o valor do row span
     * @return o valor do row span
     */
    public int obter_RowSpan(){
        return rowspan;
    }



    /**
     * Metodo que permite adicionar um valor para o col span
     * @param colSpan o valor a fixar
     */
    public void adicionar_ColSpan(int colSpan){
        this.colSpan = colSpan;
    }


    /**
     * Metodo que permite obter o valor do col span
     * @return o valor do col span
     */
    public int obter_ColSpan(){
        return colSpan;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    /**
     * Metodo que permite adicionar um valor para a altura do topo (altura entre o conteudo e a borda de topo)
     * @param alturaTopo o valor a fixar
     */
    public void adicionar_AlturaTopo(float alturaTopo){
        this.alinhamentoTopo = alturaTopo;
    }


    /**
     * Metodo que permite adicionar um valor para as dimensões da moldura<br> Todas as dimensões são relativas ao conteudo e as bordas
     * @param alinhamentoTopo
     * @param alinhamentoInferior
     */
    public void adicionar_Moldura(float alinhamentoTopo, float alinhamentoInferior, float alinhamentoEsquerda, float alinhamentoDireita){
        this.alinhamentoTopo = alinhamentoTopo;
        this.alinhamentoInferior = alinhamentoInferior;
        this.alinhamentoEsquerda = alinhamentoEsquerda;
        this.alinhamentoDireita = alinhamentoDireita;
    }

    /**
     * Metodo que permite obter a altura do topo
     * @return a altura entre o conteudo e a borda de topo
     */
    public float obter_AlturaTopo() {
        return alinhamentoTopo;
    }


    public float obter_AlturaInferior() {
        return alinhamentoInferior;
    }



    /**
     * Metodo que permite adicionar um valor para  o alinhamento à esquerda (espaco entre o conteudo e a borda esquerda)
     * @param alturaTopo o valor a fixar
     */
    public void adicionar_AlinhamentoEsquerda(float alturaTopo){
        this.alinhamentoEsquerda = alturaTopo;
    }

    /**
     * Metodo que permite obter o alinhamento à esquerda
     * @return o alinhamento
     */
    public float obter_AlinhamentoEsquerda() {
        return alinhamentoEsquerda;
    }


    /**
     * Metodo que permite obter o alinhamento à direita
     * @return o alinhamento
     */
    public float obter_AlinhamentoDireita() {
        return alinhamentoDireita;
    }


}
