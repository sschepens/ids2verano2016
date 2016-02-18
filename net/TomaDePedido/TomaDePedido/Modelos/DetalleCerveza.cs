//-----------------------------------------------------------------------
// <copyright file="DetalleCerveza.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    using TomaDePedido.Interfaces;

    public class DetalleCerveza : IDetalleCerveza
    {
        public int CodigoDetalleCerveza { get; }
        public int CodigoCerveza { get; }

        public int Cantidad { get; set; }

        public DetalleCerveza() { }

        public DetalleCerveza(int codigoCerveza, int cantidad)
        {
            this.CodigoCerveza = codigoCerveza;
            this.Cantidad = cantidad;   
        }

        public int ObtenerCodigoDetalle()
        {
            return this.CodigoDetalleCerveza;
        }

        public int ObtenerCodigoCerveza()
        {
            return this.CodigoCerveza;
        }
    }
}
