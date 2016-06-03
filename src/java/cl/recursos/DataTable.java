/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.recursos;

import cl.dto.ProductoMarcaDTO;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class DataTable {
        // Data table plugin parameter
        int iTotalRecords;
        int iTotalDisplayRecords;
        String sEcho;
        String sColumns;
        ArrayList<ProductoMarcaDTO> aaData;

        public int getiTotalRecords() {
                return iTotalRecords;
        }

        public void setiTotalRecords(int iTotalRecords) {
                this.iTotalRecords = iTotalRecords;
        }

        public int getiTotalDisplayRecords() {
                return iTotalDisplayRecords;
        }

        public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
                this.iTotalDisplayRecords = iTotalDisplayRecords;
        }

        public String getsEcho() {
                return sEcho;
        }

        public void setsEcho(String sEcho) {
                this.sEcho = sEcho;
        }

        public String getsColumns() {
                return sColumns;
        }

        public void setsColumns(String sColumns) {
                this.sColumns = sColumns;
        }

        public ArrayList<ProductoMarcaDTO> getAaData() {
                return aaData;
        }

        public void setAaData(ArrayList<ProductoMarcaDTO> aaData) {
                this.aaData = aaData;
        }
    
}
