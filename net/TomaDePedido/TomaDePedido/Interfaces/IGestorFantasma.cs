namespace TomaDePedido.Interfaces
{  
    using System.Collections.Generic;
    using TomaDePedido.Models;

    public interface IGestorFantasma
    {
        /// <summary>
        /// Solicita el listado de los Platos disponibles para la venta
        /// </summary>
        /// <returns>Lista de Platos</returns>
        List<Plato> ObtenerPlatos();

        /// <summary>
        /// Solicita el listado de las Cervezas disponibles para la venta
        /// </summary>
        /// <returns>Lista de Cervezas</returns>
        List<Cerveza> ObtenerCervezas();
    }
}