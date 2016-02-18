//-----------------------------------------------------------------------
// <copyright file="Cerveza.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    /// <summary>
    /// Descripción de la clase Cerveza
    /// </summary>
    public class Cerveza
    {
        /// <summary>
        /// Codigo de la Cerveza
        /// </summary>
        public int Codigo { get; }

        /// <summary>
        /// Precio de la Cerveza
        /// </summary>
        public double Precio { get; }

        public Cerveza()
        {
        }

        public Cerveza(int codigo, double precio)
        {
            this.Codigo = codigo;
            this.Precio = precio;
        }
    }
}
