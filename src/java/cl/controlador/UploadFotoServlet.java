package cl.controlador;

import cl.dominio.Categoria;
import cl.dominio.Marca;
import cl.servicio.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author cristian
 */
@WebServlet(name = "UploadFotoServlet", urlPatterns = {"/UploadFotoServlet"})
public class UploadFotoServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "/imgProductos";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        File uploadedFile = null;
        FileItem item = null;

        if (!isMultipart) {
            return;
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored
        String uploadFolder = getServletContext().getRealPath("")
                + File.separator + DATA_DIRECTORY;

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadFolder + File.separator + fileName;
                    uploadedFile = new File(filePath);
                    System.out.println(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);
                }
            }

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                ArrayList<Categoria> lstCategorias = servicio.listarCategorias();
                ArrayList<Marca> lstMarcas = servicio.listarMarcas();

                request.setAttribute("lstMarcas", lstMarcas);
                request.setAttribute("lstCategorias", lstCategorias);
                // displays done.jsp page after upload finished
                request.setAttribute("nombre", item.getName());
                request.getRequestDispatcher("/RegistroProducto.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error en la conexion bd", e);
            }

        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

}
