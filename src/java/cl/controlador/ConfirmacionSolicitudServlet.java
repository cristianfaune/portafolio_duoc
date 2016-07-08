package cl.controlador;

import static cl.controlador.RegistrarSolicitudServlet.listaDtSol;
import cl.dominio.DetalleSolicitud;
import cl.dominio.Marca;
import cl.dominio.Producto;
import cl.dominio.Solicitud;
import cl.dominio.Usuario;
import cl.dto.DetalleSolicitudPrUsCaDTO;
import cl.dto.ProductoMarcaDTO;
import cl.dto.UsuarioPerfilCarreraDTO;
import cl.servicio.Servicio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author cristian
 */
@WebServlet(name = "ConfirmacionSolicitudServlet", urlPatterns = {"/ConfirmacionSolicitudServlet"})
public class ConfirmacionSolicitudServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<ProductoMarcaDTO> lstPr = new ArrayList<>();

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                request.setAttribute("listaProductos", cl.controlador.RegistrarSolicitudServlet.listaProductosSolicitud);
                request.setAttribute("listaDetalle", cl.controlador.RegistrarSolicitudServlet.listaDtSol);
                request.getRequestDispatcher("ConfirmacionSolicitud.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error en la conexión a bd", e);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        UsuarioPerfilCarreraDTO usuarioSlc = (UsuarioPerfilCarreraDTO) session.getAttribute("usuarioSolicitud");

        String diasPrestamo = request.getParameter("diasPrestamo");
        String especial = request.getParameter("especial");
        Solicitud solicitud = new Solicitud();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        String especialPdf = "";
        ByteArrayOutputStream doc = new ByteArrayOutputStream();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                //int idSolicitud = servicio.idSolicitudDisponible();
                solicitud.setFechaSolicitud(new Timestamp(System.currentTimeMillis()));

                if (Integer.parseInt(especial) == 1) {
                    solicitud.setActiva((byte) 0);
                } else {
                    solicitud.setActiva((byte) 1);
                }

                solicitud.setSolicitudEspecial((byte) Integer.parseInt(especial));

                if (Integer.parseInt(especial) == 1) {
                    solicitud.setDiasPrestamo(Integer.parseInt(diasPrestamo));
                } else {
                    solicitud.setDiasPrestamo(1);
                }

                servicio.registrarSolicitud(solicitud);

                for (DetalleSolicitud detSol : cl.controlador.RegistrarSolicitudServlet.listaDtSol) {
                    DetalleSolicitud detalleS = new DetalleSolicitud();

                    detalleS.setIdSolicitud(detSol.getIdSolicitud());
                    detalleS.setIdProducto(detSol.getIdProducto());
                    detalleS.setRut(detSol.getRut());
                    detalleS.setCantidad(detSol.getCantidad());

                    servicio.registrarDetalleSolicitud(detalleS);
                }

                String nombre = usuarioSlc.getUsuario().getNombres() + " " + usuarioSlc.getUsuario().getApellidos();

                int idUltimaSolicitud = servicio.idUltimaSolicitud();
                ArrayList<DetalleSolicitudPrUsCaDTO> lista = servicio.totalSolicitudId(idUltimaSolicitud);

                //documento PDF
                Document document = new Document();

                try {

                    PdfWriter.getInstance(document, doc);

                    document.open();

                    PdfPTable table;
                    table = new PdfPTable(4);
                    table.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.setWidthPercentage(80);
                    PdfPCell cell;

                    for (int i = 0; i <= 0; i++) {
                        DetalleSolicitudPrUsCaDTO var = lista.get(i);

                        cell = new PdfPCell(new Phrase("Nombre:", FontFactory.getFont("arial", 8, Font.BOLDITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(var.getUsuario().getNombres() + " " + var.getUsuario().getApellidos(), FontFactory.getFont("arial", 8, Font.ITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Dirección:", FontFactory.getFont("arial", 8, Font.BOLDITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(var.getUsuario().getDireccion(), FontFactory.getFont("arial", 8, Font.ITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Rut:", FontFactory.getFont("arial", 8, Font.BOLDITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(var.getUsuario().getRut(), FontFactory.getFont("arial", 8, Font.ITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Teléfono:", FontFactory.getFont("arial", 8, Font.BOLDITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(var.getUsuario().getTelefono(), FontFactory.getFont("arial", 8, Font.ITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Carrera:", FontFactory.getFont("arial", 8, Font.BOLDITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(var.getCarrera().getDescripcion(), FontFactory.getFont("arial", 8, Font.ITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Email:", FontFactory.getFont("arial", 8, Font.BOLDITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(var.getUsuario().getEmail(), FontFactory.getFont("arial", 8, Font.ITALIC)));
                        cell.setBorder(0);
                        table.addCell(cell);

                    }

                    PdfPTable table2 = new PdfPTable(3);
                    table2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table2.setWidthPercentage(80);
                    PdfPCell cell2;

                    cell2 = new PdfPCell(new Phrase("ID Solicitud:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase("Fecha Solicitud:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase("Solicitud especial:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    for (int i = 0; i <= 0; i++) {
                        DetalleSolicitudPrUsCaDTO var = lista.get(i);

                        cell2 = new PdfPCell(new Phrase(String.valueOf(idUltimaSolicitud), FontFactory.getFont("arial", 10, Font.ITALIC)));
                        cell2.setBorder(0);
                        table2.addCell(cell2);

                        cell2 = new PdfPCell(new Phrase(sdf2.format(var.getSolicitud().getFechaSolicitud()), FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                        cell2.setBorder(0);
                        table2.addCell(cell2);

                        if (var.getSolicitud().getSolicitudEspecial() == 1) {
                            especialPdf = "Si";
                        } else {
                            especialPdf = "No";
                        }

                        cell2 = new PdfPCell(new Phrase(especialPdf, FontFactory.getFont("arial", 10, Font.ITALIC)));
                        cell2.setBorder(0);
                        table2.addCell(cell2);

                    }

                    PdfPTable table3 = new PdfPTable(5);
                    table3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table3.setWidthPercentage(100);
                    PdfPCell cell3;

                    cell3 = new PdfPCell(new Phrase("ID Producto:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell3.setBorder(0);
                    table3.addCell(cell3);

                    cell3 = new PdfPCell(new Phrase("Nombre:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell3.setBorder(0);
                    table3.addCell(cell3);

                    cell3 = new PdfPCell(new Phrase("Marca:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell3.setBorder(0);
                    table3.addCell(cell3);

                    cell3 = new PdfPCell(new Phrase("Modelo:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell3.setBorder(0);
                    table3.addCell(cell3);

                    cell3 = new PdfPCell(new Phrase("Cantidad:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell3.setBorder(0);
                    table3.addCell(cell3);

                    for (DetalleSolicitudPrUsCaDTO var : lista) {

                        cell3 = new PdfPCell(new Phrase(String.valueOf(var.getProducto().getIdProducto())));
                        cell3.setBorder(0);
                        table3.addCell(cell3);

                        cell3 = new PdfPCell(new Phrase(var.getProducto().getNombre()));
                        cell3.setBorder(0);
                        table3.addCell(cell3);

                        cell3 = new PdfPCell(new Phrase(var.getMarca().getDescripcion()));
                        cell3.setBorder(0);
                        table3.addCell(cell3);

                        cell3 = new PdfPCell(new Phrase(var.getProducto().getModelo()));
                        cell3.setBorder(0);
                        table3.addCell(cell3);

                        cell3 = new PdfPCell(new Phrase(String.valueOf(var.getDetalleSolicitud().getCantidad())));
                        cell3.setBorder(0);
                        table3.addCell(cell3);
                    }

                    //organización documento PDF final
                    String filename = "C:\\Users\\cristian\\Documents\\NetBeans Projects\\portafolio_duoc\\logoDuoc.png";
                    Image image = Image.getInstance(filename);
                    image = Image.getInstance(filename);
                    image.scalePercent(200f);
                    image.setAbsolutePosition(400, (float) (PageSize.A4.getHeight() - 50.0));

                    image.scaleToFit(100f, 200f);

                    document.add(image);
                    document.add(new Paragraph("Detalle Solicitud: " + String.valueOf(idUltimaSolicitud), FontFactory.getFont("arial", 20, Font.BOLD)));
                    document.add(new Paragraph("Fecha emisión documento: " + sdf.format(time), FontFactory.getFont("arial", 9, Font.ITALIC)));
                    document.add(new Paragraph("______________________________________________________________________________"));
                    document.add(new Paragraph("Datos Solicitud: ", FontFactory.getFont("arial", 16, Font.BOLD)));
                    document.add(new Paragraph("  "));

                    document.add(table2);

                    document.add(new Paragraph("  "));
                    document.add(new Paragraph("  "));
                    document.add(new Paragraph("______________________________________________________________________________"));
                    document.add(new Paragraph("Datos Usuario: ", FontFactory.getFont("arial", 16, Font.BOLD)));
                    document.add(new Paragraph(" "));

                    document.add(table);

                    document.add(new Paragraph("  "));
                    document.add(new Paragraph("  "));
                    document.add(new Paragraph("______________________________________________________________________________"));
                    document.add(new Paragraph("Detalle Productos: ", FontFactory.getFont("arial", 16, Font.BOLD)));
                    document.add(new Paragraph("  "));

                    document.add(table3);

                    document.close();

                } catch (DocumentException e) {
                    e.printStackTrace();
                }

                cl.controlador.RegistrarSolicitudServlet.listaDtSol.clear();
                cl.controlador.RegistrarSolicitudServlet.listaProductosSolicitud.clear();

                request.setAttribute("ultimoIdSolicitud", idUltimaSolicitud);
                request.getRequestDispatcher("RegistroSolicitudExitosa.jsp").forward(request, response);
                servicio.enviarEmailSolicitud(nombre, idUltimaSolicitud, usuarioSlc.getUsuario().getEmail(), doc, especial);

            } catch (SQLException e) {
                throw new RuntimeException("Error en la conexion a bd", e);
            }

        }

    }

}
