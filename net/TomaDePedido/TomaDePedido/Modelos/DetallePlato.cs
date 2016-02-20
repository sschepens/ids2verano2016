//-----------------------------------------------------------------------
// <copyright file="DetallePlato.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;

    public class DetallePlato : IDetallePlato
    {
        private int CodigoDetallePlato { get; set; }
        private int CodigoPlato { get; set; }

        public Enums.EstadoPedido Estado { get; }

        public string Comentario { get; set; }

        public int Cantidad { get; set; }

        public DetallePlato() { }

        public DetallePlato(int codigoPlato)
        {
            this.CodigoPlato = codigoPlato;
            this.Estado = Enums.EstadoPedido.Pendiente;
        }

        public void AsignarComentario(string comentario)
        {
            this.Comentario = comentario;
        }

        public string ObtenerComentario()
        {
            return this.Comentario;
        }

        public int ObtenerCodigoDetalle()
        {
            return this.CodigoDetallePlato;
        }

        public int ObtenerCodigoPlato()
        {
            return this.CodigoPlato;
        }
    }
}
