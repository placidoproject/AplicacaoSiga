package com.example.gavin.aplicacaosiga.View;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.gavin.aplicacaosiga.BO.TarefaBO;
import com.example.gavin.aplicacaosiga.R;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.exemplo.gavin.DAO.DaoTarefa;
import com.exemplo.gavin.Model.ModelTarefa;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Created by Gavin on 10/06/2015.
 */
public class Relatorios extends Activity{

    private static Relatorios relatorios = null;

    TarefaBO bo = new TarefaBO(this);
    public void gerarPDFRelatorioTarefa(){

        Document pdf = new Document();

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SIGA";

            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();

            Log.d("PDFCreator", "PDF Path: " + path);


            File file = new File(dir, "relatorio_tarefa.pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(pdf, fOut);

            //open the document
            pdf.open();


            Paragraph p1 = new Paragraph("Relatório de Tarefas Agendadas pelo apicultor");

            Font paraFont= new Font(Font.COURIER);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setFont(paraFont);

            //add paragraph to document
            pdf.add(p1);
            List<ModelTarefa> a = bo.listTarefa();

            for (ModelTarefa tarefa : a) {
                Paragraph p2 = new Paragraph(tarefa.getTAREFAS()+"-"+tarefa.getDATATAREFA()+"-Atividade:"+checkBool(tarefa.getATIVIDADE()));
                Font paraFont2 = new Font(Font.COURIER, 14.0f, Color.GREEN);
                p2.setAlignment(Paragraph.ALIGN_CENTER);
                p2.setFont(paraFont);

                pdf.add(p2);
            }
            /*
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ic_launcher);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
            Image myImg = Image.getInstance(stream.toByteArray());
            myImg.setAlignment(Image.MIDDLE);

            //add image to document
            pdf.add(myImg);*/

            //set footer
            Phrase footerText = new Phrase("This is an example of a footer");
            HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
            pdf.setFooter(pdfFooter);



        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally
        {
            pdf.close();
        }

    }


    public String checkBool (boolean bool){
        if (bool)
            return "Sim";
        return "Não";
    }

    public static Relatorios checkRelatorios() {
        if(relatorios==null)
            relatorios = new Relatorios();
        return relatorios;
    }
/*
    public void AbrirRelatorio(String name){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/SIGA/" + name);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file), "application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e){}
    }*/
}

