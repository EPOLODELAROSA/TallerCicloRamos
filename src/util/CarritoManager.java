/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import modelo.ItemCarrito;
import modelo.Producto;

/**
 *
 * @author efren
 */
public class CarritoManager {

    // Lista en memoria que guarda los productos del carrito
    private static ArrayList<ItemCarrito> items = new ArrayList<>();

    // Costo de envío (puedes cambiarlo aquí)
    public static final double COSTO_ENVIO = 8000.0;

    // Agrega un producto al carrito
    public static void agregarProducto(Producto producto) {
        // Buscamos si el producto ya está en el carrito
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                // Si ya está, solo aumentamos la cantidad
                item.setCantidad(item.getCantidad() + 1);
                return;
            }
        }
        // Si no está, lo agregamos como nuevo con cantidad 1
        items.add(new ItemCarrito(producto, 1));
    }

    // Obtener todos los ítems
    public static ArrayList<ItemCarrito> getItems() {
        return items;
    }

    // Eliminar un ítem
    public static void eliminarItem(int idProducto) {
        items.removeIf(item -> item.getProducto().getId() == idProducto);
    }

    // Vaciar el carrito
    public static void vaciar() {
        items.clear();
    }

    // Calcular el subtotal (suma de todos los ítems)
    public static double getSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    // Calcular el total (subtotal + envío)
    public static double getTotal() {
        return getSubtotal() + COSTO_ENVIO;
    }

    // Verificar si el carrito está vacío
    public static boolean estaVacio() {
        return items.isEmpty();
    }

}
