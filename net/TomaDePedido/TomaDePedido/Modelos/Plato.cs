//-----------------------------------------------------------------------
// <copyright file="Plato.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Descripción de la clase Plato
    /// </summary>
    public class Plato : IPlato
    {

        /// <summary>
        /// Codigo del Plato
        /// </summary>
        public int Codigo { get; }

        /// <summary>
        /// Nombre del Plato
        /// </summary>
        public string Nombre { get; }

        /// <summary>
        /// Precio del Plato
        /// </summary>
        public double Precio { get; }       

        /// <summary>
        /// Inicializa una nueva instancia de la clase Plato
        /// </summary>
        public Plato()
        {
        }

        /// <summary>
        /// Inicializa una nueva instancia de la clase Plato con sus propiedades
        /// </summary>
        /// <param name="codigo">Codigo del Plato</param>
        /// <param name="nombre">Nombre del Plato</param>
        /// <param name="precio">Precio del Plato</param>
        public Plato(int codigo, string nombre, double precio)
        {
            this.Codigo = codigo;
            this.Nombre = nombre;
            this.Precio = precio;
        }

        public double ObtenerPrecio()
        {
            return this.Precio;
        }
    }
}
