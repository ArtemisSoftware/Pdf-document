package com.titan.pdfdocumentlibrary.elements;

import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.titan.pdfdocumentlibrary.util.PdfConstants;

import java.io.IOException;

public class FontConfiguration {


    private BaseFont normalFont, boldFont;


    public FontConfiguration(){
        generateFont(null, null);
    }

    public FontConfiguration(String fonteNormal){
        generateFont(fonteNormal, null);
    }

    public FontConfiguration(String fonteNormal, String fonteBold){
        generateFont(fonteNormal, fonteBold);
    }




    /**
     * Method to generate fonts
     * @param normalFont nome da fonte normal
     * @param boldFont nome da fonte bold
     */
    private void generateFont(String normalFont, String boldFont){

        Font font = new Font(PdfConstants.BASE_FONT_FAMILY);

        try {
            this.normalFont = BaseFont.createFont(normalFont, /*"UTF-8"*/BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
        catch (DocumentException | IOException e) {

            Log.e("pdfdocumentlibrary", "Exception:" + e);
            //LogApp_v3.obterInstancia(MetodosApp.obterNomeClasse(this.getClass()), LogIF.ID_LOG_GERAL).adicionarExcecaoErro("Excecao ao gerar as fontes para o pdf", e);
            this.normalFont = font.getBaseFont();
        }

        try {
            this.boldFont = BaseFont.createFont(boldFont, /*"UTF-8"*/BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
        catch (DocumentException | IOException e) {

            Log.e("pdfdocumentlibrary", "Exception:" + e);
            this.boldFont = font.getBaseFont();
        }

    }


    /**
     * Method that returns a font
     * @param fontSize the size of the font
     * @return a font
     */
    public Font getFont(float fontSize){
        return getFont(fontSize, false, null);
    }


    /**
     * Method that returns a font
     * @param fontSize the size of the font
     * @param boldStyle true if the font should be bold
     * @return a font
     */
    public Font getFont(float fontSize, boolean boldStyle){
        return getFont(fontSize, boldStyle, null);
    }


    /**
     * Method that returns a font
     * @param fontSize the size of the font
     * @param boldStyle true if the font should be bold
     * @param fontColor the color of the font
     * @return a font
     */
    public Font getFont(float fontSize, boolean boldStyle, BaseColor fontColor){

        BaseFont baseFont = normalFont;
        boolean boldstyle = false;
        BaseColor color = BaseColor.BLACK;


        if(boldStyle == true){
            baseFont = boldFont;
        }

        if(fontColor != null){
            color = fontColor;
        }

        return new Font(baseFont, fontSize, Font.NORMAL, color);
    }



}
