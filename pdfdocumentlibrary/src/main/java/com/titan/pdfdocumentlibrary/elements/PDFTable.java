package com.titan.pdfdocumentlibrary.elements;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.titan.pdfdocumentlibrary.utils.Constants;
import com.titan.pdfdocumentlibrary.utils.PdfHelper;

public class PDFTable {

    private PdfPTable tabela;


    private final int NUMERO_CELULAS;

    private int numeroCelulasInseridas, numeroLinhas, contadorCelulas;


    public PDFTable(){

        this.NUMERO_CELULAS = 1;

        this.numeroLinhas = 0;
        this.numeroCelulasInseridas = 0;
        this.contadorCelulas = 0;

        tabela = new PdfPTable(NUMERO_CELULAS);
        tabela.setWidthPercentage(100);
    }


    public PDFTable(int numeroCelulas){

        this.NUMERO_CELULAS = numeroCelulas;

        this.numeroLinhas = 0;
        this.numeroCelulasInseridas = 0;
        this.contadorCelulas = 0;

        tabela = new PdfPTable(NUMERO_CELULAS);
        tabela.setWidthPercentage(100);


        float larguras [] = new float [NUMERO_CELULAS];

        for(int index = 0; index < NUMERO_CELULAS; ++index){
            larguras[index] = 100f / NUMERO_CELULAS;
        }

        try {
            tabela.setWidths(larguras);
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public PDFTable(float larguras [], double razao){

        this.NUMERO_CELULAS = larguras.length;

        this.numeroLinhas = 0;
        this.numeroCelulasInseridas = 0;
        this.contadorCelulas = 0;


        tabela = new PdfPTable(NUMERO_CELULAS);
        tabela.setWidthPercentage(100);

        try {
            tabela.setWidths(PdfHelper.converterDimensoes(larguras, razao));
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public PDFTable(float dimensoes [], float larguraTotal, boolean quebrar){

        this.NUMERO_CELULAS = dimensoes.length;

        this.numeroLinhas = 0;
        this.numeroCelulasInseridas = 0;
        this.contadorCelulas = 0;

        tabela = new PdfPTable(NUMERO_CELULAS);

        tabela.setTotalWidth(larguraTotal);
        try {
            tabela.setWidths(dimensoes);
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        tabela.setLockedWidth(!quebrar);

    }



    //------------------------------
    //Local methods
    //------------------------------


    /**
     * Metodo que permite obter a tabela pdf
     * @return a tabela pdf
     *//*
    public PdfPTable obterTabela(){
        return tabela;
    }
*/

    /**
     * Metodo que permite incrementar o numero de linhas da tabela
     *//*
    private void incrementarNumeroLinhas(){
        ++numeroLinhas;
    }*/

    /**
     * Metodo que incrementa o numero de celulas inseridas
     *//*
    private void incrementarCelulasInseridas(){
        incrementarCelulasInseridas(1);
    }*/

    /**
     * Metodo que incrementa o numero de celulas inseridas
     * @param valor o numero de celulas inseridas
     *//*
    private void incrementarCelulasInseridas(int valor){

        contadorCelulas += valor;
        numeroCelulasInseridas += valor;

        if(numeroCelulasInseridas == NUMERO_CELULAS){

            numeroCelulasInseridas = 0;
            incrementarNumeroLinhas();
        }
    }
*/

    /**
     * Metodo que permite ou impede a tabela de quebrar
     * @param quebrar true caso seja para quebrar ou false caso contrário
     *//*
    public void quebrarTabela(boolean quebrar){
        tabela.setKeepTogether(!quebrar);
    }
*/





    //--------------------------------------
    //Metodos locais - celula
    //--------------------------------------

    /**
     * Metodo que permite adicionar celulas vazias
     * @param rowSpan o rowSpan da celula
     *//*
    public void adicionarCelulaVazia(int rowSpan){

        if(rowSpan > 0){

            DimensoesCelula formato = new DimensoesCelula();
            formato.adicionar_RowSpan(rowSpan);
            adicionarCelula(PdfIF.SEM_TEXTO, formato);
        }
    }
*/

    /**
     * Metodo que permite inserir dados numa celula
     * @param conteudo conteudo a inserir
     *//*
    public void adicionarCelula(PdfPCell conteudo){

        tabela.addCell(conteudo);
        incrementarCelulasInseridas();
    }
*/

    /**
     * Metodo que permite inserir dados numa celula
     * @param conteudo conteudo a inserir
     *//*
    public void adicionarCelula(String conteudo){

        tabela.addCell(conteudo);
        incrementarCelulasInseridas();
    }
*/
    /**
     * Metodo que permite inserir dados numa celula
     * @param conteudo conteudo a inserir
     *//*
    public void adicionarCelula(Paragraph conteudo){

        tabela.addCell(conteudo);
        incrementarCelulasInseridas();
    }
*/

    /**
     * Metodo que permite inserir dados numa celula
     * @param conteudo conteudo a inserir
     *//*
    public void adicionarCelula(Phrase conteudo){

        tabela.addCell(conteudo);
        incrementarCelulasInseridas();
    }*/


    /**
     * Metodo que permite inserir dados numa celula
     * @param tabela conteudo a inserir
     *//*
    public void adicionarCelula(PdfPTable tabela){

        this.tabela.addCell(tabela);
        incrementarCelulasInseridas();
    }
*/
    /**
     * Metodo que permite inserir dados numa celula
     * @param imagem conteudo a inserir
     *//*
    public void adicionarCelula(Image imagem){

        this.tabela.addCell(imagem);
        incrementarCelulasInseridas();
    }
*/
    /**
     * Metodo que permite inserir dados numa celula
     * @param tabela conteudo a inserir
     *//*
    public void adicionarCelula(TabelaPdf tabela){
        adicionarCelula(tabela.obterTabela());
    }
*/

    /**
     * Metodo que permite inserir dados numa celula
     * @param frase a frase a inserir na celula
     * @param formato a formatacao a ser aplicada na celula
     * @return uma celula
     *//*
    public void adicionarCelula(String frase, DimensoesCelula formato){

        PdfPCell celula = new PdfPCell(new Phrase (frase));
        adicionarCelula(formatarCelula(celula, formato));
    }*/


    /**
     * Metodo que permite inserir dados numa celula
     * @param frase a frase a inserir na celula
     * @param formato a formatacao a ser aplicada na celula
     * @return uma celula
     *//*
    public void adicionarCelula(Phrase frase, DimensoesCelula formato){

        PdfPCell celula = new PdfPCell(frase);
        adicionarCelula(formatarCelula(celula, formato));
    }
*/

    /**
     * Metodo que permite inserir dados numa celula
     * @param imagem conteudo a inserir
     *//*
    public void adicionarCelula(Image imagem, DimensoesCelula formato){

        PdfPCell celula = new PdfPCell(imagem);
        adicionarCelula(formatarCelula(celula, formato));
    }


    /**
     * Metodo que permite inserir dados numa celula
     * @param tabela conteudo a inserir
     *//*
    public void adicionarCelula(TabelaPdf tabela, DimensoesCelula formato){

        PdfPCell celula = new PdfPCell(tabela.obterTabela());
        adicionarCelula(formatarCelula(celula, formato));
    }
*/

    /**
     * Metodo que aplica uma formatacao à celula<br>
     * <b>Campos formatados</b>:
     * <br>- <i>Alinhamento horizontal</i>
     * <br>- <i>Alinhamento vertical</i>
     * <br>- <i>cor de fundo</i>
     * <br>- <i>Padding</i>
     * <br>- <i>Altura</i>
     * <br>- <i>RowSpan</i>
     * <br>- <i>ColSpan</i>
     * @param celula celula formatar
     * @param formato a formatacao a aplicar
     * @return uma celula formatada
     *//*
    private PdfPCell formatarCelula(PdfPCell celula, DimensoesCelula formato){


        if(formato.obter_AlinhamentoVertical() != AppIF.SEM_REGISTO){
            celula.setVerticalAlignment(formato.obter_AlinhamentoVertical());
        }

        if(formato.obter_AlinhamentoHorizontal() != AppIF.SEM_REGISTO){
            celula.setHorizontalAlignment(formato.obter_AlinhamentoHorizontal());
        }

        if(formato.obter_Rotacao() != AppIF.SEM_REGISTO){
            celula.setRotation(formato.obter_Rotacao());
        }


        if(formato.obter_SobrePosicaoCor() == true){
            celula.setBackgroundColor(formato.obter_CorFundo());
        }
        else{

            if(celula.getBackgroundColor() == BaseColor.WHITE){
                celula.setBackgroundColor(formato.obter_CorFundo());
            }
        }

        if(formato.obter_RowSpan() != AppIF.SEM_REGISTO){
            celula.setRowspan(formato.obter_RowSpan());
        }

        if(formato.obter_ColSpan() != AppIF.SEM_REGISTO){
            celula.setColspan(formato.obter_ColSpan());
        }

        if(formato.obter_Altura() != AppIF.SEM_REGISTO ){
            celula.setFixedHeight(formato.obter_Altura());
        }

        if(formato.obter_Borda() != AppIF.SEM_REGISTO ){
            celula.setBorder(formato.obter_Borda());
        }

        if(formato.obter_Evento() != null){
            celula.setCellEvent(formato.obter_Evento());
        }



        if(formato.obter_AlturaTopo()  != AppIF.SEM_REGISTO){
            celula.setPaddingTop(formato.obter_AlturaTopo());
        }

        if(formato.obter_AlturaInferior()  != AppIF.SEM_REGISTO){
            celula.setPaddingBottom(formato.obter_AlturaInferior());
        }


        if(formato.obter_AlinhamentoEsquerda()  != AppIF.SEM_REGISTO){
            celula.setPaddingLeft(formato.obter_AlinhamentoEsquerda());
        }

        if(formato.obter_AlinhamentoDireita()  != AppIF.SEM_REGISTO){
            celula.setPaddingRight(formato.obter_AlinhamentoDireita());
        }

        return celula;
    }

*/
    //-----------------------------
    //Metodos locais - linha
    //-----------------------------


    /**
     * Metodo que permite adicionar uma celula à linha.<br>
     * A celula ocupará o tamanho de uma linha
     * @param frase a frase a apresentar na celula
     * @param formato a formatacao a ser aplicada na celula
     *//*
    public void adicionarLinha(Phrase frase, DimensoesCelula formato){

        formato.adicionar_ColSpan(NUMERO_CELULAS);
        adicionarCelula(frase, formato);
    }
*/



    /**
     * Metodo que permite adicionar varias celula à linha.<br>
     * Caso o numero de frases seja inferior ao numero de celulas da tabela a ultima celula fará um colspan
     * @param frases as frases a apresentar na celula
     * @param formato a formatacao a ser aplicada nas celulas
     * @throws Pdf_Exception
     *//*
    public void adicionarLinha(Phrase frase [], DimensoesCelula formato) throws Pdf_Exception{
        adicionarLinha(frase, formato, 0);
    }
*/




    /**
     * Metodo que permite adicionar varias celula à linha.<br>
     * Caso o numero de frases seja inferior ao numero de celulas da tabela a ultima celula fará um colspan
     * @param frases as frases a apresentar na celula
     * @param formato a formatacao a ser aplicada nas celulas
     * @throws Pdf_Exception
     *//*
    public void adicionarLinha(Phrase frases [], DimensoesCelula formato, int posicao) throws Pdf_Exception{

        if(frases.length > NUMERO_CELULAS){
            throw new Pdf_Exception("O numero de frases por celula a inserir é superior ao numero de celulas da tabela");
        }

        try {
            DimensoesCelula formato_clone = (DimensoesCelula) formato.clone();

            for(int index = 0; index < frases.length; ++index){

                if((index +1) == frases.length){ //a ultima celula vai ter o colspan alterado para completar as dimensoes da tabela

                    int colSpan = NUMERO_CELULAS - (frases.length - 1) - posicao;
                    formato_clone.adicionar_ColSpan(colSpan);
                }

                adicionarCelula(frases [index], formato_clone);
                incrementarCelulasInseridas(formato_clone.obter_ColSpan());
            }
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
*/
    /**
     * Metodo que permite adicionar varias celula à linha.<br>
     * Caso o numero de frases seja inferior ao numero de celulas da tabela a ultima celula fará um colspan
     * @param frases as frases a apresentar na celula
     * @param formatos a formatacao a ser aplicada nas celulas
     * @throws Pdf_Exception
     *//*
    public void adicionarLinha(Phrase frases [], DimensoesCelula formatos [], int posicao) throws Pdf_Exception{

        if(frases.length > NUMERO_CELULAS){
            throw new Pdf_Exception("O numero de frases por celula a inserir é superior ao numero de celulas da tabela");
        }

        if(formatos.length > frases.length || formatos.length < frases.length){
            throw new Pdf_Exception("O numero de formatos por celula não corresponde ao numero de frases");
        }


        try {

            for(int index = 0; index < frases.length; ++index){

                DimensoesCelula formato_clone = (DimensoesCelula) formatos[index].clone();

                if((index +1) == frases.length){ //a ultima celula vai ter o colspan alterado para completar as dimensoes da tabela

                    int colSpan = NUMERO_CELULAS - (frases.length - 1) - posicao;
                    formato_clone.adicionar_ColSpan(colSpan);
                }

                adicionarCelula(frases [index], formato_clone);
                incrementarCelulasInseridas(formato_clone.obter_ColSpan());
            }
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
*/



    //------------------------------------
    //Metodos locais - pintar
    //------------------------------------


    /**
     * Metodo que pinta o rebordo de uma tabela
     * @param cor cor do rebordo
     *//*
    public void pintarRebordo(BaseColor cor) throws NullPointerException{
        pintarRebordo(cor, 0.0f);
    }
*/

    /**
     * Metodo que pinta o rebordo de uma tabela
     * @param cor cor do rebordo
     * @param grossuraBorda a grossura da linha do rebordo
     *//*
    public void pintarRebordo(BaseColor cor, float grossuraBorda){
	*/
		/*já estava comentado
		DimensoesCelula formato = new DimensoesCelula();
		formato.adicionar_CorFundo(cor);
		formato.adicionar_Borda((int) grossuraBorda);
		formatarTabela(formato);
		*/

/*
        for (int i = 0; i < tabela.getRows().size(); ++i) {

            int numeroColunas = tabela.getRow(i).getCells().length;

            for(int y = 0; y < numeroColunas; ++y){
                try{

                    tabela.getRow(i).getCells()[y].setBorderColor(cor);

                    if(grossuraBorda != 0){
                        tabela.getRow(i).getCells()[y].setBorderWidth(grossuraBorda);
                    }
                }
                catch(NullPointerException e){}
            }
        }

    }
*/

    /**
     * Metodo que pinta o fundo de uma tabela
     * @param cor cor do fundo
     *//*
    public void pintarFundo(BaseColor cor){

        DimensoesCelula formato = new DimensoesCelula();
        formato.adicionar_CorFundo(cor, false);
        formatarTabela(formato);
		/*
		/* já estava comentado
		for (int i = 0; i < tabela.getRows().size(); ++i) {

			int numeroColunas = tabela.getRow(i).getCells().length;

			for(int y = 0; y < numeroColunas; ++y){
				try{
					tabela.getRow(i).getCells()[y].setBackgroundColor(cor);
				}
				catch(NullPointerException e){}
			}
		}
		*/
   /* }*/


    /**
     * Metodo que remove o rebordo da tabela
     *//*
    public void removerRebordo(){

        DimensoesCelula formato = new DimensoesCelula();
        formato.adicionar_Borda(0);
        formatarTabela(formato);
    }
*/

    /**
     * Metodo que remove o rebordo da tabela
     *//*
    public void formatarTabela(DimensoesCelula formato){

        for (int indice = 0; indice < tabela.getRows().size(); ++indice) {

            for(int index = 0; index < tabela.getRow(indice).getCells().length; ++index){
                try{

                    formatarCelula(tabela.getRow(indice).getCells()[index], formato);
                }
                catch(NullPointerException e){}
            }
        }
    }
*/
}
