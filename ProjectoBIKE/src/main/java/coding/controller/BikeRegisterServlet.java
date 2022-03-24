package coding.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import coding.model.Bike;

/**
 * Servlet implementation class BikeRegisterServlet
 */
@WebServlet("/BikeRegisterServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
				 maxFileSize=1024*1024*10,      // 10MB
				 maxRequestSize=1024*1024*50)   // 50MB
public class BikeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BikeRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final String SAVE_DIR = "uploadedFiles";
    
    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        // constructs path of the directory to save uploaded file
        String savePath = "C:\\Users\\CPC - MOISES MADADE\\eclipse-workspace\\ProjectoBIKE\\src\\main\\webapp\\uploadedFiles\\";
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
         
        Part part = request.getPart("imagem");
        String fileName = extractFileName(part);
        // refines the fileName in case it is an absolute path
        fileName = new File(fileName).getName();
        part.write(savePath + fileName);
        
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        String imagem = fileName;
        String estado = request.getParameter("estado");
        String hora = request.getParameter("hora_entrada");
        
        Bike_C bikec = new Bike_C();
        bikec.recuperarBikes();
        Bike bike = new Bike(bikec.pegarUltimo()+1, marca, modelo, imagem, estado, hora);
		bikec.registar(bike.getId_bike(), bike.getMarca(), bike.getModelo(), bike.getImagem(), bike.getEstado(), bike.getHora_entrada());
        
        getServletContext().getRequestDispatcher("/bike.jsp").forward(
                request, response);
    }
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
