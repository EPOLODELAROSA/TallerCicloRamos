/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import modelo.Usuario;

/**
 *
 * @author efren
 */
public class Sesion {

    private static Usuario usuarioLogueado = null;
    private static String filtroCategoria = "Todos";

    public static Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public static void setUsuarioLogueado(Usuario usuario) {
        usuarioLogueado = usuario;
    }

    public static String getFiltroCategoria() {
        return filtroCategoria;
    }

    public static void setFiltroCategoria(String filtro) {
        filtroCategoria = filtro;
    }

    public static void cerrarSesion() {
        usuarioLogueado = null;
        filtroCategoria = "Todos";
    }

    private static int idProductoVer = -1;

    public static int getIdProductoVer() {
        return idProductoVer;
    }

    public static void setIdProductoVer(int id) {
        idProductoVer = id;
    }

    private static String textoBusqueda = "";

    public static String getTextoBusqueda() {
        return textoBusqueda;
    }

    public static void setTextoBusqueda(String texto) {
        textoBusqueda = texto;
    }

    public static void limpiarBusqueda() {
        textoBusqueda = "";
    }

    private static int idCompraRealizada = 0;

    public static int getIdCompraRealizada() {
        return idCompraRealizada;
    }

    public static void setIdCompraRealizada(int id) {
        idCompraRealizada = id;
    }

    private static int idPedidoDetalle = 0;

    public static int getIdPedidoDetalle() {
        return idPedidoDetalle;
    }

    public static void setIdPedidoDetalle(int id) {
        idPedidoDetalle = id;
    }

}
