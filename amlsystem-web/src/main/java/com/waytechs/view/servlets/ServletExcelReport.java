/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.waytechs.view.servlets;

import com.waytechs.view.utils.JsfUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.transform.jexcel.JexcelTransformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;

/**
 *
 * @author mllerena
 */
@WebServlet(name = "ServletExcelReport", urlPatterns = {"/excelreport"})
public class ServletExcelReport extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Object list = request.getSession().getAttribute("list");
        
        String fileNameResult = (String) request.getSession().getAttribute("fileNameResult");
        
        String fileNameTemplate = (String) request.getSession().getAttribute("fileNameTemplate");
        
        System.out.println("list: "+list);
        System.out.println("fileNameResult: "+fileNameResult);
        System.out.println("fileNameTemplate: "+fileNameTemplate);
        
        if( list == null) return;
        if( fileNameTemplate == null) return;
        
        
        if( fileNameResult == null) fileNameResult = "reporte_excel";
        
        
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename="+ fileNameResult);
        
        try(InputStream is =  FileHelper.class.getClassLoader().getResourceAsStream(fileNameTemplate)) {
            
            try (OutputStream os = response.getOutputStream()) {    
                
                Context context = new Context();
                context.putVar("listVar", list);
                JxlsHelper hel = JxlsHelper.getInstance().processTemplate(is, os, context);
                
                os.flush();
                os.close();
                
            }
            
            
        }
        
        request.getSession().setAttribute("list", null);
        request.getSession().setAttribute("fileNameResult", null);
        request.getSession().setAttribute("fileNameTemplate", null);
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
