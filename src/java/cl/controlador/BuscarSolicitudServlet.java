package cl.controlador;

import cl.dominio.DetallePrestamo;
import cl.dominio.Item;
import cl.dominio.Prestamo;
import cl.dominio.Usuario;
import cl.dto.DetalleSolicitudPrUsCaDTO;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "BuscarSolicitudServlet", urlPatterns = {"/BuscarSolicitudServlet"})
public class BuscarSolicitudServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/portafolio")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idSolicitud = request.getParameter("idSolicitud");
        Map<String, String> mapMensajeSolicitud = new HashMap<>();
        ArrayList<DetalleSolicitudPrUsCaDTO> lista = new ArrayList<>();

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (usuarioS.getIdPerfil() == 100) {
            request.getRequestDispatcher("HomeJefeCarrera.jsp").forward(request, response);
        } else if (usuarioS.getIdPerfil() == 110) {
            request.getRequestDispatcher("HomeCoordinador.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                if (idSolicitud.isEmpty()) {
                    mapMensajeSolicitud.put("errorSolicitud", "**Debe ingresar un id de solicitud**");
                } else {
                    lista = servicio.buscarSolicitudId(Integer.parseInt(idSolicitud));
                    if (lista.size() == 0) {
                        mapMensajeSolicitud.put("errorSolicitud", "**No se registra solicitud con el ID " + idSolicitud + ""
                                + " o no se encuentra activa**");
                    }
                }

                if (mapMensajeSolicitud.isEmpty()) {
                    request.setAttribute("lstSolicitud", lista);
                    request.getRequestDispatcher("AdminPrestamos.jsp").forward(request, response);
                } else {
                    request.setAttribute("mensajeError", mapMensajeSolicitud);
                    request.getRequestDispatcher("AdminPrestamos.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                throw new RuntimeException("Error en la conexion bd", e);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        Usuario usuarioS = (Usuario) session.getAttribute("usuarioSesion");

        String idSolicitud = request.getParameter("varIdSol");
        ArrayList<Item> listaItems = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Prestamo> lstPrestamo = new ArrayList<>();
        ByteArrayOutputStream doc = new ByteArrayOutputStream();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        Prestamo queryPrestamo = new Prestamo();
        String nombreUsuario = null;
        String emailUsuario = null;
        String especialPdf = "";
        int idProducto;
        int cantidadItems;
        int cantidadDias = 0;

        if (usuarioS == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (usuarioS.getIdPerfil() == 100) {
            request.getRequestDispatcher("HomeJefeCarrera.jsp").forward(request, response);
        } else if (usuarioS.getIdPerfil() == 110) {
            request.getRequestDispatcher("HomeCoordinador.jsp").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Servicio servicio = new Servicio(con);

                ArrayList<DetalleSolicitudPrUsCaDTO> lista = servicio.buscarSolicitudId(Integer.parseInt(idSolicitud));

                for (DetalleSolicitudPrUsCaDTO var : lista) {

                    idProducto = var.getDetalleSolicitud().getIdProducto();
                    cantidadItems = var.getDetalleSolicitud().getCantidad();
                    cantidadDias = var.getSolicitud().getDiasPrestamo();
                    nombreUsuario = var.getUsuario().getNombres() + " " + var.getUsuario().getApellidos();
                    emailUsuario = var.getUsuario().getEmail();

                    listaItems = servicio.itemsDisponibles(idProducto, cantidadItems);

                    for (Item varItem : listaItems) {
                        items.add(varItem);
                    }
                }

                Prestamo prestamo = new Prestamo();

                prestamo.setActiva((byte) 1);
                prestamo.setFechaRetiro(new Timestamp(System.currentTimeMillis()));
                prestamo.setFechaEstimadaEntrega(new Timestamp(System.currentTimeMillis()));
                prestamo.setIdSolicitud(Integer.parseInt(idSolicitud));
                if (cantidadDias > 1) {
                    prestamo.setPrestamoEspecial((byte) 1);
                } else {
                    prestamo.setPrestamoEspecial((byte) 0);
                }

                servicio.registroPrestamo(prestamo, cantidadDias);

                int idPrestamo = servicio.idUltimoPrestamo();

                for (Item item : items) {
                    DetallePrestamo detallePrestamo = new DetallePrestamo();

                    detallePrestamo.setRut(usuarioS.getRut());
                    detallePrestamo.setIdPrestamo(idPrestamo);
                    detallePrestamo.setNroSerie(item.getNroSerie());

                    servicio.registroDetallePrestamo(detallePrestamo);

                    servicio.modificarEstadoPrestamoItem(item.getNroSerie(), (byte) 1);

                }

                servicio.ModificarEstadoSolicitud(Integer.parseInt(idSolicitud), (byte) 0);

                lstPrestamo = servicio.prestamoPorIdSolicitud(Integer.parseInt(idSolicitud));

                queryPrestamo = servicio.buscarPrestamoPorId(idPrestamo);

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

                        cell = new PdfPCell(new Phrase(nombreUsuario, FontFactory.getFont("arial", 8, Font.ITALIC)));
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

                    PdfPTable table2 = new PdfPTable(4);
                    table2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table2.setWidthPercentage(80);
                    PdfPCell cell2;

                    cell2 = new PdfPCell(new Phrase("ID Préstamo:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase("Fecha Préstamo:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase("Fecha Estimada Entrega:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase("Préstamo especial:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase(String.valueOf(idPrestamo), FontFactory.getFont("arial", 10, Font.ITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase(sdf2.format(queryPrestamo.getFechaRetiro()), FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    cell2 = new PdfPCell(new Phrase(sdf2.format(queryPrestamo.getFechaEstimadaEntrega()), FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

                    if (queryPrestamo.getPrestamoEspecial() == 1) {
                        especialPdf = "Si";
                    } else {
                        especialPdf = "No";
                    }

                    cell2 = new PdfPCell(new Phrase(especialPdf, FontFactory.getFont("arial", 10, Font.ITALIC)));
                    cell2.setBorder(0);
                    table2.addCell(cell2);

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

                    cell3 = new PdfPCell(new Phrase("Nro. Serie:", FontFactory.getFont("arial", 10, Font.BOLDITALIC)));
                    cell3.setBorder(0);
                    table3.addCell(cell3);

                    for (DetalleSolicitudPrUsCaDTO var : lista) {
                        for (Item varItem : items) {
                            if (varItem.getIdProducto() == var.getProducto().getIdProducto()) {
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

                                cell3 = new PdfPCell(new Phrase(varItem.getNroSerie()));
                                cell3.setBorder(0);
                                table3.addCell(cell3);
                            }
                        }
                    }

                    //organización documento PDF final
                    String filename = "C:\\Users\\cristian\\Documents\\NetBeans Projects\\portafolio_duoc\\logoDuoc.png";
                    Image image = Image.getInstance(filename);
                    image = Image.getInstance(filename);
                    image.scalePercent(200f);
                    image.setAbsolutePosition(400, (float) (PageSize.A4.getHeight() - 50.0));

                    image.scaleToFit(100f, 200f);

                    document.add(image);
                    document.add(new Paragraph("Detalle Préstamo: " + String.valueOf(idPrestamo), FontFactory.getFont("arial", 20, Font.BOLD)));
                    document.add(new Paragraph("Fecha emisión documento: " + sdf.format(time), FontFactory.getFont("arial", 9, Font.ITALIC)));
                    document.add(new Paragraph("______________________________________________________________________________"));
                    document.add(new Paragraph("Datos Préstamo: ", FontFactory.getFont("arial", 16, Font.BOLD)));
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

                request.setAttribute("lstSolicitud", lista);
                request.setAttribute("lstPrestamo", lstPrestamo);
                request.setAttribute("lstItems", items);
                request.setAttribute("id", idSolicitud);
                request.getRequestDispatcher("RegistroPrestamo.jsp").forward(request, response);

                servicio.enviarEmailPrestamo(nombreUsuario, idPrestamo, emailUsuario, doc);

                int largo = servicio.revisarStockPrestamo(items).size();

                if (largo > 0) {
                    ArrayList<Integer> prod = new ArrayList<>();
                    prod = servicio.revisarStockPrestamo(items);

                    servicio.alertaStockPrestamo(prod);
                }

            } catch (SQLException e) {
                throw new RuntimeException("Error en la conexion bd", e);
            }

        }

    }

}
