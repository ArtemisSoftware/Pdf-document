package com.titan.pdfdocumentlibrary.elements;

import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.titan.pdfdocumentlibrary.util.PdfConstants;

import java.io.IOException;

public class FontConfiguration {


    private BaseFont normalFont, boldFont;

    public FontConfiguration(){
        normalFont = getBaseFont(PdfConstants.BASE_FONT_FAMILY);
        boldFont = getBaseFont(PdfConstants.BASE_FONT_FAMILY);
    }

    public FontConfiguration(FontFamily fontFamily){
        normalFont = getBaseFont(fontFamily);
        boldFont = getBaseFont(PdfConstants.BASE_FONT_FAMILY);
    }

    public FontConfiguration(FontFamily fontFamily, FontFamily fontBoldFamily){
        normalFont = getBaseFont(fontFamily);
        boldFont = getBaseFont(fontBoldFamily);
    }

    public FontConfiguration(String fonteNormal){
        generateFont(fonteNormal, null);
    }

    public FontConfiguration(String fonteNormal, String fonteBold){
        generateFont(fonteNormal, fonteBold);
    }


    /**
     * Method to generate fonts
     * @param normalFont the name of the normal font
     * @param boldFont the name of the bold font
     */
    private void generateFont(String normalFont, String boldFont){

        try {
            this.normalFont = BaseFont.createFont(normalFont, /*"UTF-8"*/BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
        catch (DocumentException | IOException e) {

            Log.e("pdfdocumentlibrary", "Exception font:" + e.getMessage());
            this.normalFont = getBaseFont(PdfConstants.BASE_FONT_FAMILY);
        }

        try {
            this.boldFont = BaseFont.createFont(boldFont, /*"UTF-8"*/BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }
        catch (DocumentException | IOException | NullPointerException e) {

            Log.e("pdfdocumentlibrary", "Exception font:" + e.getMessage());
            this.boldFont = getBaseFont(PdfConstants.BASE_FONT_FAMILY);
        }
    }



    /**
     * Method to get the base font
     * @param fontFamily the font family
     * @return a basefont
     */
    private BaseFont getBaseFont(FontFamily fontFamily){

        if(fontFamily == null){
            Font font = new Font(PdfConstants.BASE_FONT_FAMILY);
            return font.getCalculatedBaseFont(true);
        }
        else{
            Font font = new Font(fontFamily);
            return font.getCalculatedBaseFont(true);
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
        return getFont(fontSize, boldStyle, fontColor, false);
    }


    /**
     * Method that returns a font
     * @param fontSize the size of the font
     * @param boldStyle true if the font should be bold
     * @param fontColor the color of the font
     * @param underlineStyle true if the font should be underlined
     * @return a font
     */
    public Font getFont(float fontSize, boolean boldStyle, BaseColor fontColor, boolean underlineStyle){

        BaseFont baseFont = normalFont;
        BaseColor color = BaseColor.BLACK;
        int style = Font.NORMAL;

        if(boldStyle == true && underlineStyle == true){
            baseFont = boldFont;
            style = Font.BOLD | Font.UNDERLINE;
        }
        else if(underlineStyle == true){
            baseFont = normalFont;
            style = Font.UNDERLINE;
        }
        else if(boldStyle == true){
            baseFont = boldFont;
            style = Font.BOLD;
        }

        if(fontColor != null){
            color = fontColor;
        }

        return new Font(baseFont, fontSize, style , color);
    }




}
