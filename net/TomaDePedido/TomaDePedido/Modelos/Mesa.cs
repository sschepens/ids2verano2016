//-----------------------------------------------------------------------
// <copyright file="Mesa.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;
    using System.Collections.Generic;

    public class Mesa : IMesa
    {
        public int CodigoMesa { get; set; }
        public Enums.EstadoMesa Estado { get; set; }

        public List<Pedido> Pedidos { get; }

        public Mesa() {
            Pedidos = new List<Pedido>();
        }

        public void ModificarEstado(Enums.EstadoMesa estado)
        {
            this.Estado = estado;
            //Todo enviar update de estado
        }

        public double ObtenerMontoAPagar()
        {
            //TODO: gestionar el ObtenerMenu de "Control"
            //var menu = ObtenerMenu();
            var menu = new Menu();
            var montoAPagar = 0.0;

            foreach (var pedido in this.Pedidos)
            {
                foreach (var detalleCerveza in pedido.Cervezas)
                {
                    montoAPagar += detalleCerveza.Cantidad * menu.ObtenerCerveza(detalleCerveza.CodigoCerveza).Precio ;
                }

                foreach (var detallePlato in pedido.Platos)
                {
                    montoAPagar += menu.ObtenerPlato(detallePlato.CodigoPlato).Precio;
                }
            }

            return montoAPagar;
        }
    }
}
