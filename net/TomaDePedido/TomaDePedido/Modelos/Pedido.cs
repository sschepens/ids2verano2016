//-----------------------------------------------------------------------
// <copyright file="Pedido.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    using System.Collections.Generic;
    using TomaDePedido.Enums;

    public class Pedido
    {
        public int Codigo { get; }

        public int CodigoMesa { get; }

        public Enums.EstadoPedido Estado { get; }

        public bool EntregarTodoJunto { get; set; }

        public List<DetallePlato> Platos {get; set;}

        public List<DetalleCerveza> Cervezas { get; set; }

        public Pedido() { }

        public Pedido(int codigoMesa)
        {
            this.CodigoMesa = codigoMesa;
            this.Estado = Enums.EstadoPedido.Pendiente;
        }

        public void AgregarCerveza(int codigoCerveza, int cantidad)
        {
            this.Cervezas.Add(new DetalleCerveza(codigoCerveza, cantidad));
        }

        public void AgregarPlato(int codigoPlato, int cantidad)
        {
            this.Platos.Add(new DetallePlato(codigoPlato));
        }

        public void GuardarPedido()
        {
            //Todo: Enviar informacion del pedido a Control
        }

        public List<Pedido> ObtenerPedidos(int codigoMesa)
        {
            //Todo: Obtener informacion de los pedidos desde Control
            return null;
        }
    }
}
